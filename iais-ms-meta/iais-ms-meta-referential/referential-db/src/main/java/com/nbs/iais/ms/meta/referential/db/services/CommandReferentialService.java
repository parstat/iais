package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramAdministratorCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramStandardCommand;
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

    public CreateStatisticalProgramCommand createStatisticalProgram(final CreateStatisticalProgramCommand command) {
            final StatisticalProgramEntity sp =
                    statisticalProgramRepository.save(CommandTranslator.translate(command));
        Translator.translate(sp, command.getLanguage()).ifPresent(command.getEvent()::setData);
        return command;
    }

    public AddStatisticalProgramAdministratorCommand addStatisticalProgramAdministrator(
            final AddStatisticalProgramAdministratorCommand command) {
        statisticalProgramRepository.findById(command.getStatisticalProgram())
                .ifPresent(sp -> agentRepository.findById(command.getAgent()).ifPresent(agent -> {
            final AgentInRole agentInRole = new AgentInRoleEntity();
            agentInRole.setRole(command.getRole());
            agentInRole.setAgent(agent);
            sp.getAdministrators().add(agentInRole);
            sp.setEditor(command.getAccountId());
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

}
