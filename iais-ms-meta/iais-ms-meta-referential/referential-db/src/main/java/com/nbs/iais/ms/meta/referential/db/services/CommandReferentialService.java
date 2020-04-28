package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
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

    public CreateStatisticalProgramCommand createStatisticalProgram(final CreateStatisticalProgramCommand command) {
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

    public AddStatisticalProgramAdministratorCommand addStatisticalProgramAdministrator(
            final AddStatisticalProgramAdministratorCommand command) {

        final Long accountId = JWT.decode(command.getJwt()).getClaim("user").asLong();

        statisticalProgramRepository.findById(command.getStatisticalProgram())
                .ifPresent(sp -> agentRepository.findById(command.getAgent()).ifPresent(agent -> {
            addAdministrator(sp, agent, command.getRole());
            sp.setEditor(accountId);
            sp.setLastModifiedTimestamp(Instant.now());
            Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
                    .ifPresent(command.getEvent()::setData);
        }));

        return command;
    }

    public AddStatisticalProgramLegislativeReferenceCommand addStatisticalProgramLegislativeReference(
            final AddStatisticalProgramLegislativeReferenceCommand command) {
        statisticalProgramRepository.findById(command.getStatisticalProgram()).ifPresent(sp ->
                legislativeReferenceRepository.findById(command.getLegislativeReference()).ifPresent(lr -> {
                            sp.getLegislativeReference().add(lr);
                            Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
                                    .ifPresent(command.getEvent()::setData);
                        }));
        return command;
    }

    public AddStatisticalProgramStandardCommand addStatisticalProgramStandard(
            final AddStatisticalProgramStandardCommand command) {
        statisticalProgramRepository.findById(command.getStatisticalProgram()).ifPresent(sp ->
               statisticalStandardReferenceRepository.findById(command.getStatisticalStandardReference()).ifPresent(sr -> {
                   sp.getStatisticalStandardReferences().add(sr);
                   Translator.translate(statisticalProgramRepository.save(sp), command.getLanguage())
                           .ifPresent(command.getEvent()::setData);
               }));

        return command;
    }


    public CreateBusinessFunctionCommand createBusinessFunction(final CreateBusinessFunctionCommand command) throws AuthorizationException {

        if(AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
            throw new AuthorizationException("You have no permission to perform this operation", ExceptionCodes.NO_PERMISSION);
        }
        final BusinessFunctionEntity businessFunctionEntity = CommandTranslator.translate(command);

        Translator.translate(businessFunctionRepository.save(businessFunctionEntity), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;
    }

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
