package com.nbs.iais.ms.meta.referential.db.config;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.enums.AdministrativeStatus;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
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
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import com.nbs.iais.ms.common.messaging.channels.Channels;
import com.nbs.iais.ms.meta.referential.db.domains.StatisticalProcessEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProcessRepository;
import com.nbs.iais.ms.meta.referential.db.services.QueryReferentialService;
import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessQuery;
import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessesQuery;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {
		StatisticalProcessEntity.class,
		AdministrativeDetailsEntity.class,
		AgentEntity.class,
		AgentInRole.class,
		BusinessFunctionEntity.class,
		BusinessProcessEntity.class,
		BusinessServiceEntity.class,
		ChangeEventEntity.class,
		LegislativeReferenceEntity.class,
		MultiLanguageTextEntity.class,
		ProcessDesignEntity.class,
		ProcessDocumentEntity.class,
		ProcessInputSpecificationsEntity.class,
		ProcessMethodEntity.class,
		ProcessOutputSpecificationEntity.class,
		ProcessStepEntity.class,
		RoleEntity.class,
		StatisticalProgramCycleEntity.class,
		StatisticalProgramDesignEntity.class,
		StatisticalProgramEntity.class,
		StatisticalStandardReferenceEntity.class
})
@ComponentScan(basePackageClasses = QueryReferentialService.class)
@EnableJpaRepositories(basePackageClasses = StatisticalProcessRepository.class)
@EnableIntegration
public class ApplicationConfig {

	@Bean
	public IntegrationFlow queryIntegrationFlow(final QueryReferentialService queryReferentialService) {
		return IntegrationFlows.from(queryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetStatisticalProcessesQuery.class,
								sf -> sf.<GetStatisticalProcessesQuery>handle((p, h) -> queryReferentialService.getStatisticalProcesses(p)))
						.subFlowMapping(GetStatisticalProcessQuery.class,
								sf -> sf.<GetStatisticalProcessQuery>handle((p, h) -> queryReferentialService.getStatisticalProcess(p))))
				.get();
	}

	@Bean(name = Channels.QUERY_INPUT)
	public MessageChannel queryInput() {
		return new DirectChannel();

	}

	@Bean
	public MessageChannel commandInput() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel serviceInput() {
		return new DirectChannel();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		return Pollers.fixedRate(500).get();
	}
}
