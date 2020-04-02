package com.nbs.iais.cloud.zuul.jwt.service;

import com.auth0.jwt.JWT;
import com.nbs.iais.cloud.zuul.jwt.model.PrivateJwt;
import com.nbs.iais.cloud.zuul.jwt.repository.TokenRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class SecurityServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private SecurityService securityService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void createSigninJwt() {

        final PrivateJwt privateJwt = new PrivateJwt();
        privateJwt.setSecret("secret");
        privateJwt.setValid(true);

        Mockito.when(tokenRepository.savePrivateToken(Mockito.anyString(), Mockito.any())).thenReturn(privateJwt);
        final Optional<String> jwtOptional = securityService.getToken("{\n" +
                "    \"id\": \"60fa3ee5-c886-4f44-89ec-495b27ef9876\",\n" +
                "    \"name\": \"Florian Nika\",\n" +
                "    \"username\": \"florian\",\n" +
                "    \"role\": \"USER\",\n" +
                "    \"status\": \"ACTIVE\"\n" +
                "}");

        Assert.assertTrue(jwtOptional.isPresent());
        final String jwt = jwtOptional.get();
        final String name = JWT.decode(jwt).getClaim("name").asString();
        Assert.assertEquals("Florian Nika",name);
    }

    @Test
    public void getSignupJwt() {

    }
}
