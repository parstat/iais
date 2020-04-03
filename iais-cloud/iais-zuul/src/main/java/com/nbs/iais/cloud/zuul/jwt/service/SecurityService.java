package com.nbs.iais.cloud.zuul.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbs.iais.cloud.zuul.jwt.model.PrivateJwt;
import com.nbs.iais.cloud.zuul.jwt.repository.TokenRepository;
import com.nbs.iais.cloud.zuul.utils.JwtUtils;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.utils.StringTools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityService {

    private final static Logger LOG = LogManager.getLogger(SecurityService.class);

    @Autowired
    private TokenRepository tokenRepository;

    private String getSignedJwt(final UUID user, final String name, final String email, final AccountRole role) {

        final PrivateJwt privateJwt = createPrivateJwt(user.toString());
        return JwtUtils.createJwt(user, name, email, role.toString(), privateJwt.getSecret());
    }

    public String verifyJwt(final String jwt) throws JWTVerificationException {
        final String secret = tokenRepository
                .getToken(JwtUtils.getJwtPrivateKey(JWT.decode(jwt).getClaim("user").asString()))
                .getSecret();
        return JwtUtils.verifyJwt(jwt, secret).getClaim("user").asString();
    }

    public void invalidateToken(final String jwt) {
        tokenRepository.deleteToken(JwtUtils.getJwtPrivateKey((JWT.decode(jwt).getClaim("user").asString())));
    }

    private PrivateJwt createPrivateJwt(final String user) {
        final String tokenKey = JwtUtils.getJwtPrivateKey(user);
        final PrivateJwt privateJwt = new PrivateJwt();

        privateJwt.setValid(true);
        privateJwt.setSecret(UUID.randomUUID().toString());

        return  tokenRepository.savePrivateToken(tokenKey, privateJwt);
    }

    public String refreshToken(final String oldJwt) {
        final String user = JWT.decode(oldJwt).getClaim("user").asString();
        final String name = JWT.decode(oldJwt).getClaim("name").asString();
        final String email = JWT.decode(oldJwt).getClaim("email").asString();
        final String role = JWT.decode(oldJwt).getClaim("role").asString();

        //delete old private token from cache
        tokenRepository.deleteToken(JwtUtils.getJwtPrivateKey(user));
        //create the new private in cache
        final PrivateJwt privateJwt = createPrivateJwt(user);

        return JwtUtils.createJwt(UUID.fromString(user), name, email, role, privateJwt.getSecret());
    }

    @JsonView(Views.Secure.class)
    private Optional<AccountDTO> jsonToAccount(final String jsonResponse) {

        if (StringTools.isNotEmpty(jsonResponse)) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final AccountDTO accountDTO = objectMapper.readValue(jsonResponse, AccountDTO.class);
                return Optional.of(accountDTO);
            } catch (IOException ex) {
                LOG.error("Error serializing json {}", ex.getMessage());
            }
        }

        return Optional.empty();
    }

    /**
     * Create a JWT after signin or signup
     *
     * @param jsonResponse response from security server
     * @return optional token
     */
    public Optional<String> getToken(final String jsonResponse) {

        final Optional<AccountDTO> optionalAccountDTO = jsonToAccount(jsonResponse);

        if (optionalAccountDTO.isPresent()) {

            final AccountDTO accountDTO = optionalAccountDTO.get();

            if (accountDTO.getId() != null) {
                final String jwt = getSignedJwt(accountDTO.getId(), accountDTO.getName(), accountDTO.getEmail(),
                        accountDTO.getRole());
                return Optional.of(jwt);
            }
        }

        return Optional.empty();
    }
}
