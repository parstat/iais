package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
import com.nbs.iais.ms.meta.referential.db.repositories.*;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import javax.transaction.Transactional;

@Service
public class CommandReferentialService {

	private final static Logger LOG = LogManager.getLogger(CommandReferentialService.class);

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
	 *         dto in the evnet
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
	 * @throws EntityException        when the command includes a parent that can
	 *                                not be found
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
	 * @throws AGENT_NOT_FOUND        when the agent can  not be found
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
	 * @return CreateStatisticalStandardCommand including the dto of StatisticalStandard in the event
	 * @throws EntityException        when the command includes a parent that can
	 *                                not be found
	 */
	public CreateStatisticalStandardCommand createStatisticalStandard(final CreateStatisticalStandardCommand command)
			throws AuthorizationException, EntityException {

		final StatisticalStandardReferenceEntity standardEntity = statisticalStandardReferenceRepository.save(CommandTranslator.translate(command));
		
		if (command.getAdministrativeDetailsId() != null) {
			administrativeDetailsRepository.findById(command.getAdministrativeDetailsId()).ifPresentOrElse(admindet -> {
				standardEntity.setAdministrativeDetails(admindet);
				statisticalStandardReferenceRepository.save(standardEntity);
			}, () -> {
				throw new EntityException(ExceptionCodes.ADMINISTRATIVE_DETAILS_NOT_FOUND);
			});
		}

	   Translator.translate(standardEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
		return command;
	}

	/** FIXME Francesco
	 * Method to update an agent usually to add name and description in other
	 * languages
	 * 
	 * @param command to execute
	 * @return UpdateAgentCommand including the dto of updated agent in the event
	 */
	public UpdateStatisticalStandardCommand updateStatisticalStandard(final UpdateStatisticalStandardCommand command) throws AuthorizationException {
             //TODO   
			return command;
	}

	/** FIXME Francesco
	 * Method to delete an agent
	 * 
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws AGENT_NOT_FOUND        when the agent can  not be found
	 */
	
	public DeleteStatisticalStandardCommand deleteStatisticalStandard(final DeleteStatisticalStandardCommand command) throws AuthorizationException {
         //TODO
		return command;
	}
	
	

}
