package com.nbs.iais.ms.security.db.config;

import com.nbs.iais.ms.common.exceptions.ChangePasswordException;
import com.nbs.iais.ms.security.common.messageing.commands.*;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import com.nbs.iais.ms.security.common.messageing.queries.IsAuthenticatedQuery;
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
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = AccountEntity.class)
@ComponentScan(basePackageClasses = {CommandSecurityService.class, QuerySecurityService.class})
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
@EnableIntegration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    public IntegrationFlow commandIntegrationFlow(final CommandSecurityService commandSecurityService) {
        return IntegrationFlows.from(commandInput())
                .<Object, Class<?>>route(Object::getClass, rs -> rs
                        .subFlowMapping(SignupCommand.class, sf ->
                                sf.<SignupCommand>handle((p, h) -> commandSecurityService.signup(p)))
                        .subFlowMapping(SigninCommand.class, sf ->
                                sf.<SigninCommand>handle((p, h) -> commandSecurityService.signin(p)))
                        .subFlowMapping(ChangePasswordCommand.class, sf ->
                                sf.<ChangePasswordCommand>handle((p, h) -> commandSecurityService.changePassword(p)))
                        .subFlowMapping(ResetPasswordCommand.class, sf ->
                                sf.<ResetPasswordCommand>handle((p, h) -> commandSecurityService.resetPassword(p)))
                        .subFlowMapping(ConfirmEmailCommand.class, sf ->
                                sf.<ConfirmEmailCommand>handle((p, h) -> commandSecurityService.confirmEmail(p))))
                .get();
    }

    @Bean
    public IntegrationFlow queryIntegrationFlow(final QuerySecurityService querySecurityService) {
        return IntegrationFlows.from(queryInput())
                .<Object, Class<?>>route(Object::getClass, rs -> rs
                        .subFlowMapping(GetAccountsQuery.class, sf ->
                                sf.<GetAccountsQuery>handle((p, h) -> querySecurityService.getAccounts(p)))
                        .subFlowMapping(IsAuthenticatedQuery.class, sf ->
                                sf.<IsAuthenticatedQuery>handle((p, h) -> querySecurityService.authenticate(p))))
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
