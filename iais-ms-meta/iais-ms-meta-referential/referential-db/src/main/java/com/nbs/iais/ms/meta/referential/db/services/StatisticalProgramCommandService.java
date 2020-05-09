package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.*;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
import com.nbs.iais.ms.meta.referential.db.repositories.*;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

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

}
