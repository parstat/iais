package com.nbs.iais.ms.meta.referential.db.utils;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;

import java.time.Instant;
import java.time.LocalDateTime;

public class CommandTranslator {

    public static StatisticalProgramEntity translate(final CreateStatisticalProgramCommand command) {

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();

        if(StringTools.isNotEmpty(command.getName())) {
            statisticalProgram.setName(command.getName(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getAcronym())) {
            statisticalProgram.setAcronym(command.getAcronym(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getDescription())) {
            statisticalProgram.setDescription(command.getDescription(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getLocalId())) {
            statisticalProgram.setLocalId(command.getLocalId());
        }

        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setBudget(command.getBudget());
        statisticalProgram.setSourceOfFunding(command.getSourceOfFunding());
        statisticalProgram.setDateEnded(command.getDateEnded());
        statisticalProgram.setDateInitiated(command.getDateInitiated());

        statisticalProgram.setVersion("1.0");
        statisticalProgram.setVersionDate(LocalDateTime.now());
        statisticalProgram.setVersionRationale("Newly created");
        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setCreator(JWT.decode(command.getJwt()).getClaim("user").asLong());
        statisticalProgram.setCreatedTimestamp(Instant.now());


        return statisticalProgram;
    }
}
