package com.nbs.iais.ms.common.api.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

public abstract class AbstractApiConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corssFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedOrigin("http://localhost:8090");
        config.setAllowedMethods(Arrays.asList(RequestMethod.GET.name(),
                RequestMethod.POST.name(),
                RequestMethod.OPTIONS.name(),
                RequestMethod.DELETE.name(),
                RequestMethod.PUT.name(),
                RequestMethod.PATCH.name()));
        config.setExposedHeaders(Collections.singletonList("jwt-auth"));
        config.setAllowedHeaders(Collections.singletonList("jwt-auth"));
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;

    }


}

