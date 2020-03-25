package com.nbs.iais.cloud.zuul.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbs.iais.ms.common.utils.StringTools;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {

    private static final String SECRET = "secret"; //TODO change this
    final private static ObjectMapper mapper = new ObjectMapper();
    final public static int LIFESPAN_SECONDS = 3600;

    public static String createJwt(final UUID id, final String name, final String email, final String role,
                                   final String secret) {
        return JWT.create().withIssuer("iais")
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("user", id.toString())
                .withClaim("name", name)
                .withClaim("email", email)
                .withClaim("role", role)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600))).sign(Algorithm.HMAC256(secret));
    }

    public static DecodedJWT verifyJwt(final String token, final String secret) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);
    }

    public static String getJwtPrivateKey(final String userId) {
        return StringTools.hash(userId);
    }

}
