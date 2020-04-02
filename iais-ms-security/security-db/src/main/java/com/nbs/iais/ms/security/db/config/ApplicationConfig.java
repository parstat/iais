package com.nbs.iais.ms.security.db.config;

import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.services.CommandSecurityService;
import com.nbs.iais.ms.security.db.services.QuerySecurityService;
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
@ComponentScan(basePackageClasses = CommandSecurityService.class)
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
@EnableIntegration
@IntegrationComponentScan
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    public IntegrationFlow commandIntegrationFlow(final CommandSecurityService commandSecurityService) {
        return IntegrationFlows.from(this::commandInput)
                .<Object, Class<?>>route(Object::getClass, rs -> rs
                        .subFlowMapping(SignupCommand.class, sf ->
                                sf.<SignupCommand>handle((p, h) -> commandSecurityService.signup(p)))
                        .subFlowMapping(SigninCommand.class, sf ->
                                sf.<SigninCommand>handle((p, h) -> commandSecurityService.signin(p))))
                .get();
    }

    @Bean
    public IntegrationFlow queryIntegrationFlow(final QuerySecurityService querySecurityService) {
        return IntegrationFlows.from(this::queryInput)
                .<Object, Class<?>>route(Object::getClass, rs -> rs
                        .subFlowMapping(GetAccountsQuery.class, sf ->
                                sf.<GetAccountsQuery>handle((p, h) -> querySecurityService.getAccounts(p))))
                        .get();
    }

    @Bean
    public DirectChannel queryInput() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel commandInput() {
        return new DirectChannel();
    }
}
