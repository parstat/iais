package com.nbs.iais.cloud.zuul.config;

import com.nbs.iais.cloud.zuul.filters.post.SigninJwtFilter;
import com.nbs.iais.cloud.zuul.filters.post.SignupJwtFilter;
import com.nbs.iais.cloud.zuul.filters.pre.AuthenticatedFilter;
import com.nbs.iais.cloud.zuul.filters.pre.LanguageFilter;
import com.nbs.iais.cloud.zuul.filters.pre.SignoutFilter;
import com.nbs.iais.cloud.zuul.filters.pre.XssFilter;
import com.nbs.iais.ms.common.enums.Language;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public AuthenticatedFilter securityFilter() {
        return new AuthenticatedFilter();
    }

    @Bean
    public XssFilter xssFilter() {
        return new XssFilter();
    }


    @Bean
    public SigninJwtFilter signinJwtFilter() {
        return new SigninJwtFilter();
    }

    @Bean
    public SignupJwtFilter signupJwtFilter() {
        return new SignupJwtFilter();
    }

    @Bean
    public SignoutFilter signoutFilter() {
        return new SignoutFilter();
    }

    @Bean
    public LanguageFilter languageFilter() {
        return new LanguageFilter();
    }
}
