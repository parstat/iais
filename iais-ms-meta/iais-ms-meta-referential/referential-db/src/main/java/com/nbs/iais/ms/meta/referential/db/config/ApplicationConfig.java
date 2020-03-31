package com.nbs.iais.ms.meta.referential.db.config;

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

import com.nbs.iais.ms.meta.referential.db.domains.StatisticalProcessEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProcessRepository;
import com.nbs.iais.ms.meta.referential.db.services.ReferentialService;
import com.nbs.iais.ms.referential.common.messageing.commands.StatisticalProcessQueryCommand;
/*
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = StatisticalProcessEntity.class)
@ComponentScan(basePackageClasses = ReferentialService.class)
@EnableJpaRepositories(basePackageClasses = StatisticalProcessRepository.class)
@EnableIntegration
@IntegrationComponentScan*/
public class ApplicationConfig {



    @Bean
    public IntegrationFlow integrationFlow(final ReferentialService referentialService) {
        return IntegrationFlows.from(this::serviceInput)
                .<Object, Class<?>>route(Object::getClass, rs -> rs
                        .subFlowMapping(StatisticalProcessQueryCommand.class, sf ->
                                sf.<StatisticalProcessQueryCommand>handle((p, h) -> referentialService.findStatisticalProcessById(p))))
               .get();
    }

    @Bean
    public DirectChannel serviceInput() {
        return new DirectChannel();
    }



}
