package com.nbs.iais.ms.meta.referential.db.services;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
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
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentInRoleEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentInRoleRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

@Service
public class StatisticalProgramCommandService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private StatisticalProgramRepository statisticalProgramRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentInRoleRepository agentInRoleRepository;

    @Autowired
    private LegislativeReferenceRepository legislativeReferenceRepository;

    @Autowired
    private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;

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
     * Method to remove an administrator from statistical survey
     * @param command The command to execute
     * @return RemoveStatisticalProgramAdministratorCommand with true if removed
     */
    public RemoveStatisticalProgramAdministratorCommand removeStatisticalProgramAdministrator(
            final RemoveStatisticalProgramAdministratorCommand command) {

        statisticalProgramRepository.findById(command.getStatisticalProgramId()).ifPresentOrElse(statisticalProgram -> {

            final AgentEntity agent = agentRepository.findById(command.getAgentId()).orElseThrow(() ->
                    new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

            final AgentInRole agentInRole = agentInRoleRepository.findByAgentAndRole(agent, command.getRoleType()).orElseThrow(() ->
                    new EntityException(ExceptionCodes.ROLE_NOT_FOUND));

            statisticalProgram.getAdministrators().remove(agentInRole);
            statisticalProgramRepository.save(statisticalProgram);
            command.getEvent().setData(DTOBoolean.TRUE);

        }, () -> {
            throw  new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND);
        });

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
     * Method to remove a legislative reference from statistical program
     * @param command The command to execute
     * @return RemoveStatisticalProgramLegislativeReferenceCommand
     */
    public RemoveStatisticalProgramLegislativeReferenceCommand removeStatisticalProgramLegislativeReference(
            final RemoveStatisticalProgramLegislativeReferenceCommand command) {

        statisticalProgramRepository.findById(command.getStatisticalProgramId()).ifPresentOrElse(statisticalProgram -> {
            final LegislativeReferenceEntity legislativeReference = legislativeReferenceRepository.findById(
                    command.getLegislativeReferenceId()).orElseThrow(() ->
                    new EntityException(ExceptionCodes.LEGISLATIVE_REFERENCE_NOT_FOUND));

            statisticalProgram.getLegislativeReference().remove(legislativeReference);

            statisticalProgramRepository.save(statisticalProgram);
            command.getEvent().setData(DTOBoolean.TRUE);
        }, () -> {
            throw new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND);
        });

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

    /**
     * Method to remove statistical standard from statistical program
     * @param command The command to execute
     * @return RemoveStatisticalProgramStandardCommand with value true if success
     */
    public RemoveStatisticalProgramStandardCommand removeStatisticalProgramStandard(
            final RemoveStatisticalProgramStandardCommand command) {

        statisticalProgramRepository.findById(command.getStatisticalProgramId()).ifPresentOrElse(statisticalProgram -> {

            final StatisticalStandardReferenceEntity statisticalStandardReference =
                    statisticalStandardReferenceRepository.findById(command.getStatisticalStandardId()).orElseThrow(()
                            -> new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

            statisticalProgram.getStatisticalStandardReferences().remove(statisticalStandardReference);
            statisticalProgramRepository.save(statisticalProgram);
            command.getEvent().setData(DTOBoolean.TRUE);

        }, () -> {

            throw new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND);

        });

        return command;
    }


    private void auditingChanges(StatisticalProgramEntity sp, String jwt) {
        final Long accountId = JWT.decode(jwt).getClaim("user").asLong();
        sp.setEditor(accountId);
        sp.setLastModifiedTimestamp(Instant.now());
    }

}
