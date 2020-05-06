package com.nbs.iais.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})
@EnableZuulProxy
@EnableCaching
public class ZuulApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ZuulApplication.class);
    }


    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

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
