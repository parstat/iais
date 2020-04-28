package com.nbs.iais.ms.meta.referential.db.utils;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
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
        statisticalProgram.setVersionRationale("First Version");
        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setCreator(JWT.decode(command.getJwt()).getClaim("user").asLong());
        statisticalProgram.setCreatedTimestamp(Instant.now());


        return statisticalProgram;
    }
    
    public static AgentEntity translate(final CreateAgentCommand command) {

		final AgentEntity agentEntity = new AgentEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			agentEntity.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			agentEntity.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			agentEntity.setLocalId(command.getLocalId());
		}

		agentEntity.setType(command.getType());
		agentEntity.setAccount(command.getAccount());
		agentEntity.setVersion("1.0");
		agentEntity.setVersionDate(LocalDateTime.now());
		agentEntity.setVersionRationale("Newly created");

		return agentEntity;
	}
}
