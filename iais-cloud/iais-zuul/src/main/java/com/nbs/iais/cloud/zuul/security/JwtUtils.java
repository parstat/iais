package com.nbs.iais.cloud.zuul.security;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class JwtUtils {

    private static final String AUTHORIZATION="Authorization";

    public static Optional<String> getRequestJwtToken(final HttpServletRequest req) {
        String jwt = req.getHeader(AUTHORIZATION);
        if(jwt != null) {
            return Optional.of(jwt);
        }
        return Optional.empty();
    }
}
