package com.nbs.iais.cloud.zuul.filters.pre;

import com.auth0.jwt.exceptions.*;
import com.nbs.iais.cloud.zuul.jwt.service.SecurityService;
import com.nbs.iais.cloud.zuul.utils.ZuulUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

public class AuthenticatedFilter extends ZuulFilter {

    @Value("${iais.jwt.header.name}")
    private final String jwtHeaderName = "jwt-auth";

    @Autowired
    private SecurityService securityService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();

        final String requestUri = request.getRequestURI();
        final String method = request.getMethod();

        return !method.equals(HttpMethod.OPTIONS.toString())
                && requestUri.matches(".*/api/v\\d+\\.?(\\d+\\.?)?(\\d+)?/\\w*/?\\w*/?[cC][lL][oO][sS][eE].*");
    }

    @Override
    public Object run() {

        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        final String jwtAuthHeader = request.getHeader(jwtHeaderName);
        try {
            securityService.verifyJwt(jwtAuthHeader);
            ctx.addZuulResponseHeader(jwtHeaderName, jwtAuthHeader);
        }
        catch (TokenExpiredException ex) {
            //if expired provide a new one
            final String jwt = securityService.refreshToken(jwtAuthHeader);
            ctx.addZuulResponseHeader(jwtHeaderName, jwt);
        }
        catch (Exception ex) {
            //any other case return a unauthorized response status
            ZuulUtils.stopRequest(ctx, HttpStatus.UNAUTHORIZED, jwtHeaderName);
        }

        return null;
    }

}
