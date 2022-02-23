package com.nbs.iais.cloud.zuul.jwt.service;

import com.auth0.jwt.JWT;
import com.nbs.iais.cloud.zuul.jwt.model.PrivateJwt;
import com.nbs.iais.cloud.zuul.jwt.repository.TokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
public class SecurityServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private SecurityService securityService;

    @BeforeAll
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void createJwt() {

        final PrivateJwt privateJwt = new PrivateJwt();
        privateJwt.setSecret("secret");
        privateJwt.setValid(true);

        Mockito.when(tokenRepository.savePrivateToken(Mockito.anyString(), Mockito.any())).thenReturn(privateJwt);
        final Optional<String> jwtOptional = securityService.getToken("{\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"Florian Nika\",\n" +
                "    \"username\": \"florian\",\n" +
                "    \"role\": \"USER\",\n" +
                "    \"email\": \"florian.nika@parstat.md\",\n" +
                "    \"status\": \"ACTIVE\"\n" +
                "}");

        Assertions.assertTrue(jwtOptional.isPresent());
        final String jwt = jwtOptional.get();
        final String name = JWT.decode(jwt).getClaim("name").asString();
        final Long id = JWT.decode(jwt).getClaim("user").asLong();
        Assertions.assertEquals("Florian Nika",name);
        Assertions.assertEquals(1L, (long) id);
    }

}
