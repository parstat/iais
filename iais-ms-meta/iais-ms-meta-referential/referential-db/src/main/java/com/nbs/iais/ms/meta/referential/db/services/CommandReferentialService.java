package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramAdministratorCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;
import com.nbs.iais.ms.meta.referential.db.repositories.*;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommandReferentialService {

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

    @Autowired
    private BusinessFunctionRepository businessFunctionRepository;

    /**
     * Method to create a statistical program
     * @param command to execute
     * @return CreateStatisticalProgramCommand including the statistical program dto in the event
     * @throws EntityException when at least one version of statistical program exists
     */
    public CreateStatisticalProgramCommand createStatisticalProgram(final CreateStatisticalProgramCommand command) throws EntityException {

        if(statisticalProgramRepository.existsByLocalId(command.getLocalId())) {
            throw new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_EXISTS);
        }

        final StatisticalProgramEntity sp =
                statisticalProgramRepository.save(CommandTranslator.translate(command));

        if(command.getOwner() != null) {
            agentRepository.findById(command.getOwner()).ifPresent(agent -> {
                addAdministrator(sp, agent, RoleType.OWNER);
                statisticalProgramRepository.save(sp);
            });
        }

        if(command.getMaintainer() != null) {
            agentRepository.findById(command.getMaintainer()).ifPresent(agent -> {
                addAdministrator(sp, agent, RoleType.MAINTAINER);
                statisticalProgramRepository.save(sp);
            });
        }

        if(command.getContact() != null) {
            agentRepository.findById(command.getContact()).ifPresent(agent -> {
                addAdministrator(sp, agent, RoleType.CONTACT);
                statisticalProgramRepository.save(sp);
            });
        }

        Translator.translate(sp, command.getLanguage()).ifPresent(command.getEvent()::setData);

        return command;
    }

    private void addAdministrator(final StatisticalProgramEntity sp, final AgentEntity agent, final RoleType type) {
        final AgentInRole agentInRole = new AgentInRoleEntity();
        agentInRole.setAgent(agent);
        agentInRole.setRole(type);
        sp.getAdministrators().add(agentInRole);

    }

    /**
     * Method to add agentInRole for statistical program (to add an administrator)
     * @param command to execute
     * @return AddStatisticalProgramAdministratorCommand and statistical program dto in the event
     * @throws EntityException when agent or statistical program not found
     */
    public AddStatisticalProgramAdministratorCommand addStatisticalProgramAdministrator(
            final AddStatisticalProgramAdministratorCommand command) throws EntityException {

        final StatisticalProgramEntity sp = statisticalProgramRepository
                .findById(command.getStatisticalProgram()).orElseThrow(() ->
                        new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

        final AgentEntity agent = agentRepository.findById(command.getAgent()).orElseThrow(() ->
                new EntityException(ExceptionCodes.AGENT_NOT_FOUND));

        final Long accountId = JWT.decode(command.getJwt()).getClaim("user").asLong();

        addAdministrator(sp, agent, command.getRole());
        sp.setEditor(accountId);
        sp.setLastModifiedTimestamp(Instant.now());

        Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;
    }

    /**
     * Method to add a legislative reference to the statistical program
     * @param command to execute
     * @return AddStatisticalProgramLegislativeReferenceCommand with statistical program dto in the event
     * @throws EntityException when statistical program or legislative reference does not exist
     */
    public AddStatisticalProgramLegislativeReferenceCommand addStatisticalProgramLegislativeReference(
            final AddStatisticalProgramLegislativeReferenceCommand command) throws EntityException {

        final StatisticalProgramEntity sp = statisticalProgramRepository
                .findById(command.getStatisticalProgram()).orElseThrow(() ->
                        new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

        final LegislativeReferenceEntity lr = legislativeReferenceRepository
                .findById(command.getLegislativeReference()).orElseThrow(() ->
                        new EntityException(ExceptionCodes.LEGISLATIVE_REFERENCE_NOT_FOUND));

        Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;
    }

    /**
     * Method to add a statistical standard to statistical program
     * @param command to execute
     * @return AddStatisticalProgramStandardCommand including statistical program dto in the evnet
     * @throws EntityException when statistical program or statistical standard not found
     */
    public AddStatisticalProgramStandardCommand addStatisticalProgramStandard(
            final AddStatisticalProgramStandardCommand command) throws EntityException {
        final StatisticalProgramEntity sp = statisticalProgramRepository.findById(command.getStatisticalProgram())
                .orElseThrow(() -> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));

        final StatisticalStandardReferenceEntity sr = statisticalStandardReferenceRepository
                .findById(command.getStatisticalStandardReference()).orElseThrow(() ->
                        new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND));

        sp.getStatisticalStandardReferences().add(sr);

        Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;
    }


    /**
     * Method to create a business function
     * only ADMIN and ROOT users allowed
     * @param command to execute
     * @return command including the created business function in the event
     * @throws AuthorizationException when the request is from a user that is not ADMIN or ROOT
     * @throws EntityException when the business function already exists
     */
    public CreateBusinessFunctionCommand createBusinessFunction(final CreateBusinessFunctionCommand command) throws AuthorizationException, EntityException {

        //check if user has permission (has role ADMIN or ROOT)
        if(AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
            throw new AuthorizationException(ExceptionCodes.NO_PERMISSION);
        }

        //check if business function already exists
        if(businessFunctionRepository.findByLocalIdAndVersion(command.getLocalId(), command.getVersion()).isPresent()) {
            throw new EntityException(ExceptionCodes.BUSINESS_FUNCTION_EXISTS);
        }

        final BusinessFunctionEntity businessFunctionEntity = CommandTranslator.translate(command);

        Translator.translate(businessFunctionRepository.save(businessFunctionEntity), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;
    }

    /**
     * Method to update a business function
     * usually to add name and description in other languages
     * @param command to execute
     * @return UpdateBusinessFunctionCommand including the dto of updated business function in the event
     * @throws AuthorizationException when user is not an ADMIN or ROOT
     */
    public UpdateBusinessFunctionCommand updateBusinessFunction(final UpdateBusinessFunctionCommand command) throws AuthorizationException{

        if(AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
            throw new AuthorizationException("You have no permission to perform this operation", ExceptionCodes.NO_PERMISSION);
        }

        if(command.getId() != null) {
            businessFunctionRepository.findById(command.getId()).ifPresent(bf -> {
                CommandTranslator.translate(command, bf);
                Translator.translate(businessFunctionRepository.save(bf), command.getLanguage())
                        .ifPresent(command.getEvent()::setData);
            });

        }

        if(StringTools.isNotEmpty(command.getLocalId())) {
            businessFunctionRepository.findByLocalIdAndVersion(command.getLocalId(), command.getVersion())
                    .ifPresent(bf -> {
                        CommandTranslator.translate(command, bf);
                        Translator.translate(businessFunctionRepository.save(bf), command.getLanguage())
                                .ifPresent(command.getEvent()::setData);
                    });
        }

        return command;
    }


    /**
     * Method to create a new Agent
     * @param command to execute
     * @return CreateAgentCommand including the new agent dto in the event
     */
    public CreateAgentCommand createAgent(final CreateAgentCommand command) {
        final AgentEntity agentEntity =
                agentRepository.save(CommandTranslator.translate(command));

        if(command.getParent() != null) {
            agentRepository.findById(command.getParent()).ifPresent(parent -> {
             	agentEntity.setParent(parent);
            	agentRepository.save(agentEntity);
            });
        }

    Translator.translate(agentEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
    return command;
}

}
