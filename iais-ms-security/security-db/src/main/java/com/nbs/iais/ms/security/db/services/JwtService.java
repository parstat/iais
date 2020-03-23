package com.nbs.iais.ms.security.db.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Verification;
import com.nbs.iais.ms.security.db.auth.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.http.HttpRequest;
import java.time.Instant;
import java.util.Date;

public class JwtService {

    private final String SECRET = "secret"; //TODO change this

    public String createJwt(final String username, final String email, final String role) {

        return JWT.create().withIssuer("iais")
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("user", username)
                .withClaim("email", email)
                .withClaim("role", role)
                .withIssuedAt(Date.from(Instant.now().plusSeconds(3600))).sign(Algorithm.HMAC256(SECRET));
    }

    public void verifyJwt(final String token) throws JWTVerificationException {

        JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
    }

    public UserDetails getUserDetails(String token) {
        String userName =  JWT.decode(token).getClaim("user").asString();
        String role = JWT.decode(token).getClaim("role").asString();
        return new SecurityUser(userName, role);
    }

    public Authentication getAuthentication(String token) {
        //using data base: uncomment when you want to fetch data from data base
        //UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        //from token take user value. comment below line for changing it taking from data base
        UserDetails userDetails = getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
