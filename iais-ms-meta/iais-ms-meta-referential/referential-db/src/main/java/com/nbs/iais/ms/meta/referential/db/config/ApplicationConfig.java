package com.nbs.iais.ms.meta.referential.db.config;

import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.CreateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.DeleteProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.UpdateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service.GetBusinessServiceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service.GetBusinessServicesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodsQuery;
import com.nbs.iais.ms.meta.referential.db.services.*;
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
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.CreateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.DeleteProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveProcessDocumentationInputTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.UpdateProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.AddOutputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.CreateOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.DeleteOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.RemoveOutputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.UpdateOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.CreateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.DeleteProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.UpdateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramAdministratorCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.DeleteStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.RemoveStatisticalProgramAdministratorCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.RemoveStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.RemoveStatisticalProgramStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.input.specification.GetProcessInputSpecificationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.input.specification.GetProcessInputSpecificationsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.output.specification.GetProcessOutputSpecificationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.output.specification.GetProcessOutputSpecificationsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.quality.GetProcessQualitiesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.quality.GetProcessQualityQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AdministrativeDetailsEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentInRoleEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessServiceEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ChangeEventEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.MultiLanguageTextEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessMethodEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessOutputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessQualityEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentInRoleRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = { AdministrativeDetailsEntity.class, AgentEntity.class, AgentInRoleEntity.class,
		BusinessFunctionEntity.class, BusinessServiceEntity.class, ChangeEventEntity.class,
		LegislativeReferenceEntity.class, MultiLanguageTextEntity.class, ProcessDocumentationEntity.class,
		ProcessDocumentEntity.class, ProcessInputSpecificationEntity.class, ProcessMethodEntity.class,
		ProcessOutputSpecificationEntity.class, ProcessQualityEntity.class, StatisticalProgramEntity.class,
		StatisticalStandardReferenceEntity.class })
@ComponentScan(basePackageClasses = { ProcessDocumentationQueryService.class, ProcessDocumentationCommandService.class,
		StatisticalProgramCommandService.class, StatisticalProgramQueryService.class, AgentCommandService.class,
		AgentQueryService.class, BusinessFunctionCommandService.class, BusinessFunctionQueryService.class,
		StatisticalStandardCommandService.class, StatisticalStandardQueryService.class,
		LegislativeReferenceCommandService.class, LegislativeReferenceQueryService.class })
@EnableJpaRepositories(basePackageClasses = { AgentRepository.class, AgentInRoleRepository.class,
		BusinessFunctionRepository.class, LegislativeReferenceRepository.class,
		StatisticalStandardReferenceRepository.class, StatisticalProgramRepository.class })
@EnableIntegration
public class ApplicationConfig {

	/**
	 * Message router to route all the message in the correspondent channel
	 * 
	 * @return IntegrationFlow
	 */

	@Bean
	public IntegrationFlow queryIntegrationFlow() {
		return IntegrationFlows.from(queryInput()).<Object, Class<?>>route(Object::getClass,
				rs -> rs
						//AGENT
						.channelMapping(GetAgentQuery.class, Channels.AGENT_QUERY_INPUT)
						.channelMapping(GetAgentsQuery.class, Channels.AGENT_QUERY_INPUT)
						//BUSINESS FUNCTION
						.channelMapping(GetBusinessFunctionQuery.class, Channels.BUSINESS_FUNCTION_QUERY_INPUT)
						.channelMapping(GetBusinessFunctionsQuery.class, Channels.BUSINESS_FUNCTION_QUERY_INPUT)
						//BUSINESS SERVICE
						.channelMapping(GetBusinessServiceQuery.class, Channels.BUSINESS_SERVICE_QUERY_INPUT)
						.channelMapping(GetBusinessServicesQuery.class, Channels.BUSINESS_SERVICE_QUERY_INPUT)
						//LEGISLATIVE REFERENCE
						.channelMapping(GetLegislativeReferenceQuery.class, Channels.LEGISLATIVE_REFERENCE_QUERY_INPUT)
						.channelMapping(GetLegislativeReferencesQuery.class, Channels.LEGISLATIVE_REFERENCE_QUERY_INPUT)
						//PROCESS DOCUMENTATION
						.channelMapping(GetProcessDocumentationQuery.class, Channels.PROCESS_DOCUMENTATION_QUERY_INPUT)
						.channelMapping(GetProcessDocumentationsQuery.class, Channels.PROCESS_DOCUMENTATION_QUERY_INPUT)
						//STATISTICAL PROGRAM
						.channelMapping(GetStatisticalProgramQuery.class, Channels.STATISTICAL_PROGRAM_QUERY_INPUT)
						.channelMapping(GetStatisticalProgramsQuery.class, Channels.STATISTICAL_PROGRAM_QUERY_INPUT)
						//STATISTICAL STANDARD
						.channelMapping(GetStatisticalStandardQuery.class, Channels.STATISTICAL_STANDARD_QUERY_INPUT)
						.channelMapping(GetStatisticalStandardsQuery.class, Channels.STATISTICAL_STANDARD_QUERY_INPUT)
						//PROCESS DOCUMENTATION DOCUMENT
						.channelMapping(GetProcessDocumentQuery.class, Channels.PROCESS_DOCUMENT_QUERY_INPUT)
						.channelMapping(GetProcessDocumentsQuery.class, Channels.PROCESS_DOCUMENT_QUERY_INPUT)
						//PROCESS DOCUMENTATION QUALITY
						.channelMapping(GetProcessQualityQuery.class, Channels.PROCESS_QUALITY_QUERY_INPUT)
						.channelMapping(GetProcessQualitiesQuery.class, Channels.PROCESS_QUALITY_QUERY_INPUT)
						//PROCESS METHOD
						.channelMapping(GetProcessMethodQuery.class, Channels.PROCESS_METHOD_QUERY_INPUT)
						.channelMapping(GetProcessMethodsQuery.class, Channels.PROCESS_METHOD_QUERY_INPUT)
						//PROCESS INPUT SPECIFICATION
						.channelMapping(GetProcessInputSpecificationQuery.class, Channels.PROCESS_INPUT_SPECIFICATION_QUERY_INPUT)
						.channelMapping(GetProcessInputSpecificationsQuery.class, Channels.PROCESS_INPUT_SPECIFICATION_QUERY_INPUT)
						//PROCESS OUTPUT SPECIFICATION
						.channelMapping(GetProcessOutputSpecificationQuery.class, Channels.PROCESS_OUTPUT_SPECIFICATION_QUERY_INPUT)
						.channelMapping(GetProcessOutputSpecificationsQuery.class, Channels.PROCESS_OUTPUT_SPECIFICATION_QUERY_INPUT)

		).get();
	}

	@Bean
	public IntegrationFlow commandIntegrationFlow() {
		return IntegrationFlows.from(commandInput()).<Object, Class<?>>route(Object::getClass, rs -> rs
				//AGENT
				.channelMapping(CreateAgentCommand.class, Channels.AGENT_COMMAND_INPUT)
				.channelMapping(UpdateAgentCommand.class, Channels.AGENT_COMMAND_INPUT)
				.channelMapping(DeleteAgentCommand.class, Channels.AGENT_COMMAND_INPUT)
				//BUSINESS FUNCTION
				.channelMapping(CreateBusinessFunctionCommand.class, Channels.BUSINESS_FUNCTION_COMMAND_INPUT)
				.channelMapping(UpdateBusinessFunctionCommand.class, Channels.BUSINESS_FUNCTION_COMMAND_INPUT)
				//BUSINESS SERVICE
				.channelMapping(CreateBusinessServiceCommand.class, Channels.BUSINESS_SERVICE_COMMAND_INPUT)
				.channelMapping(UpdateBusinessServiceCommand.class, Channels.BUSINESS_SERVICE_COMMAND_INPUT)
				.channelMapping(DeleteBusinessServiceCommand.class, Channels.BUSINESS_SERVICE_COMMAND_INPUT)
				.channelMapping(AddBusinessServiceInterfaceCommand.class, Channels.BUSINESS_SERVICE_COMMAND_INPUT)
				.channelMapping(RemoveBusinessServiceInterfaceCommand.class, Channels.BUSINESS_SERVICE_COMMAND_INPUT)
				//LEGISLATIVE REFERENCE
				.channelMapping(CreateLegislativeReferenceCommand.class, Channels.LEGISLATIVE_REFERENCE_COMMAND_INPUT)
				.channelMapping(UpdateLegislativeReferenceCommand.class, Channels.LEGISLATIVE_REFERENCE_COMMAND_INPUT)
				.channelMapping(DeleteLegislativeReferenceCommand.class, Channels.LEGISLATIVE_REFERENCE_COMMAND_INPUT)
				//PROCESS DOCUMENTATION
				.channelMapping(CreateProcessDocumentationCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(UpdateProcessDocumentationCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationDocumentCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationMethodCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationInputCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationOutputCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationStandardCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationQualityCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationVersionCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationAdministratorCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(RemoveProcessDocumentationAdministratorCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(RemoveProcessDocumentationStandardCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(RemoveProcessDocumentationMethodCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationServiceCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				.channelMapping(RemoveProcessDocumentationServiceCommand.class, Channels.PROCESS_DOCUMENTATION_COMMAND_INPUT)
				//PROCESS METHOD
				.channelMapping(CreateProcessMethodCommand.class, Channels.PROCESS_METHOD_COMMAND_INPUT)
				.channelMapping(UpdateProcessMethodCommand.class, Channels.PROCESS_METHOD_COMMAND_INPUT)
				.channelMapping(DeleteProcessMethodCommand.class, Channels.PROCESS_METHOD_COMMAND_INPUT)
				//STATISTICAL PROGRAM
				.channelMapping(CreateStatisticalProgramCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(AddStatisticalProgramVersionCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(UpdateStatisticalProgramCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(DeleteStatisticalProgramCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(AddStatisticalProgramAdministratorCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(RemoveStatisticalProgramAdministratorCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(AddStatisticalProgramStandardCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(RemoveStatisticalProgramStandardCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(AddStatisticalProgramLegislativeReferenceCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				.channelMapping(RemoveStatisticalProgramLegislativeReferenceCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				//STATISTICAL STANDARD
				.channelMapping(CreateStatisticalStandardCommand.class, Channels.STATISTICAL_STANDARD_COMMAND_INPUT)
				.channelMapping(UpdateStatisticalStandardCommand.class, Channels.STATISTICAL_STANDARD_COMMAND_INPUT)
				.channelMapping(DeleteStatisticalStandardCommand.class, Channels.STATISTICAL_PROGRAM_COMMAND_INPUT)
				//PROCESS DOCUMENT
				.channelMapping(CreateProcessDocumentCommand.class, Channels.PROCESS_DOCUMENT_COMMAND_INPUT)
				.channelMapping(UpdateProcessDocumentCommand.class, Channels.PROCESS_DOCUMENT_COMMAND_INPUT)
				.channelMapping(DeleteProcessDocumentCommand.class, Channels.PROCESS_DOCUMENT_COMMAND_INPUT)
				.channelMapping(CreateProcessQualityCommand.class, Channels.PROCESS_QUALITY_COMMAND_INPUT)
				.channelMapping(UpdateProcessQualityCommand.class, Channels.PROCESS_QUALITY_COMMAND_INPUT)
				.channelMapping(DeleteProcessQualityCommand.class, Channels.PROCESS_QUALITY_COMMAND_INPUT)
				//STATISTICAL INPUT SPECIFICATION
				.channelMapping(AddProcessDocumentationInputCommand.class, Channels.PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(UpdateProcessDocumentationInputCommand.class, Channels.PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(RemoveProcessDocumentationInputCommand.class, Channels.PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(AddProcessDocumentationInputTypeCommand.class, Channels.PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(RemoveProcessDocumentationInputTypeCommand.class, Channels.PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT)
				//STATISTICAL OUTPUT SPECIFICATION
				.channelMapping(CreateOutputSpecificationCommand.class, Channels.PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(UpdateOutputSpecificationCommand.class, Channels.PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(DeleteOutputSpecificationCommand.class, Channels.PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(AddOutputSpecificationTypeCommand.class, Channels.PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT)
				.channelMapping(RemoveOutputSpecificationTypeCommand.class, Channels.PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT)

		).get();
	}

	@Bean
	public IntegrationFlow agentQueryIntegrationFlow(final AgentQueryService agentQueryService) {
		return IntegrationFlows.from(agentQueryInput())
				.<Object, Class<?>>route(Object::getClass,
						rs -> rs.subFlowMapping(GetAgentsQuery.class,
								sf -> sf.<GetAgentsQuery>handle((p, h) -> agentQueryService.getAgents(p)))
								.subFlowMapping(GetAgentQuery.class,
										sf -> sf.<GetAgentQuery>handle((p, h) -> agentQueryService.getAgent(p))))
				.get();
	}

	@Bean
	IntegrationFlow agentCommandIntegrationFlow(final AgentCommandService agentCommandService) {
		return IntegrationFlows.from(agentCommandInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(CreateAgentCommand.class,
										sf -> sf.<CreateAgentCommand>handle(
												(p, h) -> agentCommandService.createAgent(p)))
								.subFlowMapping(
										UpdateAgentCommand.class,
										sf -> sf.<UpdateAgentCommand>handle(
												(p, h) -> agentCommandService.updateAgent(p)))
								.subFlowMapping(DeleteAgentCommand.class, sf -> sf
										.<DeleteAgentCommand>handle((p, h) -> agentCommandService.deleteAgent(p))))
				.get();
	}

	@Bean
	public IntegrationFlow businessFunctionQueryIntegrationFlow(
			final BusinessFunctionQueryService businessFunctionQueryService) {
		return IntegrationFlows.from(businessFunctionQueryInput()).<Object, Class<?>>route(Object::getClass, rs -> rs
				.subFlowMapping(GetBusinessFunctionQuery.class,
						sf -> sf.<GetBusinessFunctionQuery>handle(
								(p, h) -> businessFunctionQueryService.getBusinessFunction(p)))
				.subFlowMapping(GetBusinessFunctionsQuery.class, sf -> sf.<GetBusinessFunctionsQuery>handle(
						(p, h) -> businessFunctionQueryService.getBusinessFunctions(p))))
				.get();
	}

	@Bean
	public IntegrationFlow businessFunctionCommandIntegrationFlow(
			final BusinessFunctionCommandService businessFunctionCommandService) {
		return IntegrationFlows.from(businessFunctionCommandInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(CreateBusinessFunctionCommand.class,
										sf -> sf.<CreateBusinessFunctionCommand>handle(
												(p, h) -> businessFunctionCommandService.createBusinessFunction(p)))
								.subFlowMapping(UpdateBusinessFunctionCommand.class,
										sf -> sf.<UpdateBusinessFunctionCommand>handle(
												(p, h) -> businessFunctionCommandService.updateBusinessFunction(p))))
				.get();
	}

	@Bean
	public IntegrationFlow businessServiceQueryIntegrationFlow(
			final BusinessServiceQueryService businessServiceQueryService) {
		return IntegrationFlows.from(businessServiceQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetBusinessServiceQuery.class,
										sf -> sf.<GetBusinessServiceQuery>handle((p, h) -> businessServiceQueryService.getBusinessService(p)))
								.subFlowMapping(GetBusinessServicesQuery.class,
										sf -> sf.<GetBusinessServicesQuery>handle((p, h) -> businessServiceQueryService.getBusinessServices(p))))
				.get();
	}

	@Bean
	public IntegrationFlow businessServiceCommandIntegrationFlow(
			final BusinessServiceCommandService businessServiceCommandService) {

		return IntegrationFlows.from(businessServiceCommandInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(CreateBusinessServiceCommand.class,
										sf -> sf.<CreateBusinessServiceCommand>handle((p, h)
												-> businessServiceCommandService.createBusinessService(p)))

								.subFlowMapping(UpdateBusinessServiceCommand.class,
										sf -> sf.<UpdateBusinessServiceCommand>handle((p, h)
												-> businessServiceCommandService.updateBusinessService(p)))

								.subFlowMapping(DeleteBusinessServiceCommand.class,
										sf -> sf.<DeleteBusinessServiceCommand>handle((p, h)
												-> businessServiceCommandService.deleteBusinessService(p)))

								.subFlowMapping(AddBusinessServiceInterfaceCommand.class,
										sf -> sf.<AddBusinessServiceInterfaceCommand>handle((p, h) -> businessServiceCommandService.addBusinessServiceInterface(p)))
								.subFlowMapping(RemoveBusinessServiceInterfaceCommand.class,
										sf -> sf.<RemoveBusinessServiceInterfaceCommand>handle((p, h) -> businessServiceCommandService.removeBusinessServiceInterface(p))))


						.get();

	}

	@Bean
	public IntegrationFlow legislativeReferenceQueryIntegrationFlow(
			final LegislativeReferenceQueryService legislativeReferenceQueryService) {
		return IntegrationFlows.from(legislativeReferenceQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetLegislativeReferenceQuery.class,
										sf -> sf.<GetLegislativeReferenceQuery>handle(
												(p, h) -> legislativeReferenceQueryService.getLegislativeReference(p)))
								.subFlowMapping(GetLegislativeReferencesQuery.class,
										sf -> sf.<GetLegislativeReferencesQuery>handle((p,
												h) -> legislativeReferenceQueryService.getLegislativeReferences(p))))
				.get();
	}

	@Bean
	public IntegrationFlow legislativeReferenceCommandIntegrationFlow(
			final LegislativeReferenceCommandService legislativeReferenceCommandService) {
		return IntegrationFlows.from(legislativeReferenceCommandInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(CreateLegislativeReferenceCommand.class,
										sf -> sf.<CreateLegislativeReferenceCommand>handle((p,
												h) -> legislativeReferenceCommandService.createLegislativeReference(p)))
								.subFlowMapping(UpdateLegislativeReferenceCommand.class,
										sf -> sf.<UpdateLegislativeReferenceCommand>handle((p,
												h) -> legislativeReferenceCommandService.updateLegislativeReference(p)))
								.subFlowMapping(DeleteLegislativeReferenceCommand.class,
										sf -> sf.<DeleteLegislativeReferenceCommand>handle(
												(p, h) -> legislativeReferenceCommandService
														.deleteLegislativeReference(p))))
				.get();
	}

	@Bean
	public IntegrationFlow statisticalStandardQueryIntegrationFlow(
			final StatisticalStandardQueryService statisticalStandardQueryService) {

		return IntegrationFlows.from(statisticalStandardQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
								.subFlowMapping(GetStatisticalStandardQuery.class,
										sf -> sf.<GetStatisticalStandardQuery>handle((p, h) -> statisticalStandardQueryService.getStatisticalStandard(p)))
								.subFlowMapping(GetStatisticalStandardsQuery.class,
										sf -> sf.<GetStatisticalStandardsQuery>handle((p, h) -> statisticalStandardQueryService.getStatisticalStandards(p)))
				).get();
	}

	@Bean
	public IntegrationFlow statisticalStandardCommandIntegrationFlow(
			final StatisticalStandardCommandService statisticalStandardCommandService) {

		return IntegrationFlows.from(statisticalStandardCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateStatisticalStandardCommand.class,
								sf -> sf.<CreateStatisticalStandardCommand>handle((p, h) -> statisticalStandardCommandService.createStatisticalStandard(p)))
						.subFlowMapping(UpdateStatisticalStandardCommand.class,
								sf -> sf.<UpdateStatisticalStandardCommand>handle((p, h) -> statisticalStandardCommandService.updateStatisticalStandard(p)))
						.subFlowMapping(DeleteStatisticalStandardCommand.class,
								sf -> sf.<DeleteStatisticalStandardCommand>handle((p, h) -> statisticalStandardCommandService.deleteStatisticalStandard(p)))
				).get();
	}

	@Bean
	public IntegrationFlow statisticalProgramQueryIntegrationFlow(
			final StatisticalProgramQueryService statisticalProgramQueryService) {
		return IntegrationFlows.from(statisticalProgramQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetStatisticalProgramQuery.class,
										sf -> sf.<GetStatisticalProgramQuery>handle(
												(p, h) -> statisticalProgramQueryService.getStatisticalProgram(p)))
								.subFlowMapping(GetStatisticalProgramsQuery.class,
										sf -> sf.<GetStatisticalProgramsQuery>handle(
												(p, h) -> statisticalProgramQueryService.getStatisticalPrograms(p))))
				.get();
	}

	@Bean
	public IntegrationFlow statisticalProgramCommandIntegrationFlow(
			final StatisticalProgramCommandService statisticalProgramCommandService) {
		return IntegrationFlows.from(statisticalProgramCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateStatisticalProgramCommand.class,
								sf -> sf.<CreateStatisticalProgramCommand>handle(
										(p, h) -> statisticalProgramCommandService.createStatisticalProgram(p)))
						.subFlowMapping(UpdateStatisticalProgramCommand.class,
								sf -> sf.<UpdateStatisticalProgramCommand>handle(
										(p, h) -> statisticalProgramCommandService.updateStatisticalProgram(p)))
						.subFlowMapping(DeleteStatisticalProgramCommand.class,
								sf -> sf.<DeleteStatisticalProgramCommand>handle(
										(p, h) -> statisticalProgramCommandService.deleteStatisticalProgram(p)))
						.subFlowMapping(AddStatisticalProgramAdministratorCommand.class,
								sf -> sf.<AddStatisticalProgramAdministratorCommand>handle((p,
										h) -> statisticalProgramCommandService.addStatisticalProgramAdministrator(p)))
						.subFlowMapping(RemoveStatisticalProgramAdministratorCommand.class,
								sf -> sf.<RemoveStatisticalProgramAdministratorCommand>handle(
										(p, h) -> statisticalProgramCommandService
												.removeStatisticalProgramAdministrator(p)))
						.subFlowMapping(AddStatisticalProgramLegislativeReferenceCommand.class,
								sf -> sf.<AddStatisticalProgramLegislativeReferenceCommand>handle(
										(p, h) -> statisticalProgramCommandService
												.addStatisticalProgramLegislativeReference(p)))
						.subFlowMapping(RemoveStatisticalProgramLegislativeReferenceCommand.class,
								sf -> sf.<RemoveStatisticalProgramLegislativeReferenceCommand>handle(
										(p, h) -> statisticalProgramCommandService
												.removeStatisticalProgramLegislativeReference(p)))
						.subFlowMapping(AddStatisticalProgramStandardCommand.class,
								sf -> sf.<AddStatisticalProgramStandardCommand>handle(
										(p, h) -> statisticalProgramCommandService.addStatisticalProgramStandard(p)))
						.subFlowMapping(RemoveStatisticalProgramStandardCommand.class,
								sf -> sf.<RemoveStatisticalProgramStandardCommand>handle(
										(p, h) -> statisticalProgramCommandService.removeStatisticalProgramStandard(p)))
						.subFlowMapping(AddStatisticalProgramVersionCommand.class,
								sf -> sf.<AddStatisticalProgramVersionCommand>handle(
										(p, h) -> statisticalProgramCommandService.addStatisticalProgramVersion(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processDocumentationQueryIntegrationFlow(
			final ProcessDocumentationQueryService processDocumentationQueryService) {
		return IntegrationFlows.from(processDocumentationQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetProcessDocumentationQuery.class,
										sf -> sf.<GetProcessDocumentationQuery>handle(
												(p, h) -> processDocumentationQueryService.getProcessDocumentation(p)))
								.subFlowMapping(GetProcessDocumentationsQuery.class,
										sf -> sf.<GetProcessDocumentationsQuery>handle((p,
												h) -> processDocumentationQueryService.getProcessDocumentations(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processDocumentationCommandIntegrationFlow(
			final ProcessDocumentationCommandService processDocumentationCommandService) {
		return IntegrationFlows.from(processDocumentationCommandInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(CreateProcessDocumentationCommand.class,
										sf -> sf.<CreateProcessDocumentationCommand>handle((p,
												h) -> processDocumentationCommandService.createProcessDocumentation(p)))
								.subFlowMapping(UpdateProcessDocumentationCommand.class,
										sf -> sf.<UpdateProcessDocumentationCommand>handle((p,
												h) -> processDocumentationCommandService.updateProcessDocumentation(p)))
								.subFlowMapping(DeleteProcessDocumentationCommand.class,
										sf -> sf.<DeleteProcessDocumentationCommand>handle((p,
												h) -> processDocumentationCommandService.deleteProcessDocumentation(p)))
								.subFlowMapping(AddProcessDocumentationDocumentCommand.class,
										sf -> sf.<AddProcessDocumentationDocumentCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationDocument(p)))
								.subFlowMapping(AddProcessDocumentationMethodCommand.class,
										sf -> sf.<AddProcessDocumentationMethodCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationMethod(p)))
								.subFlowMapping(com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationInputCommand.class,
										sf -> sf.<com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationInputCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationInput(p)))
								.subFlowMapping(AddProcessDocumentationOutputCommand.class,
										sf -> sf.<AddProcessDocumentationOutputCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationOutput(p)))
								.subFlowMapping(AddProcessDocumentationQualityCommand.class,
										sf -> sf.<AddProcessDocumentationQualityCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationQuality(p)))
								.subFlowMapping(AddProcessDocumentationStandardCommand.class,
										sf -> sf.<AddProcessDocumentationStandardCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationStandard(p)))
								.subFlowMapping(AddProcessDocumentationVersionCommand.class,
										sf -> sf.<AddProcessDocumentationVersionCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationVersion(p)))
								.subFlowMapping(AddProcessDocumentationAdministratorCommand.class,
										sf -> sf.<AddProcessDocumentationAdministratorCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationAdministrator(p)))
								.subFlowMapping(RemoveProcessDocumentationAdministratorCommand.class,
										sf -> sf.<RemoveProcessDocumentationAdministratorCommand>handle(
												(p, h) -> processDocumentationCommandService
														.removeProcessDocumentationAdministrator(p)))
								.subFlowMapping(RemoveProcessDocumentationStandardCommand.class,
										sf -> sf.<RemoveProcessDocumentationStandardCommand>handle(
												(p, h) -> processDocumentationCommandService.
														removeProcessDocumentationStandard(p)))
								.subFlowMapping(RemoveProcessDocumentationMethodCommand.class,
										sf -> sf.<RemoveProcessDocumentationMethodCommand>handle((p, h) ->
												processDocumentationCommandService
														.removeProcessDocumentationMethod(p)))
								.subFlowMapping(AddProcessDocumentationServiceCommand.class,
										sf -> sf.<AddProcessDocumentationServiceCommand>handle(
												(p, h) -> processDocumentationCommandService
														.addProcessDocumentationService(p)))
								.subFlowMapping(RemoveProcessDocumentationServiceCommand.class,
										sf -> sf.<RemoveProcessDocumentationServiceCommand>handle(
												(p, h) -> processDocumentationCommandService
														.removeProcessDocumentationService(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processDocumentQueryIntegrationFlow(
			final ProcessDocumentQueryService processDocumentQueryService) {
		return IntegrationFlows.from(processDocumentQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetProcessDocumentQuery.class,
										sf -> sf.<GetProcessDocumentQuery>handle(
												(p, h) -> processDocumentQueryService.getProcessDocument(p)))
								.subFlowMapping(GetProcessDocumentsQuery.class,
										sf -> sf.<GetProcessDocumentsQuery>handle(
												(p, h) -> processDocumentQueryService.getProcessDocuments(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processDocumentCommandIntegrationFlow(
			final ProcessDocumentCommandService processDocumentCommandService) {
		return IntegrationFlows.from(processDocumentCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
				.subFlowMapping(CreateProcessDocumentCommand.class,
						sf -> sf.<CreateProcessDocumentCommand>handle(
								(p, h) -> processDocumentCommandService.createProcessDocument(p)))
				.subFlowMapping(UpdateProcessDocumentCommand.class,
						sf -> sf.<UpdateProcessDocumentCommand>handle(
								(p, h) -> processDocumentCommandService.updateProcessDocument(p)))
				.subFlowMapping(DeleteProcessDocumentCommand.class, sf -> sf.<DeleteProcessDocumentCommand>handle(
						(p, h) -> processDocumentCommandService.deleteProcessDocument(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processMethodQueryIntegrationFlow(
			final ProcessMethodQueryService processMethodQueryService) {
		return IntegrationFlows.from(processMethodQueryInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
				.subFlowMapping(GetProcessMethodQuery.class,
						sf -> sf.<GetProcessMethodQuery>handle((p, h) -> processMethodQueryService.getProcessMethod(p)))
				.subFlowMapping(GetProcessMethodsQuery.class,
						sf -> sf.<GetProcessMethodsQuery>handle((p, h) -> processMethodQueryService.getProcessMethods(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processMethodCommandIntegrationFlow(
			final ProcessMethodCommandService processMethodCommandService) {
		return IntegrationFlows.from(processMethodCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateProcessMethodCommand.class,
								sf -> sf.<CreateProcessMethodCommand>handle((p, h) -> processMethodCommandService.createProcessMethod(p)))
						.subFlowMapping(UpdateProcessMethodCommand.class,
								sf -> sf.<UpdateProcessMethodCommand>handle((p, h) -> processMethodCommandService.updateProcessMethod(p)))
						.subFlowMapping(DeleteProcessMethodCommand.class,
								sf -> sf.<DeleteProcessMethodCommand>handle((p, h) -> processMethodCommandService.deleteProcessMethod(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processQualityQueryIntegrationFlow(
			final ProcessQualityQueryService processQualityQueryService) {
		return IntegrationFlows.from(processQualityQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetProcessQualityQuery.class,
										sf -> sf.<GetProcessQualityQuery>handle(
												(p, h) -> processQualityQueryService.getProcessQualityQuery(p)))
								.subFlowMapping(GetProcessQualitiesQuery.class,
										sf -> sf.<GetProcessQualitiesQuery>handle(
												(p, h) -> processQualityQueryService.getProcessQualitiesQuery(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processQualityCommandIntegrationFlow(
			final ProcessQualityCommandService processQualityCommandService) {
		return IntegrationFlows.from(processQualityCommandInput())
				.<Object, Class<?>>route(Object::getClass, rs -> rs
						.subFlowMapping(CreateProcessQualityCommand.class,
								sf -> sf.<CreateProcessQualityCommand>handle(
										(p, h) -> processQualityCommandService.createProcessQuality(p)))
						.subFlowMapping(UpdateProcessQualityCommand.class,
								sf -> sf.<UpdateProcessQualityCommand>handle(
										(p, h) -> processQualityCommandService.updateProcessQuality(p)))
						.subFlowMapping(DeleteProcessQualityCommand.class, sf -> sf.<DeleteProcessQualityCommand>handle(
								(p, h) -> processQualityCommandService.deleteProcessQuality(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processInputSpecificationQueryIntegrationFlow(
			final ProcessInputSpecificationQueryService processInputSpecificationQueryService) {
		return IntegrationFlows.from(processInputSpecificationQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetProcessInputSpecificationQuery.class,
										sf -> sf.<GetProcessInputSpecificationQuery>handle(
												(p, h) -> processInputSpecificationQueryService.getProcessInputSpecification(p)))
								.subFlowMapping(GetProcessInputSpecificationsQuery.class,
										sf -> sf.<GetProcessInputSpecificationsQuery>handle(
												(p, h) -> processInputSpecificationQueryService.getProcessInputSpecifications(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processOutputSpecificationQueryIntegrationFlow(
			final ProcessOutputSpecificationQueryService processOutputSpecificationQueryService) {
		return IntegrationFlows.from(processOutputSpecificationQueryInput())
				.<Object, Class<?>>route(
						Object::getClass, rs -> rs
								.subFlowMapping(GetProcessOutputSpecificationQuery.class,
										sf -> sf.<GetProcessOutputSpecificationQuery>handle(
												(p, h) -> processOutputSpecificationQueryService.getProcessOutputSpecification(p)))
								.subFlowMapping(GetProcessOutputSpecificationsQuery.class,
										sf -> sf.<GetProcessOutputSpecificationsQuery>handle(
												(p, h) -> processOutputSpecificationQueryService.getProcessOutputSpecifications(p))))
				.get();
	}
	
	@Bean
	public IntegrationFlow processInputSpecificationCommandIntegrationFlow(
			final ProcessInputSpecificationCommandService processInputSpecificationCommandService) {
		return IntegrationFlows.from(processInputSpecificationCommandInput()).<Object, Class<?>>route(Object::getClass,
				rs -> rs.subFlowMapping(AddProcessDocumentationInputCommand.class,
						sf -> sf.<AddProcessDocumentationInputCommand>handle(
								(p, h) -> processInputSpecificationCommandService.createProcessInputSpecification(p)))
						.subFlowMapping(UpdateProcessDocumentationInputCommand.class,
								sf -> sf.<UpdateProcessDocumentationInputCommand>handle(
										(p, h) -> processInputSpecificationCommandService
												.updateProcessInputSpecification(p)))
						.subFlowMapping(AddProcessDocumentationInputTypeCommand.class,
								sf -> sf.<AddProcessDocumentationInputTypeCommand>handle(
										(p, h) -> processInputSpecificationCommandService
												.addInputSpecificationTypeCommand(p)))
						.subFlowMapping(RemoveProcessDocumentationInputTypeCommand.class,
								sf -> sf.<RemoveProcessDocumentationInputTypeCommand>handle(
										(p, h) -> processInputSpecificationCommandService
												.removeInputSpecificationTypeCommand(p)))
						.subFlowMapping(RemoveProcessDocumentationInputCommand.class,
								sf -> sf.<RemoveProcessDocumentationInputCommand>handle(
										(p, h) -> processInputSpecificationCommandService
												.deleteProcessInputSpecification(p))))
				.get();
	}

	@Bean
	public IntegrationFlow processOutputSpecificationCommandIntegrationFlow(
			final ProcessOutputSpecificationCommandService processOutputSpecificationCommandService) {
		return IntegrationFlows.from(processOutputSpecificationCommandInput()).<Object, Class<?>>route(Object::getClass,
				rs -> rs.subFlowMapping(CreateOutputSpecificationCommand.class,
						sf -> sf.<CreateOutputSpecificationCommand>handle(
								(p, h) -> processOutputSpecificationCommandService.createProcessOutputSpecification(p)))
						.subFlowMapping(UpdateOutputSpecificationCommand.class,
								sf -> sf.<UpdateOutputSpecificationCommand>handle(
										(p, h) -> processOutputSpecificationCommandService
												.updateProcessOutputSpecification(p)))
						.subFlowMapping(AddOutputSpecificationTypeCommand.class,
								sf -> sf.<AddOutputSpecificationTypeCommand>handle(
										(p, h) -> processOutputSpecificationCommandService
												.addOutputSpecificationTypeCommand(p)))
						.subFlowMapping(RemoveOutputSpecificationTypeCommand.class,
								sf -> sf.<RemoveOutputSpecificationTypeCommand>handle(
										(p, h) -> processOutputSpecificationCommandService
												.removeOutputSpecificationTypeCommand(p)))
						.subFlowMapping(DeleteOutputSpecificationCommand.class,
								sf -> sf.<DeleteOutputSpecificationCommand>handle(
										(p, h) -> processOutputSpecificationCommandService
												.deleteProcessOutputSpecification(p))))
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

	@Bean(name = Channels.BUSINESS_SERVICE_QUERY_INPUT)
	public MessageChannel businessServiceQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.BUSINESS_SERVICE_COMMAND_INPUT)
	public MessageChannel businessServiceCommandInput() {
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

	@Bean(name = Channels.PROCESS_DOCUMENT_QUERY_INPUT)
	public MessageChannel processDocumentQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_DOCUMENT_COMMAND_INPUT)
	public MessageChannel processDocumentCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_METHOD_QUERY_INPUT)
	public MessageChannel processMethodQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_METHOD_COMMAND_INPUT)
	public MessageChannel processMethodCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_QUALITY_QUERY_INPUT)
	public MessageChannel processQualityQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_QUALITY_COMMAND_INPUT)
	public MessageChannel processQualityCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_INPUT_SPECIFICATION_QUERY_INPUT)
	public MessageChannel processInputSpecificationQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT)
	public MessageChannel processInputSpecificationCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_OUTPUT_SPECIFICATION_QUERY_INPUT)
	public MessageChannel processOutputSpecificationQueryInput() {
		return new DirectChannel();
	}

	@Bean(name = Channels.PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT)
	public MessageChannel processOutputSpecificationCommandInput() {
		return new DirectChannel();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		return Pollers.fixedRate(500).get();
	}
}
