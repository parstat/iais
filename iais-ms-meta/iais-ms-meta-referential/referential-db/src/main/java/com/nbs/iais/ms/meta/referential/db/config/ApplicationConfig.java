package com.nbs.iais.ms.meta.referential.db.config;

import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.*;
import com.nbs.iais.ms.meta.referential.db.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
		ProcessDocumentationQueryService.class,
		ProcessDocumentationCommandService.class,
		StatisticalProgramCommandService.class,
		StatisticalProgramQueryService.class,
		AgentCommandService.class,
		AgentQueryService.class,
		BusinessFunctionCommandService.class,
		BusinessFunctionQueryService.class,
		StatisticalStandardCommandService.class,
		StatisticalStandardQueryService.class,
		LegislativeReferenceCommandService.class,
		LegislativeReferenceQueryService.class})
@EnableJpaRepositories(basePackageClasses = {
		AgentRepository.class,
		AgentInRoleRepository.class,
		BusinessFunctionRepository.class,
		LegislativeReferenceRepository.class,
		StatisticalStandardReferenceRepository.class,
		StatisticalProgramRepository.class })
@EnableIntegration
public class ApplicationConfig {

	/**
	 * Message router to route all the message in the correspondent channel
	 * @return IntegrationFlow
	 */

	@Bean
	public IntegrationFlow queryIntegrationFlow() {
		return IntegrationFlows.from(queryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
					.channelMapping(GetAgentQuery.class, Channels.AGENT_QUERY_INPUT)
					.channelMapping(GetAgentsQuery.class, Channels.AGENT_QUERY_INPUT)
					.channelMapping(GetBusinessFunctionQuery.class, Channels.BUSINESS_FUNCTION_QUERY_INPUT)
					.channelMapping(GetBusinessFunctionsQuery.class, Channels.BUSINESS_FUNCTION_QUERY_INPUT)
					.channelMapping(GetLegislativeReferenceQuery.class, Channels.LEGISLATIVE_REFERENCE_QUERY_INPUT)
					.channelMapping(GetLegislativeReferencesQuery.class, Channels.LEGISLATIVE_REFERENCE_QUERY_INPUT)
					.channelMapping(GetProcessDocumentationQuery.class, Channels.PROCESS_DOCUMENTATION_QUERY_INPUT)
					.channelMapping(GetProcessDocumentationsQuery.class, Channels.PROCESS_DOCUMENTATION_QUERY_INPUT)
					.channelMapping(GetStatisticalProgramQuery.class, Channels.STATISTICAL_PROGRAM_QUERY_INPUT)
					.channelMapping(GetStatisticalProgramsQuery.class, Channels.STATISTICAL_PROGRAM_QUERY_INPUT)
					.channelMapping(GetStatisticalStandardQuery.class, Channels.STATISTICAL_STANDARD_QUERY_INPUT)
					.channelMapping(GetStatisticalStandardsQuery.class, Channels.STATISTICAL_STANDARD_QUERY_INPUT))
				.get();
	}

	@Bean
	public IntegrationFlow commandIntegrationFlow() {
		return IntegrationFlows.from(commandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.channelMapping(CreateAgentCommand.class, Channels.AGENT_COMMAND_INPUT)
						.channelMapping(UpdateAgentCommand.class, Channels.AGENT_COMMAND_INPUT)
						.channelMapping(DeleteAgentCommand.class, Channels.AGENT_COMMAND_INPUT)
						.channelMapping(CreateBusinessFunctionCommand.class, Channels.BUSINESS_FUNCTION_COMMAND_INPUT)
						.channelMapping(UpdateBusinessFunctionCommand.class, Channels.BUSINESS_FUNCTION_COMMAND_INPUT)
						.channelMapping(CreateLegislativeReferenceCommand.class, Channels.LEGISLATIVE_REFERENCE_COMMAND_INPUT)
						.channelMapping(UpdateLegislativeReferenceCommand.class, Channels.LEGISLATIVE_REFERENCE_COMMAND_INPUT)
						.channelMapping(CreateProcessDocumentationCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(UpdateProcessDocumentationCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationDocumentCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationMethodCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationInputCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationOutputCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationStandardCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationQualityCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(AddProcessDocumentationVersionCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
						.channelMapping(CreateStatisticalProgramCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(AddStatisticalProgramVersionCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(UpdateStatisticalProgramCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(AddStatisticalProgramAdministratorCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(RemoveStatisticalProgramAdministratorCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(AddStatisticalProgramStandardCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(RemoveStatisticalProgramStandardCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(AddStatisticalProgramLegislativeReferenceCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(RemoveStatisticalProgramLegislativeReferenceCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
						.channelMapping(CreateStatisticalStandardCommand.class, Channels.STATISTICAL_STANDARD_COMMAND_INPUT)
						.channelMapping(UpdateStatisticalStandardCommand.class, Channels.STATISTICAL_STANDARD_COMMAND_INPUT))
				.get();
	}


	@Bean
	public IntegrationFlow agentQueryIntegrationFlow(final AgentQueryService agentQueryService) {
		return IntegrationFlows.from(agentQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetAgentsQuery.class,
								sf -> sf.<GetAgentsQuery>handle((p, h) -> agentQueryService.getAgents(p)))
						.subFlowMapping(GetAgentQuery.class,
								sf -> sf.<GetAgentQuery>handle((p, h) -> agentQueryService.getAgent(p)))
				).get();
	}

	@Bean IntegrationFlow agentCommandIntegrationFlow(final AgentCommandService agentCommandService) {
		return IntegrationFlows.from(agentCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateAgentCommand.class,
								sf -> sf.<CreateAgentCommand>handle((p, h) -> agentCommandService.createAgent(p)))
						.subFlowMapping(UpdateAgentCommand.class,
								sf -> sf.<UpdateAgentCommand>handle((p, h) -> agentCommandService.updateAgent(p)))
						.subFlowMapping(DeleteAgentCommand.class,
								sf -> sf.<DeleteAgentCommand>handle((p, h) -> agentCommandService.deleteAgent(p)))
				).get();
	}

	@Bean
	public IntegrationFlow businessFunctionQueryIntegrationFlow(final BusinessFunctionQueryService businessFunctionQueryService) {
		return IntegrationFlows.from(businessFunctionQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetBusinessFunctionQuery.class,
								sf -> sf.<GetBusinessFunctionQuery>handle((p, h) -> businessFunctionQueryService.getBusinessFunction(p)))
						.subFlowMapping(GetBusinessFunctionsQuery.class,
								sf -> sf.<GetBusinessFunctionsQuery>handle((p, h) -> businessFunctionQueryService.getBusinessFunctions(p)))
				).get();
	}

	@Bean
	public IntegrationFlow businessFunctionCommandIntegrationFlow(final BusinessFunctionCommandService businessFunctionCommandService) {
		return IntegrationFlows.from(businessFunctionCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateBusinessFunctionCommand.class,
								sf -> sf.<CreateBusinessFunctionCommand>handle((p, h) -> businessFunctionCommandService.createBusinessFunction(p)))
						.subFlowMapping(UpdateBusinessFunctionCommand.class,
								sf -> sf.<UpdateBusinessFunctionCommand>handle((p, h) -> businessFunctionCommandService.updateBusinessFunction(p)))
				).get();
	}

	@Bean
	public IntegrationFlow legislativeReferenceQueryIntegrationFlow(final LegislativeReferenceQueryService legislativeReferenceQueryService) {
		return IntegrationFlows.from(legislativeReferenceQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetLegislativeReferenceQuery.class,
								sf -> sf.<GetLegislativeReferenceQuery>handle((p, h) -> legislativeReferenceQueryService.getLegislativeReference(p)))
						.subFlowMapping(GetLegislativeReferencesQuery.class,
								sf -> sf.<GetLegislativeReferencesQuery>handle((p, h) -> legislativeReferenceQueryService.getLegislativeReferences(p)))
				).get();
	}

	@Bean
	public IntegrationFlow legislativeReferenceCommandIntegrationFlow(final LegislativeReferenceCommandService legislativeReferenceCommandService) {
		return IntegrationFlows.from(legislativeReferenceCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateLegislativeReferenceCommand.class,
								sf -> sf.<CreateLegislativeReferenceCommand>handle((p, h) -> legislativeReferenceCommandService.createLegislativeReference(p)))
						.subFlowMapping(UpdateLegislativeReferenceCommand.class,
								sf -> sf.<UpdateLegislativeReferenceCommand>handle((p, h) -> legislativeReferenceCommandService.updateLegislativeReference(p)))
						.subFlowMapping(DeleteLegislativeReferenceCommand.class,
								sf -> sf.<DeleteLegislativeReferenceCommand>handle((p, h) -> legislativeReferenceCommandService.deleteLegislativeReference(p)))
				).get();
	}

	@Bean
	public IntegrationFlow statisticalProgramQueryIntegrationFlow(final StatisticalProgramQueryService statisticalProgramQueryService) {
		return IntegrationFlows.from(statisticalProgramQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetStatisticalProgramQuery.class,
								sf -> sf.<GetStatisticalProgramQuery>handle((p, h) -> statisticalProgramQueryService.getStatisticalProgram(p)))
						.subFlowMapping(GetStatisticalProgramsQuery.class,
								sf -> sf.<GetStatisticalProgramsQuery>handle((p, h) -> statisticalProgramQueryService.getStatisticalPrograms(p)))
				).get();
	}

	@Bean
	public IntegrationFlow statisticalProgramCommandIntegrationFlow(final StatisticalProgramCommandService statisticalProgramCommandService) {
		return IntegrationFlows.from(statisticalProgramCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateStatisticalProgramCommand.class,
								sf -> sf.<CreateStatisticalProgramCommand>handle((p, h) ->
										statisticalProgramCommandService.createStatisticalProgram(p)))
						.subFlowMapping(UpdateStatisticalProgramCommand.class,
								sf -> sf.<UpdateStatisticalProgramCommand>handle((p, h) ->
										statisticalProgramCommandService.updateStatisticalProgram(p)))
						.subFlowMapping(DeleteStatisticalProgramCommand.class,
								sf -> sf.<DeleteStatisticalProgramCommand>handle((p, h) ->
										statisticalProgramCommandService.deleteStatisticalProgram(p)))
						.subFlowMapping(AddStatisticalProgramAdministratorCommand.class,
								sf -> sf.<AddStatisticalProgramAdministratorCommand>handle((p, h) ->
										statisticalProgramCommandService.addStatisticalProgramAdministrator(p)))
						.subFlowMapping(RemoveStatisticalProgramAdministratorCommand.class,
								sf -> sf.<RemoveStatisticalProgramAdministratorCommand>handle((p, h) ->
										statisticalProgramCommandService.removeStatisticalProgramAdministrator(p)))
						.subFlowMapping(AddStatisticalProgramLegislativeReferenceCommand.class,
								sf -> sf.<AddStatisticalProgramLegislativeReferenceCommand>handle((p, h) ->
										statisticalProgramCommandService.addStatisticalProgramLegislativeReference(p)))
						.subFlowMapping(RemoveStatisticalProgramLegislativeReferenceCommand.class,
								sf -> sf.<RemoveStatisticalProgramLegislativeReferenceCommand>handle((p, h) ->
										statisticalProgramCommandService.removeStatisticalProgramLegislativeReference(p)))
						.subFlowMapping(AddStatisticalProgramStandardCommand.class,
								sf -> sf.<AddStatisticalProgramStandardCommand>handle((p, h) ->
										statisticalProgramCommandService.addStatisticalProgramStandard(p)))
						.subFlowMapping(RemoveStatisticalProgramStandardCommand.class,
								sf -> sf.<RemoveStatisticalProgramStandardCommand>handle((p, h) ->
										statisticalProgramCommandService.removeStatisticalProgramStandard(p)))
						.subFlowMapping(AddStatisticalProgramVersionCommand.class,
								sf -> sf.<AddStatisticalProgramVersionCommand>handle((p, h) ->
										statisticalProgramCommandService.addStatisticalProgramVersion(p)))
				).get();
	}

	@Bean
	public IntegrationFlow processDocumentationQueryIntegrationFlow(final ProcessDocumentationQueryService processDocumentationQueryService) {
		return IntegrationFlows.from(processDocumentationQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(GetProcessDocumentationQuery.class,
								sf -> sf.<GetProcessDocumentationQuery>handle((p, h) -> processDocumentationQueryService.getProcessDocumentation(p)))
						.subFlowMapping(GetProcessDocumentationsQuery.class,
								sf -> sf.<GetProcessDocumentationsQuery>handle((p, h) -> processDocumentationQueryService.getProcessDocumentations(p)))
				).get();
	}

	@Bean
	public IntegrationFlow processDocumentationCommandIntegrationFlow(final ProcessDocumentationCommandService processDocumentationCommandService) {
		return IntegrationFlows.from(processDocumentationCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateProcessDocumentationCommand.class,
								sf -> sf.<CreateProcessDocumentationCommand>handle((p, h) -> processDocumentationCommandService.createProcessDocumentation(p)))
						.subFlowMapping(UpdateProcessDocumentationCommand.class,
								sf -> sf.<UpdateProcessDocumentationCommand>handle((p, h) -> processDocumentationCommandService.updateProcessDocumentation(p)))
						.subFlowMapping(DeleteProcessDocumentationCommand.class,
								sf -> sf.<DeleteProcessDocumentationCommand>handle((p, h) -> processDocumentationCommandService.deleteProcessDocumentation(p)))
						.subFlowMapping(AddProcessDocumentationDocumentCommand.class,
								sf -> sf.<AddProcessDocumentationDocumentCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationDocumentCommand(p)))
						.subFlowMapping(AddProcessDocumentationMethodCommand.class,
								sf -> sf.<AddProcessDocumentationMethodCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationMethodCommand(p)))
						.subFlowMapping(AddProcessDocumentationInputCommand.class,
								sf -> sf.<AddProcessDocumentationInputCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationInputCommand(p)))
						.subFlowMapping(AddProcessDocumentationOutputCommand.class,
								sf -> sf.<AddProcessDocumentationOutputCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationOutputCommand(p)))
						.subFlowMapping(AddProcessDocumentationQualityCommand.class,
								sf -> sf.<AddProcessDocumentationQualityCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationQualityCommand(p)))
						.subFlowMapping(AddProcessDocumentationStandardCommand.class,
								sf -> sf.<AddProcessDocumentationStandardCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationStandardCommand(p)))
						.subFlowMapping(AddProcessDocumentationVersionCommand.class,
								sf -> sf.<AddProcessDocumentationVersionCommand>handle((p, h) -> processDocumentationCommandService.addProcessDocumentationVersionCommand(p)))
				).get();
	}


	@Bean(name = Channels.QUERY_INPUT)
	public MessageChannel queryInput() {
		return new DirectChannel();

	}

	@Bean(name = Channels.COMMAND_INPUT)
	public MessageChannel commandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.AGENT_QUERY_INPUT)
	public MessageChannel agentQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.AGENT_COMMAND_INPUT)
	public MessageChannel agentCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.BUSINESS_FUNCTION_QUERY_INPUT)
	public MessageChannel businessFunctionQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.BUSINESS_FUNCTION_COMMAND_INPUT)
	public MessageChannel businessFunctionCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.LEGISLATIVE_REFERENCE_QUERY_INPUT)
	public MessageChannel legislativeReferenceQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.LEGISLATIVE_REFERENCE_COMMAND_INPUT)
	public MessageChannel legislativeReferenceCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_DOCUMENTATION_QUERY_INPUT)
	public MessageChannel processDocumentationQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
	public MessageChannel processDocumentationCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.STATISTICAL_PROGRAM_QUERY_INPUT)
	public MessageChannel statisticalProgramQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
	public MessageChannel statisticalProgramCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.STATISTICAL_STANDARD_QUERY_INPUT)
	public MessageChannel statisticalStandardQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.STATISTICAL_STANDARD_COMMAND_INPUT)
	public MessageChannel statisticalStandardCommandInput() {
		return new DirectChannel();
	}



	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		return Pollers.fixedRate(500).get();
	}
}
