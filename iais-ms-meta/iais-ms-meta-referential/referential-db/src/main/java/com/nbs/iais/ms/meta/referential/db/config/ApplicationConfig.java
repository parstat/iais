package com.nbs.iais.ms.meta.referential.db.config;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramAdministratorCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.services.CommandReferentialService;
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
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.services.QueryReferentialService;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramQuery;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {
		AdministrativeDetailsEntity.class,
		AgentEntity.class,
		AgentInRole.class,
		BusinessFunctionEntity.class,
		BusinessServiceEntity.class,
		ChangeEventEntity.class,
		LegislativeReferenceEntity.class,
		MultiLanguageTextEntity.class,
		ProcessDocumentationEntity.class,
		ProcessDocumentEntity.class,
		ProcessInputSpecificationEntity.class,
		ProcessMethodEntity.class,
		ProcessOutputSpecificationEntity.class,
		StatisticalProgramEntity.class,
		StatisticalStandardReferenceEntity.class
})
@ComponentScan(basePackageClasses = QueryReferentialService.class)
@EnableJpaRepositories(basePackageClasses = StatisticalProgramRepository.class)
@EnableIntegration
public class ApplicationConfig {

	@Bean
	public IntegrationFlow queryIntegrationFlow(final QueryReferentialService queryReferentialService) {
		return IntegrationFlows.from(queryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetStatisticalProgramsQuery.class,
								sf -> sf.<GetStatisticalProgramsQuery>handle((p, h) -> queryReferentialService.getStatisticalPrograms(p)))
						.subFlowMapping(GetStatisticalProgramQuery.class,
								sf -> sf.<GetStatisticalProgramQuery>handle((p, h) -> queryReferentialService.getStatisticalProcess(p)))
						.subFlowMapping(GetBusinessFunctionQuery.class,
								sf -> sf.<GetBusinessFunctionQuery> handle((p, h) -> queryReferentialService.getBusinessFunction(p))))
				.get();
	}

	@Bean public IntegrationFlow commandIntegrationFlow(final CommandReferentialService commandReferentialService) {
		return IntegrationFlows.from(commandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateStatisticalProgramCommand.class,
								sf -> sf.<CreateStatisticalProgramCommand>handle((p, h) -> commandReferentialService.createStatisticalProgram(p)))
						.subFlowMapping(AddStatisticalProgramAdministratorCommand.class,
								sf -> sf.<AddStatisticalProgramAdministratorCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramAdministrator(p)))
						.subFlowMapping(AddStatisticalProgramLegislativeReferenceCommand.class,
								sf -> sf.<AddStatisticalProgramLegislativeReferenceCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramLegislativeReference(p)))
						.subFlowMapping(AddStatisticalProgramStandardCommand.class,
								sf -> sf.<AddStatisticalProgramStandardCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramStandard(p))))
				.get();
	}


	@Bean(name = Channels.QUERY_INPUT)
	public MessageChannel queryInput() {
		return new DirectChannel();

	}

	@Bean(name = Channels.COMMAND_INPUT)
	public MessageChannel commandInput() {
		return new DirectChannel();
	}


	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		return Pollers.fixedRate(500).get();
	}
}
