package com.nbs.iais.ms.security.db.config;

import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.services.SecurityService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = AccountEntity.class)
@ComponentScan(basePackageClasses = SecurityService.class)
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
@EnableIntegration
@IntegrationComponentScan
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    public IntegrationFlow integrationFlow(final SecurityService securityService) {
        return IntegrationFlows.from(this::serviceInput)
                .<Object, Class<?>>route(Object::getClass, rs -> rs
                        .subFlowMapping(SignupCommand.class, sf ->
                                sf.<SignupCommand>handle((p, h) -> securityService.signup(p)))
                        .subFlowMapping(SigninCommand.class, sf ->
                                sf.<SigninCommand>handle((p, h) -> securityService.signin(p))))
                .get();
    }

    @Bean
    public DirectChannel serviceInput() {
        return new DirectChannel();
    }

}
