package com.nbs.iais.cloud.zuul.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nbs.iais.cloud.zuul.jwt.model.PrivateJwt;
import com.nbs.iais.cloud.zuul.jwt.repository.TokenRepository;
import com.nbs.iais.cloud.zuul.utils.JwtUtils;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SecurityService {

    @Autowired
    private TokenRepository tokenRepository;

    public String getSignedJwt(final UUID user, final String name, final String email, final AccountRole role,
                               final boolean authenticated) {

        final String tokenKey = JwtUtils.getJwtPrivateKey(user.toString());
        final PrivateJwt privateJwt = new PrivateJwt();

        privateJwt.setValid(true);
        privateJwt.setSecret(UUID.randomUUID().toString());

        tokenRepository.savePrivateToken(tokenKey, privateJwt);

        return JwtUtils.createJwt(user, name, email, role.toString(), authenticated, privateJwt.getSecret());
    }

    public String verifyJwt(final String jwt) throws JWTVerificationException {
        final String secret = tokenRepository.getToken(JWT.decode(jwt).getClaim("user").asString()).getSecret();
        return JwtUtils.verifyJwt(jwt, secret).getClaim("user").asString();
    }

    public void invalidateToken(final String jwt) {
        tokenRepository.deleteToken(StringTools.hash(JWT.decode(jwt).getClaim("user").asString()));
    }
}
