package com.nbs.iais.cloud.zuul.filters.pre;

import com.nbs.iais.cloud.zuul.utils.SecurityTools;
import com.nbs.iais.cloud.zuul.utils.ZuulUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

public class XssFilter extends ZuulFilter {

    @Value("${iais.jwt.header.name}")
    private final String jwtHeaderName = "jwt-auth";


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
        final String method = request.getMethod();
        return method.equalsIgnoreCase(HttpMethod.OPTIONS.toString());
    }

    @Override
    public Object run() {
        try {
            final RequestContext context = getCurrentContext();

            InputStream in = (InputStream) context.get("requestEntity");
            final HttpServletRequest request = context.getRequest();
            // check for xss injections in requestUri or query string
            if ((request.getRequestURI() != null && SecurityTools.isNotSecure(URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8)))
                    || (request.getQueryString() != null && SecurityTools.isNotSecure(URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8)))
                    || (request.getRequestURL() != null && SecurityTools.isNotSecure(request.getRequestURL().toString()))) {
                ZuulUtils.stopRequest(context, HttpStatus.FORBIDDEN, jwtHeaderName);
                return null;
            }

            if (in == null) {
                in = request.getInputStream();
            }
            if (in != null) {
                final String body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
                // body = "request body modified via set('requestEntity'): "+ body;
                // check body content for xss injections
                if (SecurityTools.isNotSecure(body)) {
                    ZuulUtils.stopRequest(context, HttpStatus.FORBIDDEN, jwtHeaderName);
                    return null;
                }
            }

            // check context request query params for xss injections
            final Map<String, List<String>> contextQueryParams = context.getRequestQueryParams();
            if (contextQueryParams != null && contextQueryParams.size() > 0) {
                for (final Map.Entry<String, List<String>> entry : contextQueryParams.entrySet()) {
                    for (final String param : entry.getValue()) {
                        if (SecurityTools.isNotSecure(param)) {
                            ZuulUtils.stopRequest(context, HttpStatus.FORBIDDEN, jwtHeaderName);
                            return null;
                        }
                    }
                }
            }

            // check query parameters for xss injections
            final Map<String, String[]> parameters = request.getParameterMap();
            if (parameters != null && parameters.size() > 0) {
                for (final Map.Entry<String, String[]> entry : parameters.entrySet()) {
                    for (final String param : entry.getValue()) {
                        if (SecurityTools.isNotSecure(param)) {
                            ZuulUtils.stopRequest(context, HttpStatus.FORBIDDEN, jwtHeaderName);
                            return null;
                        }
                    }
                }
            }

        } catch (final IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }
}
