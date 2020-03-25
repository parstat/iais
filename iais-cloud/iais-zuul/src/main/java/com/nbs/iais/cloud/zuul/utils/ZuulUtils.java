package com.nbs.iais.cloud.zuul.utils;

import com.nbs.iais.ms.common.utils.StringTools;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZuulUtils {

    /**
     * Method to stop zuul forwarding the request and Zuul sends a response with
     * httpstatus and includes jwt header if there was one in request
     *
     * @param context
     * @param httpStatus
     * @param jwtHeaderName
     */
    public static void stopRequest(final RequestContext context, final HttpStatus httpStatus,
                                   final String jwtHeaderName) {
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(httpStatus.value());
        final String jwtAuthHeader = context.getRequest().getHeader(jwtHeaderName);
        if (StringTools.isNotEmpty(jwtAuthHeader)) {
            context.addZuulResponseHeader(jwtHeaderName, jwtAuthHeader);
        }
    }

    /**
     * Method to stop zuul forwarding the request and Zuul sends a response with
     * httpstatus and includes jwt header if there was one in request
     *
     * @param context
     * @param httpStatus
     * @param headers
     */
    public static void signoutRequest(final RequestContext context, final HttpStatus httpStatus,
                                      final Map<String, String> headers) {
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(httpStatus.value());
        headers.forEach(context::addZuulResponseHeader);
    }
}
