package com.nbs.iais.ms.meta.referential.db.services;

import java.time.Instant;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationOutputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.CreateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.DeleteProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.UpdateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramAdministratorCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.DeleteStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentInRoleEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessMethodEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessOutputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessQualityEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AdministrativeDetailsRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentInRoleRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
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
public class CommandReferentialService {

	private final static Logger LOG = LoggerFactory.getLogger(CommandReferentialService.class);

	@Autowired
	private StatisticalProgramRepository statisticalProgramRepository;

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private LegislativeReferenceRepository legislativeReferenceRepository;

	@Autowired
	private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;

	@Autowired
	private BusinessFunctionRepository businessFunctionRepository;

	@Autowired
	private AgentInRoleRepository agentInRoleRepository;

	@Autowired
	AdministrativeDetailsRepository administrativeDetailsRepository;
	
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
	 * Method to create a statistical program
	 * 
	 * @param command to execute
	 * @return CreateStatisticalProgramCommand including the statistical program dto
	 *         in the event
	 * @throws EntityException when at least one version of statistical program
	 *                         exists
	 */
	public CreateStatisticalProgramCommand createStatisticalProgram(final CreateStatisticalProgramCommand command)
			throws EntityException {

		if (statisticalProgramRepository.existsByLocalId(command.getLocalId())) {
			throw new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_EXISTS);
		}

		final StatisticalProgramEntity sp = statisticalProgramRepository.save(CommandTranslator.translate(command));

		if (command.getOwner() != null) {
			agentRepository.findById(command.getOwner()).ifPresent(agent -> {
				addAdministrator(sp, agent, RoleType.OWNER);
				statisticalProgramRepository.save(sp);
			});
		}

		if (command.getMaintainer() != null) {
			agentRepository.findById(command.getMaintainer()).ifPresent(agent -> {
				addAdministrator(sp, agent, RoleType.MAINTAINER);
				statisticalProgramRepository.save(sp);
			});
		}

		if (command.getContact() != null) {
			agentRepository.findById(command.getContact()).ifPresent(agent -> {
				addAdministrator(sp, agent, RoleType.CONTACT);
				statisticalProgramRepository.save(sp);
			});
		}

		Translator.translate(sp, command.getLanguage()).ifPresent(command.getEvent()::setData);

		return command;
	}

	private void addAdministrator(final StatisticalProgramEntity sp, final AgentEntity agent, final RoleType type) {
		agentInRoleRepository.findByAgentAndRole(agent, type)
				.ifPresentOrElse(agentInRole -> sp.getAdministrators().add(agentInRole), () -> {
					final AgentInRoleEntity agentInRole = new AgentInRoleEntity();
					agentInRole.setAgent(agent);
					agentInRole.setRole(type);

					sp.getAdministrators().add(agentInRoleRepository.save(agentInRole));
				});

	}

	/**
	 * Method to add a statistical program version
	 * 
	 * @param command to execute
	 * @return AddStatisticalProgramVersionCommand
	 * @throws EntityException when new version exits or previous version does not
	 *                         exist
	 */
	public AddStatisticalProgramVersionCommand addStatisticalProgramVersion(
			final AddStatisticalProgramVersionCommand command) throws EntityException {

		final StatisticalProgramEntity previousVersion = statisticalProgramRepository
				.findByLocalIdAndVersion(command.getLocalId(), command.getPreviousVersion())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		if (statisticalProgramRepository.existsByLocalIdAndVersion(command.getLocalId(), command.getVersion())) {
			throw new EntityException(ExceptionCodes.VERSION_EXIST);
		}

		final StatisticalProgramEntity sp = statisticalProgramRepository
				.save(CommandTranslator.translate(command, previousVersion));

		if (command.getOwner() != null) {
			agentRepository.findById(command.getOwner()).ifPresent(agent -> {
				addAdministrator(sp, agent, RoleType.OWNER);
				statisticalProgramRepository.save(sp);
			});
		}

		if (command.getMaintainer() != null) {
			agentRepository.findById(command.getMaintainer()).ifPresent(agent -> {
				addAdministrator(sp, agent, RoleType.MAINTAINER);
				statisticalProgramRepository.save(sp);
			});
		}

		if (command.getContact() != null) {
			agentRepository.findById(command.getContact()).ifPresent(agent -> {
				addAdministrator(sp, agent, RoleType.CONTACT);
				statisticalProgramRepository.save(sp);
			});
		}

		Translator.translate(sp, command.getLanguage()).ifPresent(command.getEvent()::setData);

		return command;

	}

	/**
	 * Method to update a statistical program by id
	 * 
	 * @param command to execute
	 * @return UpdateStatisticalProgramCommand including the updated dto
	 * @throws EntityException when statistical program not found
	 */
	public UpdateStatisticalProgramCommand updateStatisticalProgram(final UpdateStatisticalProgramCommand command)
			throws EntityException {

		final StatisticalProgramEntity sp = statisticalProgramRepository.findById(command.getId())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		CommandTranslator.translate(command, sp);

		auditingChanges(sp, command.getJwt());

		Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;

	}

	/**
	 * Method to delete a statistical program by id
	 * @param command the command to execute
	 * @return DeleteStatisticalProgramCommand including the DTOBoolean
	 * @throws AuthorizationException when the user has no rights to delete
	 */
	public DeleteStatisticalProgramCommand deleteStatisticalProgram(final DeleteStatisticalProgramCommand command)
			throws AuthorizationException {

		final AccountRole role = AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString());

		if (role == AccountRole.USER) {
			throw new AuthorizationException(ExceptionCodes.NOT_AUTHORIZED);
		}

		try {
			statisticalProgramRepository.deleteById(command.getId());
		} catch (Exception e) {
			LOG.debug("Error deleting statistical program: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}

	/**
	 * Method to add agentInRole for statistical program (to add an administrator)
	 * 
	 * @param command to execute
	 * @return AddStatisticalProgramAdministratorCommand and statistical program dto
	 *         in the event
	 * @throws EntityException when agent or statistical program not found
	 */
	public AddStatisticalProgramAdministratorCommand addStatisticalProgramAdministrator(
			final AddStatisticalProgramAdministratorCommand command) throws EntityException {

		final StatisticalProgramEntity sp = statisticalProgramRepository.findById(command.getStatisticalProgram())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		final AgentEntity agent = agentRepository.findById(command.getAgent())
				.orElseThrow(() -> new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

		addAdministrator(sp, agent, command.getRole());
		auditingChanges(sp, command.getJwt());

		Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to add a legislative reference to the statistical program
	 * 
	 * @param command to execute
	 * @return AddStatisticalProgramLegislativeReferenceCommand with statistical
	 *         program dto in the event
	 * @throws EntityException when statistical program or legislative reference
	 *                         does not exist
	 */
	public AddStatisticalProgramLegislativeReferenceCommand addStatisticalProgramLegislativeReference(
			final AddStatisticalProgramLegislativeReferenceCommand command) throws EntityException {

		final StatisticalProgramEntity sp = statisticalProgramRepository.findById(command.getStatisticalProgram())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		final LegislativeReferenceEntity lr = legislativeReferenceRepository.findById(command.getLegislativeReference())
				.orElseThrow(() -> new EntityException(ExceptionCodes.LEGISLATIVE_REFERENCE_NOT_FOUND));

		sp.getLegislativeReference().add(lr);
		auditingChanges(sp, command.getJwt());

		// save and translate to dto
		Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to add a statistical standard to statistical program
	 * 
	 * @param command to execute
	 * @return AddStatisticalProgramStandardCommand including statistical program
	 *         dto in the event
	 * @throws EntityException when statistical program or statistical standard not
	 *                         found
	 */
	public AddStatisticalProgramStandardCommand addStatisticalProgramStandard(
			final AddStatisticalProgramStandardCommand command) throws EntityException {
		final StatisticalProgramEntity sp = statisticalProgramRepository.findById(command.getStatisticalProgram())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		final StatisticalStandardReferenceEntity sr = statisticalStandardReferenceRepository
				.findById(command.getStatisticalStandardReference())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

		sp.getStatisticalStandardReferences().add(sr);
		auditingChanges(sp, command.getJwt());

		Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	private void auditingChanges(StatisticalProgramEntity sp, String jwt) {
		final Long accountId = JWT.decode(jwt).getClaim("user").asLong();
		sp.setEditor(accountId);
		sp.setLastModifiedTimestamp(Instant.now());
	}

	/**
	 * Method to create a business function only ADMIN and ROOT users allowed
	 * 
	 * @param command to execute
	 * @return command including the created business function in the event
	 * @throws AuthorizationException when the request is from a user that is not
	 *                                ADMIN or ROOT
	 * @throws EntityException        when the business function already exists
	 */
	public CreateBusinessFunctionCommand createBusinessFunction(final CreateBusinessFunctionCommand command)
			throws AuthorizationException, EntityException {

		// check if user has permission (has role ADMIN or ROOT)
		if (AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
			throw new AuthorizationException(ExceptionCodes.NO_PERMISSION);
		}

		// check if business function already exists
		if (businessFunctionRepository.findByLocalIdAndVersion(command.getLocalId(), command.getVersion())
				.isPresent()) {
			throw new EntityException(ExceptionCodes.BUSINESS_FUNCTION_EXISTS);
		}

		final BusinessFunctionEntity businessFunctionEntity = CommandTranslator.translate(command);

		Translator.translate(businessFunctionRepository.save(businessFunctionEntity), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to update a business function usually to add name and description in
	 * other languages
	 * 
	 * @param command to execute
	 * @return UpdateBusinessFunctionCommand including the dto of updated business
	 *         function in the event
	 * @throws AuthorizationException when user is not an ADMIN or ROOT
	 */
	public UpdateBusinessFunctionCommand updateBusinessFunction(final UpdateBusinessFunctionCommand command)
			throws AuthorizationException {

		if (AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
			throw new AuthorizationException("You have no permission to perform this operation",
					ExceptionCodes.NO_PERMISSION);
		}
		if (command.getId() != null) {
			businessFunctionRepository.findById(command.getId()).ifPresent(bf -> {
				CommandTranslator.translate(command, bf);
				Translator.translate(businessFunctionRepository.save(bf), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			});

		}
		return command;
	}

	/**
	 * Method to create an agent
	 * 
	 * @param command to execute
	 * @return CreateAgentCommand including the dto of agent in the event
	 * @throws EntityException when the command includes a parent that can not be
	 *                         found
	 */
	public CreateAgentCommand createAgent(final CreateAgentCommand command)
			throws AuthorizationException, EntityException {

		final AgentEntity agentEntity = agentRepository.save(CommandTranslator.translate(command));

		if (command.getParent() != null) {
			agentRepository.findById(command.getParent()).ifPresentOrElse(parent -> {
				agentEntity.setParent(parent);
				agentRepository.save(agentEntity);
			}, () -> {
				throw new EntityException(ExceptionCodes.AGENT_PARENT_NOT_FOUND);
			});
		}

		Translator.translate(agentEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
		return command;
	}

	/**
	 * Method to update an agent usually to add name and description in other
	 * languages
	 * 
	 * @param command to execute
	 * @return UpdateAgentCommand including the dto of updated agent in the event
	 */
	public UpdateAgentCommand updateAgent(final UpdateAgentCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			agentRepository.findById(command.getId()).ifPresentOrElse(agent -> {
				CommandTranslator.translate(command, agent);

				if (command.getParent() != null) {
					if (command.getId().equals(command.getParent())) {
						throw new EntityException(ExceptionCodes.AGENT_PARENT_NOT_APPLICABLE);
					}

					agentRepository.findById(command.getParent()).ifPresentOrElse(agent::setParent, () -> {
						throw new EntityException(ExceptionCodes.AGENT_PARENT_NOT_FOUND);
					});
				}

				Translator.translate(agentRepository.save(agent), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.AGENT_NOT_FOUND);
			});

		}
		return command;
	}

	/**
	 * Method to delete an agent
	 * 
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws AuthorizationException AGENT_NOT_FOUND when the agent can not be found
	 */
	@Transactional
	public DeleteAgentCommand deleteAgent(final DeleteAgentCommand command) throws AuthorizationException {

		try {
			final AgentEntity agentToDelete = agentRepository.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

			// remove Parent relationship
			agentRepository.findByParent(agentToDelete).forEach(child -> {
				child.setParent(null);
				agentRepository.save(child);
			});

			agentRepository.delete(agentToDelete);
		} catch (Exception e) {
			LOG.debug("Error deleting agent: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}

	/**
	 * Method to create an Statistical Standard
	 * 
	 * @param command to execute
	 * @return CreateStatisticalStandardCommand including the dto of
	 *         StatisticalStandard in the event
	 * @throws EntityException when the command includes a parent that can not be
	 *                         found
	 */
	public CreateStatisticalStandardCommand createStatisticalStandard(final CreateStatisticalStandardCommand command)
			throws AuthorizationException, EntityException {

		final StatisticalStandardReferenceEntity standardEntity = statisticalStandardReferenceRepository
				.save(CommandTranslator.translate(command));

		Translator.translate(standardEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
		return command;
	}

	/**
	 * Method to update a StatisticalStandard
	 * 
	 * @param command to execute
	 * @return UpdateStatisticalStandardCommand including the dto of updated entity
	 *         in the event
	 */
	public UpdateStatisticalStandardCommand updateStatisticalStandard(final UpdateStatisticalStandardCommand command)
			throws AuthorizationException {

		if (command.getId() != null) {
			statisticalStandardReferenceRepository.findById(command.getId()).ifPresentOrElse(standard -> {
				CommandTranslator.translate(command, standard);

				Translator.translate(statisticalStandardReferenceRepository.save(standard), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to delete a Statistical Standard
	 * 
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws EntityException STANDARD_REFERENCE_NOT_FOUND when the Statistical Standard can not be
	 *                                      found
	 */

	public DeleteStatisticalStandardCommand deleteStatisticalStandard(final DeleteStatisticalStandardCommand command)
			throws AuthorizationException, EntityException {

		try {
			final StatisticalStandardReferenceEntity standardEntity = statisticalStandardReferenceRepository
					.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

			statisticalStandardReferenceRepository.delete(standardEntity);
		} catch (Exception e) {
			LOG.debug("Error deleting agent: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}

	/**
	 * Method to create an legislative reference
	 * 
	 * @param command to execute
	 * @return CreateLegislativeReferenceCommand including the dto of  LegislativeReference in the event
	
	 */
	public CreateLegislativeReferenceCommand createLegislativeReference(final CreateLegislativeReferenceCommand command)
			throws AuthorizationException, EntityException {

		final LegislativeReferenceEntity referenceEntity = legislativeReferenceRepository
				.save(CommandTranslator.translate(command));

		Translator.translate(referenceEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
		return command;
	}

	/**
	 * Method to update a LegislativeReference
	 * 
	 * @param command to execute
	 * @return UpdateLegislativeReferenceCommand including the dto of updated entity
	 *         in the event
	 * @throws EntityException LEGISLATIVE_REFERENCE_NOT_FOUND when the legislative reference can not be found
	 */
	public UpdateLegislativeReferenceCommand updateLegislativeReference(final UpdateLegislativeReferenceCommand command)
			throws AuthorizationException, EntityException {

		if (command.getId() != null) {
			legislativeReferenceRepository.findById(command.getId()).ifPresentOrElse(reference -> {
				CommandTranslator.translate(command, reference);

				Translator.translate(legislativeReferenceRepository.save(reference), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.LEGISLATIVE_REFERENCE_NOT_FOUND);
			});

		}

		return command;
	}

	/** 
	 * Method to delete an legislative reference
	 * 
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws EntityException LEGISLATIVE_REFERENCE_NOT_FOUND when the legislative reference can not be found
	 */

	public DeleteLegislativeReferenceCommand deleteLegislativeReference(final DeleteLegislativeReferenceCommand command)
			throws AuthorizationException, EntityException {

		try {
			final LegislativeReferenceEntity referenceEntity = legislativeReferenceRepository
					.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.LEGISLATIVE_REFERENCE_NOT_FOUND));

			legislativeReferenceRepository.delete(referenceEntity);
		} catch (Exception e) {
			LOG.debug("Error deleting agent: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}

	/**
	 * Method to create an process documentation
	 * 
	 * @param command to execute
	 * @return CreateProcessDocumentationCommand including the dto of process documentation in the event
	 * @throws EntityException when the command includes a parent that can not be
	 *                         found
	 */
	public CreateProcessDocumentationCommand createProcessDocumentation(final CreateProcessDocumentationCommand command)
			throws AuthorizationException, EntityException {

		final BusinessFunctionEntity businessFunction = businessFunctionRepository
				.findById(command.getBusinessFunction()).orElseThrow(() ->
						new EntityException(ExceptionCodes.BUSINESS_FUNCTION_NOT_FOUND));

		final StatisticalProgramEntity statisticalProgram = statisticalProgramRepository
				.findById(command.getStatisticalProgram()).orElseThrow(() ->
						new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

		if(processDocumentationRepository.existsByStatisticalProgramAndBusinessFunction(statisticalProgram,
				businessFunction)) {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_EXISTS);
		}

		final ProcessDocumentationEntity processDocumentationEntity = CommandTranslator.translate(command);

		processDocumentationEntity.setBusinessFunction(businessFunction);
		processDocumentationEntity.setStatisticalProgram(statisticalProgram);

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
				.ifPresentOrElse(agentInRole -> pd.getAdministrators().add(agentInRole), () -> {
					final AgentInRoleEntity agentInRole = new AgentInRoleEntity();
					agentInRole.setAgent(agent);
					agentInRole.setRole(type);

					pd.getAdministrators().add(agentInRoleRepository.save(agentInRole));
				});

	}
	
	
	/**
	 * Method to update an process documentation usually to add name and description in other
	 * languages
	 * 
	 * @param command to execute
	 * @return UpdateProcessDocumentationCommand including the dto of updated process documentation in the event
	 */
	public UpdateProcessDocumentationCommand updateProcessDocumentation(final UpdateProcessDocumentationCommand command) throws AuthorizationException {

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
	 * @throws AuthorizationException AGENT_NOT_FOUND when the process documentation can not be found
	 */
	@Transactional
	public DeleteProcessDocumentationCommand deleteProcessDocumentation(final DeleteProcessDocumentationCommand command) throws AuthorizationException {

		try {
			final ProcessDocumentationEntity processDocumentationToDelete = processDocumentationRepository.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_EXISTS));

			 //Todo Relationship

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
	 * @return AddProcessDocumentationStandardCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or statistical standard not
	 *                         found
	 */
	public AddProcessDocumentationStandardCommand addProcessDocumentationStandardCommand(
			final AddProcessDocumentationStandardCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final StatisticalStandardReferenceEntity sr = statisticalStandardReferenceRepository
				.findById(command.getStatisticalStandardReference())
				.orElseThrow(() -> new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

		pd.getStandardsUsed().add(sr);
	 	Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}
	
	/**
	 * Method to add a process document to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or  process document not
	 *                         found
	 */
	public AddProcessDocumentationDocumentCommand addProcessDocumentationDocumentCommand(
			final AddProcessDocumentationDocumentCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessDocumentEntity pDocument = processDocumentRepository
				.findById(command.getProcessDocument())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENT_NOT_FOUND));

		pd.getProcessDocuments().add(pDocument);
	 	Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	/**
	 * Method to add a input specification to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationInputCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or input specification not
	 *                         found
	 */
	public AddProcessDocumentationInputCommand addProcessDocumentationInputCommand(
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
	 * Method to add a output specification to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationOutputCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or output specification not
	 *                         found
	 */
	public AddProcessDocumentationOutputCommand addProcessDocumentationOutputCommand(
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
	 * @return AddProcessDocumentationDocumentCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or process method not
	 *                         found
	 */
	public AddProcessDocumentationMethodCommand addProcessDocumentationMethodCommand(
			final AddProcessDocumentationMethodCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessMethodEntity method = processMethodRepository
				.findById(command.getProcessMethod())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_METHOD_NOT_FOUND));

		pd.getProcessMethods().add(method);
	 	Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}

	

	/**
	 * Quality to add a process quality to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or process quality not
	 *                         found
	 */
	public AddProcessDocumentationQualityCommand addProcessDocumentationQualityCommand(
			final AddProcessDocumentationQualityCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		final ProcessQualityEntity quality = processQualityRepository
				.findById(command.getProcessQuality())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_QUALITY_NOT_FOUND));

		pd.getProcessQualityIndicators().add(quality);
	 	Translator.translate(processDocumentationRepository.save(pd), command.getLanguage())
				.ifPresent(command.getEvent()::setData);

		return command;
	}
	

	/** FIXME
	 * Version to add a process version to process documentation
	 * 
	 * @param command to execute
	 * @return AddProcessDocumentationDocumentCommand including  process documentation
	 *         dto in the event
	 * @throws EntityException when  process documentation or process version not
	 *                         found
	 */
	public AddProcessDocumentationVersionCommand addProcessDocumentationVersionCommand(
			final AddProcessDocumentationVersionCommand command) throws EntityException {
		final ProcessDocumentationEntity pd = processDocumentationRepository.findById(command.getProcessDocumentation())
				.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND));

		//TODO

		return command;
	}
	
}
