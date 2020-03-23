package com.nbs.iais.cloud.zuul.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nbs.iais.cloud.zuul.exceptions.CustomException;
import com.nbs.iais.ms.security.db.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private JwtService jwtService;

    public JwtTokenFilter(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (JwtUtils.getRequestJwtToken(request).isPresent()) {
            final String jwt = JwtUtils.getRequestJwtToken(request).get();
            try {
                jwtService.verifyJwt(jwt);
            }
            catch (JWTVerificationException ex ) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT token");
                throw new CustomException("Invalid Jwt Token", HttpStatus.UNAUTHORIZED);
            }

            Authentication auth = jwtService.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
