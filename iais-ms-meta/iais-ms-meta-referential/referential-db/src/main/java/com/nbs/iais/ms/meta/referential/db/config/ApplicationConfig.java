package com.nbs.iais.ms.meta.referential.db.config;

import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.*;
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
import com.nbs.iais.ms.meta.referential.db.services.QueryReferentialService;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {
		AdministrativeDetailsEntity.class,
		AgentEntity.class,
		AgentInRoleEntity.class,
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
		ProcessQualityEntity.class,
		StatisticalProgramEntity.class,
		StatisticalStandardReferenceEntity.class })
@ComponentScan(basePackageClasses = {
		QueryReferentialService.class,
		CommandReferentialService.class })
@EnableJpaRepositories(basePackageClasses = {
		AgentRepository.class,
		AgentInRoleRepository.class,
		BusinessFunctionRepository.class,
		LegislativeReferenceRepository.class,
		StatisticalStandardReferenceRepository.class,
		StatisticalProgramRepository.class })
@EnableIntegration
public class ApplicationConfig {

	@Bean
	public IntegrationFlow queryIntegrationFlow(final QueryReferentialService queryReferentialService) {
		return IntegrationFlows.from(queryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						//Statistical Program
						.subFlowMapping(GetStatisticalProgramsQuery.class,
								sf -> sf.<GetStatisticalProgramsQuery>handle(
										(p, h) -> queryReferentialService.getStatisticalPrograms(p)))
						.subFlowMapping(GetStatisticalProgramQuery.class,
								sf -> sf.<GetStatisticalProgramQuery>handle((p, h) -> queryReferentialService.getStatisticalProgram(p)))
						//Business Function
						.subFlowMapping(GetBusinessFunctionQuery.class,
								sf -> sf.<GetBusinessFunctionQuery>handle(
										(p, h) -> queryReferentialService.getBusinessFunction(p)))
						.subFlowMapping(GetBusinessFunctionsQuery.class,
								sf -> sf.<GetBusinessFunctionsQuery>handle((p, h) -> queryReferentialService.getBusinessFunctions(p)))
						//Agent
						.subFlowMapping(GetAgentsQuery.class,
								sf -> sf.<GetAgentsQuery>handle((p, h) -> queryReferentialService.getAgents(p)))
						.subFlowMapping(GetAgentQuery.class,
								sf -> sf.<GetAgentQuery>handle((p, h) -> queryReferentialService.getAgent(p)))
						//Statistical Standard
						.subFlowMapping(GetStatisticalStandardsQuery.class,
								sf -> sf.<GetStatisticalStandardsQuery>handle((p, h) -> queryReferentialService.getStatisticalStandards(p)))
						.subFlowMapping(GetStatisticalStandardQuery.class,
								sf -> sf.<GetStatisticalStandardQuery>handle((p, h) -> queryReferentialService.getStatisticalStandard(p)))

				).get();
	}

	@Bean
	public IntegrationFlow commandIntegrationFlow(final CommandReferentialService commandReferentialService) {
		return IntegrationFlows.from(commandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						//Statistical Program
						.subFlowMapping(CreateStatisticalProgramCommand.class,
								sf -> sf.<CreateStatisticalProgramCommand>handle((p, h) -> commandReferentialService.createStatisticalProgram(p)))
						.subFlowMapping(AddStatisticalProgramAdministratorCommand.class,
								sf -> sf.<AddStatisticalProgramAdministratorCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramAdministrator(p)))
						.subFlowMapping(AddStatisticalProgramLegislativeReferenceCommand.class,
								sf -> sf.<AddStatisticalProgramLegislativeReferenceCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramLegislativeReference(p)))
						.subFlowMapping(AddStatisticalProgramStandardCommand.class,
								sf -> sf.<AddStatisticalProgramStandardCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramStandard(p)))
						.subFlowMapping(AddStatisticalProgramVersionCommand.class,
								sf -> sf.<AddStatisticalProgramVersionCommand>handle((p, h) -> commandReferentialService.addStatisticalProgramVersion(p)))
						.subFlowMapping(UpdateStatisticalProgramCommand.class,
								sf -> sf.<UpdateStatisticalProgramCommand>handle((p, h) -> commandReferentialService.updateStatisticalProgram(p)))
						.subFlowMapping(DeleteStatisticalProgramCommand.class,
								sf -> sf.<DeleteStatisticalProgramCommand>handle((p, h) -> commandReferentialService.deleteStatisticalProgram(p)))
						//Agent
						.subFlowMapping(CreateAgentCommand.class,
								sf -> sf.<CreateAgentCommand>handle((p, h) -> commandReferentialService.createAgent(p)))
						.subFlowMapping(UpdateAgentCommand.class,
								sf -> sf.<UpdateAgentCommand>handle((p, h) -> commandReferentialService.updateAgent(p)))
						.subFlowMapping(DeleteAgentCommand.class,
								sf -> sf.<DeleteAgentCommand>handle((p, h) -> commandReferentialService.deleteAgent(p)))
						//Statistical Standard
						.subFlowMapping(CreateStatisticalStandardCommand.class,
								sf -> sf.<CreateStatisticalStandardCommand>handle((p, h) -> commandReferentialService.createStatisticalStandard(p)))
						.subFlowMapping(UpdateStatisticalStandardCommand.class,
								sf -> sf.<UpdateStatisticalStandardCommand>handle((p, h) -> commandReferentialService.updateStatisticalStandard(p)))
						.subFlowMapping(DeleteStatisticalStandardCommand.class,
								sf -> sf.<DeleteStatisticalStandardCommand>handle((p, h) -> commandReferentialService.deleteStatisticalStandard(p)))
					
						//Business Function
						.subFlowMapping(CreateBusinessFunctionCommand.class,
								sf -> sf.<CreateBusinessFunctionCommand>handle((p, h) -> commandReferentialService.createBusinessFunction(p)))
						.subFlowMapping(UpdateBusinessFunctionCommand.class,
								sf -> sf.<UpdateBusinessFunctionCommand>handle((p, h) -> commandReferentialService.updateBusinessFunction(p))))
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
