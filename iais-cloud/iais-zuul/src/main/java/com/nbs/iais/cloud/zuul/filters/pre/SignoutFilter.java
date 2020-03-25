package com.nbs.iais.cloud.zuul.filters.pre;

import com.auth0.jwt.exceptions.*;
import com.nbs.iais.cloud.zuul.jwt.service.SecurityService;
import com.nbs.iais.ms.common.utils.StringTools;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

public class SignoutFilter extends ZuulFilter {

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
        // this filter should run for every request except security and closed requests
        return !method.equals(HttpMethod.OPTIONS.toString())
                && requestUri.matches(".*/api/v[12]/{0,1}\\w*/{0,1}\\w*/{0,1}\\w*/(signout).*");
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        final String jwtAuthHeader = request.getHeader(jwtHeaderName);

        if(StringTools.isEmpty(jwtAuthHeader)) {
            //the user is not signed in
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        try {
            final String jwt = securityService.verifyJwt(jwtAuthHeader);
            securityService.invalidateToken(jwt);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            return null;
        } catch (TokenExpiredException ex) {
            securityService.invalidateToken(jwtAuthHeader);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            return null;
        }
        catch (AlgorithmMismatchException | InvalidClaimException |
                JWTDecodeException | SignatureVerificationException ex) {
            //Shouldn't go here Zuul will mark as bad request if jwt is empty or jwt is not provided by dotoday
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
            return null;
        }

    }
}
