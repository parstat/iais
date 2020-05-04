package com.nbs.iais.cloud.zuul.config;

import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public Cache tokenCache() {
        return new ConcurrentMapCache("token_cache");
    }

}
