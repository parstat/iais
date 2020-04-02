package com.nbs.iais.cloud.zuul.filters.pre;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.utils.StringTools;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

public class LanguageFilter extends ZuulFilter {
    private static String getLanguage(RequestContext context) {
        List<String> languages = new ArrayList<>();
        if (context.getRequestQueryParams() != null) {
            languages = context.getRequestQueryParams().get("language");
        }
        if (languages != null && !languages.isEmpty()) {
            return languages.get(0);
        }

        return context.getRequest().getParameter("language");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        // should run after PreDecorationFilter
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {

        final RequestContext context = RequestContext.getCurrentContext();
        final HttpServletRequest request = context.getRequest();
        final String method = request.getMethod();
        if (StringTools.isNotEmpty(request.getQueryString()) && request.getQueryString().contains("language")
                || (StringTools.isNotEmpty(request.getRequestURI()) && request.getRequestURI().contains("language"))) {
            return false;
        }
        final String language = getLanguage(context);

        return (!method.equals(HttpMethod.OPTIONS.toString()) && (StringTools.isEmpty(language)));
    }

    @Override
    public Object run() {
        final RequestContext context = RequestContext.getCurrentContext();
        Map<String, List<String>> params = context.getRequestQueryParams();
        if (params == null) {
            params = new HashMap<>();
        }
        params.putIfAbsent("language",
                new ArrayList<>(Collections.singletonList(Language.ENG.toString())));
        context.setRequestQueryParams(params);
        return null;
    }
}
