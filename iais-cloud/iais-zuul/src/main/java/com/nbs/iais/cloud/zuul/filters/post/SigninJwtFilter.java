package com.nbs.iais.cloud.zuul.filters.post;

import com.nbs.iais.cloud.zuul.jwt.service.SecurityService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

public class SigninJwtFilter extends ZuulFilter {

    private final static Logger LOG = LoggerFactory.getLogger(SigninJwtFilter.class);

    @Value("${iais.jwt.header.name}")
    private final String jwtHeaderName = "jwt-auth";

    @Autowired
    private SecurityService securityService;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI();
        final String method = request.getMethod();


        // this filter should run only when signing in or authenticating
        return !method.equals(HttpMethod.OPTIONS.toString())
                && requestUri.matches(".*/api/v[12]/{0,1}\\w*/{0,1}\\w*/{0,1}\\w*/(signin|authenticate).*");
    }

    @Override
    public Object run() {
        try {
            final RequestContext ctx = RequestContext.getCurrentContext();
            final HttpServletRequest request = ctx.getRequest();
            final InputStream responseBody = ctx.getResponseDataStream();
            final String responseBodyString = StreamUtils.copyToString(responseBody, StandardCharsets.UTF_8);

            securityService.getToken(responseBodyString).ifPresent(jwt ->
                ctx.addZuulResponseHeader(jwtHeaderName, jwt));

            ctx.setResponseBody(responseBodyString);
        } catch (IOException ex) {
            rethrowRuntimeException(ex);
        }
        return null;

    }

}
