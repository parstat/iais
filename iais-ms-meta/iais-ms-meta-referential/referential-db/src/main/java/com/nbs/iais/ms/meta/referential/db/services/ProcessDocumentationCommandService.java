package com.nbs.iais.ms.meta.referential.db.services;

import javax.transaction.Transactional;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentInRoleEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
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
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessInputSpecificationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessMethodRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessOutputSpecificationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessQualityRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

@Service
public class ProcessDocumentationCommandService {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

	@Autowired
	private StatisticalProgramRepository statisticalProgramRepository;

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;

	@Autowired
	private BusinessFunctionRepository businessFunctionRepository;

	@Autowired
	private AgentInRoleRepository agentInRoleRepository;

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;

	@Autowired
	private ProcessDocumentRepository processDocumentRepository;

	@Autowired
	private ProcessInputSpecificationRepository processInputSpecificationRepository;

	@Autowired
	private ProcessOutputSpecificationRepository processOutputSpecificationRepository;

	@Autowired
	private ProcessMethodRepository processMethodRepository;

	@Autowired
	private ProcessQualityRepository processQualityRepository;

	/**
	 * Method to create an process documentation
	 * 
	 * @param command to execute
	 * @return CreateProcessDocumentationCommand including the dto of process
	 *         documentation in the event
	 * @throws EntityException when the command includes a parent that can not be
	 *                         found
	 */
	public CreateProcessDocumentationCommand createProcessDocumentation(final CreateProcessDocumentationCommand command)
			throws AuthorizationException, EntityException {

		final BusinessFunctionEntity businessFunction = businessFunctionRepository
				.findById(command.getBusinessFunction())
				.orElseThrow(() -> new EntityException(ExceptionCodes.BUSINESS_FUNCTION_NOT_FOUND));

		final StatisticalProgramEntity statisticalProgram = statisticalProgramRepository
				.findById(command.getStatisticalProgram())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		if (processDocumentationRepository.existsByStatisticalProgramAndBusinessFunction(statisticalProgram,
				businessFunction)) {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_EXISTS);
		}

		final ProcessDocumentationEntity processDocumentationEntity = CommandTranslator.translate(command);

		processDocumentationEntity.setBusinessFunction(businessFunction);
		processDocumentationEntity.setStatisticalProgram(statisticalProgram);
		if(StringTools.isNotEmpty(command.getNextSubPhase())) {
			businessFunctionRepository.findByLocalIdAndVersion(command.getNextSubPhase(), "5.1")
					.ifPresentOrElse(processDocumentationEntity::setNextBusinessFunction, () -> {
						throw new EntityException(ExceptionCodes.BUSINESS_FUNCTION_NOT_FOUND);
					});
		}

		processDocumentationRepository.save(processDocumentationEntity);

		if (command.getMaintainer() != null) {
			agentRepository.findById(command.getMaintainer()).ifPresent(agent -> {
				addAdministrator(processDocumentationEntity, agent, RoleType.MAINTAINER);
				processDocumentationRepository.save(processDocumentationEntity);
			});
		}
		Translator.translate(processDocumentationEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
		return command;
	}

	private void addAdministrator(final ProcessDocumentationEntity pd, final AgentEntity agent, final RoleType type) {
		agentInRoleRepository.findByAgentAndRole(agent, type)
				.ifPresentOrElse(agentInRole -> {
					if(!pd.getAdministrators().contains(agentInRole)) {
						pd.getAdministrators().add(agentInRole);
					}
				}, () -> {
					final AgentInRoleEntity agentInRole = new AgentInRoleEntity();
					agentInRole.setAgent(agent);
					agentInRole.setRole(type);
					pd.getAdministrators().add(agentInRoleRepository.save(agentInRole));
				});

	}

	/**
	 * Method to update an process documentation usually to add name and description
	 * in other languages
	 * 
	 * @param command to execute
	 * @return UpdateProcessDocumentationCommand including the dto of updated
	 *         process documentation in the event
	 */
	public UpdateProcessDocumentationCommand updateProcessDocumentation(final UpdateProcessDocumentationCommand command)
			throws AuthorizationException {

		if (command.getId() != null) {
			processDocumentationRepository.findById(command.getId()).ifPresentOrElse(processDocumentation -> {
				CommandTranslator.translate(command, processDocumentation);

				// TODO fields
				Translator.translate(processDocumentationRepository.save(processDocumentation), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
			});

		}
		return command;
	}

	/**
	 * Method to delete an process documentation
	 * 
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws AuthorizationException AGENT_NOT_FOUND when the process documentation
	 *                                can not be found
	 */
	@Transactional
	public DeleteProcessDocumentationCommand deleteProcessDocumentation(final DeleteProcessDocumentationCommand command)
			throws AuthorizationException {

		try {
			final ProcessDocumentationEntity processDocumentationToDelete = processDocumentationRepository
					.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_EXISTS));

			// Todo Relationship

			processDocumentationRepository.delete(processDocumentationToDelete);
		} catch (Exception e) {
			LOG.debug("Error deleting process documentation: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}

	/**
	 * Method to add a statistical standard to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationStandardCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or statistical standard
	 *                         not found
	 */
	public AddProcessDocumentationStandardCommand addProcessDocumentationStandard(
			final AddProcessDocumentationStandardCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final StatisticalStandardReferenceEntity sr = statisticalStandardReferenceRepository
				.findById(command.getStatisticalStandardReference())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

		if(!pd.getStandardsUsed().contains(sr)) { //add only if not present
			pd.getStandardsUsed().add(sr);
		}
		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to remove a statistical standard from process documentation
	 *
	 * @param command the command to execute
	 * @return RemoveProcessDocumentationStandardCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or statistical standard
	 *                         not found
	 */
	public RemoveProcessDocumentationStandardCommand removeProcessDocumentationStandard(
			final RemoveProcessDocumentationStandardCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final StatisticalStandardReferenceEntity ss = statisticalStandardReferenceRepository
				.findById(command.getStatisticalStandard())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

		pd.getStandardsUsed().remove(ss);

		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 *  FIXME oneToMany it is better that this command should create also the output
	 * Method to add a process document to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or process document not
	 *                         found
	 */
	public AddProcessDocumentationDocumentCommand addProcessDocumentationDocument(
			final AddProcessDocumentationDocumentCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessDocumentEntity pDocument = processDocumentRepository.findById(command.getProcessDocument())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENT_NOT_FOUND));

		if(!pd.getProcessDocuments().contains(pDocument)) {//added if not present
			pd.getProcessDocuments().add(pDocument);
		}
		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to add a input specification to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationInputCommand including process documentation
	 *         dto in the event
	 * @throws EntityException when process documentation or input specification not
	 *                         found
	 */
	public AddProcessDocumentationInputCommand addProcessDocumentationInput(
			final AddProcessDocumentationInputCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessInputSpecificationEntity input = processInputSpecificationRepository
				.findById(command.getInputSpecification())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION__NOT_FOUND));

		pd.getProcessInputs().add(input);
		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * FIXME oneToMany it is better that this command should create also the output
	 * Method to add a output specification to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationOutputCommand including process documentation
	 *         dto in the event
	 * @throws EntityException when process documentation or output specification
	 *                         not found
	 */
	public AddProcessDocumentationOutputCommand addProcessDocumentationOutput(
			final AddProcessDocumentationOutputCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessOutputSpecificationEntity output = processOutputSpecificationRepository
				.findById(command.getOutputSpecification())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION__NOT_FOUND));

		pd.getProcessOutputs().add(output);
		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to add a process method to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or process method not
	 *                         found
	 */
	public AddProcessDocumentationMethodCommand addProcessDocumentationMethod(
			final AddProcessDocumentationMethodCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessMethodEntity method = processMethodRepository.findById(command.getProcessMethod())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_METHOD_NOT_FOUND));

		if(!pd.getProcessMethods().contains(method)) { //added only if not present
			pd.getProcessMethods().add(method);
		}
		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to remove a process method to process documentation
	 *
	 * @param command to execute
	 * @return RemoveProcessDocumentationMethodCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or process method not
	 *                         found
	 */
	public RemoveProcessDocumentationMethodCommand removeProcessDocumentationMethod(
			final RemoveProcessDocumentationMethodCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessMethodEntity method = processMethodRepository.findById(command.getProcessMethod())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_METHOD_NOT_FOUND));

		pd.getProcessMethods().remove(method);

		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * FIXME This is a oneToMany relation and it is better to create the document directly when this command is executed
	 * Quality to add a process quality to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or process quality not
	 *                         found
	 */
	public AddProcessDocumentationQualityCommand addProcessDocumentationQuality(
			final AddProcessDocumentationQualityCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessQualityEntity quality = processQualityRepository.findById(command.getProcessQuality())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_QUALITY_NOT_FOUND));

		pd.getProcessQualityIndicators().add(quality);
		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Version to add a new process version to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including process
	 *         documentation dto in the event
	 * @throws EntityException when process documentation or process version not
	 *                         found
	 */
	public AddProcessDocumentationVersionCommand addProcessDocumentationVersion(
			final AddProcessDocumentationVersionCommand command) throws EntityException {

		final BusinessFunctionEntity businessFunction = businessFunctionRepository
				.findById(command.getBusinessFunction())
				.orElseThrow(() -> new EntityException(ExceptionCodes.BUSINESS_FUNCTION_NOT_FOUND));

		final StatisticalProgramEntity statisticalProgram = statisticalProgramRepository
				.findById(command.getStatisticalProgram())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		//Getting the current version (latest version)
		final ProcessDocumentationEntity currentVersion = processDocumentationRepository
				.findAllTopByStatisticalProgramAndBusinessFunctionOrderByVersionDateDesc(statisticalProgram,
						businessFunction).orElseThrow(() ->
						new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessDocumentationEntity processDocumentation = CommandTranslator.translate(command,
				currentVersion);

		processDocumentation.setStatisticalProgram(statisticalProgram);
		processDocumentation.setBusinessFunction(businessFunction);

		processDocumentationRepository.save(processDocumentation);

		if (command.getMaintainer() != null) {
			agentRepository.findById(command.getMaintainer()).ifPresent(agent -> {
				addAdministrator(processDocumentation, agent, RoleType.MAINTAINER);
				processDocumentationRepository.save(processDocumentation);
			});
		}
		Translator.translate(processDocumentation, command.getLanguage()).ifPresent(command.getEvent()::setData);
		return command;
	}

	/**
	 * Method to add an administrator to a process documentation
	 * usually the maintainer/responsible division
	 * @param command the command to be executed
	 * @return AddProcessDocumentationAdministratorCommand including the process documentation dto
	 */
	public AddProcessDocumentationAdministratorCommand addProcessDocumentationAdministrator(final AddProcessDocumentationAdministratorCommand command) {

		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final AgentEntity agent = agentRepository.findById(command.getAgent())
				.orElseThrow(() -> new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

		addAdministrator(pd, agent, command.getRole());

		Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	public RemoveProcessDocumentationAdministratorCommand removeProcessDocumentationAdministrator(final RemoveProcessDocumentationAdministratorCommand command) {


		processDocumentationRepository.findById(command.getProcessDocumentation()).ifPresentOrElse(processDocumentation -> {

			final AgentEntity agent = agentRepository.findById(command.getAgent()).orElseThrow(() ->
					new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

			final AgentInRole agentInRole = agentInRoleRepository.findByAgentAndRole(agent, command.getRole()).orElseThrow(() ->
					new EntityException(ExceptionCodes.ROLE_NOT_FOUND));

			processDocumentation.getAdministrators().remove(agentInRole);
			Translator.translate(processDocumentationRepository.save(processDocumentation), command.getLanguage())
					.ifPresent(command.getEvent()::setData);

		}, () -> {
			throw  new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
		});

		return command;
	}

}
