package com.nbs.iais.ms.meta.referential.db.utils;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
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

        statisticalProgram.setVersion(command.getVersion());
        statisticalProgram.setVersionDate(LocalDateTime.now());
        statisticalProgram.setVersionRationale(command.getVersionRationale());
        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setCreator(JWT.decode(command.getJwt()).getClaim("user").asLong());
        statisticalProgram.setCreatedTimestamp(Instant.now());


        return statisticalProgram;
    }


    public static StatisticalProgramEntity translate(final AddStatisticalProgramVersionCommand command,
                                                     final StatisticalProgramEntity previousVersion) {

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();

        if(StringTools.isNotEmpty(command.getName())) {
            statisticalProgram.setName(command.getName(), command.getLanguage());
        } else {
            statisticalProgram.setName(previousVersion.getName(command.getLanguage()), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getAcronym())) {
            statisticalProgram.setAcronym(command.getAcronym(), command.getLanguage());
        } else {
            statisticalProgram.setAcronym(previousVersion.getAcronym(command.getLanguage()), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getDescription())) {
            statisticalProgram.setDescription(command.getDescription(), command.getLanguage());
        } else {
            statisticalProgram.setDescription(previousVersion.getDescription(command.getLanguage()), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getLocalId())) {
            statisticalProgram.setLocalId(command.getLocalId());
        }

        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setBudget(command.getBudget());
        statisticalProgram.setSourceOfFunding(command.getSourceOfFunding());
        statisticalProgram.setDateEnded(command.getDateEnded());
        statisticalProgram.setDateInitiated(command.getDateInitiated());

        statisticalProgram.setVersion(command.getVersion());
        statisticalProgram.setVersionDate(command.getVersionDate());
        statisticalProgram.setVersionRationale(command.getVersionRationale());
        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setCreator(JWT.decode(command.getJwt()).getClaim("user").asLong());
        statisticalProgram.setCreatedTimestamp(Instant.now());


        return statisticalProgram;
    }

    public static void translate(final UpdateStatisticalProgramCommand command, final StatisticalProgramEntity sp) {

        if(StringTools.isNotEmpty(command.getName())) {
            sp.setName(command.getName(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getDescription())) {
            sp.setDescription(command.getDescription(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getAcronym())) {
            sp.setAcronym(command.getAcronym(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getVersionRationale())) {
            sp.setVersionRationale(command.getVersionRationale());
        }

        if(command.getBudget() > 0.0) {
            sp.setBudget(command.getBudget());
        }

        if(command.getVersionDate() != null) {
            sp.setVersionDate(command.getVersionDate());
        }

        if(command.getDateInitiated() != null) {
            sp.setDateInitiated(command.getDateInitiated());
        }

        if(command.getDateEnded() != null) {
            sp.setDateEnded(command.getDateEnded());
        }

        if(StringTools.isNotEmpty(command.getSourceOfFunding())) {
            sp.setSourceOfFunding(command.getSourceOfFunding());
        }

        if(command.getStatus() != null) {
            sp.setProgramStatus(command.getStatus());
        }

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

    public static BusinessFunctionEntity translate(final CreateBusinessFunctionCommand command) {

        final BusinessFunctionEntity businessFunctionEntity = new BusinessFunctionEntity();

        businessFunctionEntity.setLocalId(command.getLocalId());
        businessFunctionEntity.setName(command.getName(), command.getLanguage());
        businessFunctionEntity.setDescription(command.getDescription(), command.getLanguage());
        businessFunctionEntity.setVersion("5.1");
        businessFunctionEntity.setVersionRationale("Latest version");
        businessFunctionEntity.setVersionDate(command.getVersionDate());

        return businessFunctionEntity;

    }

    public static void translate(final UpdateBusinessFunctionCommand command, final BusinessFunctionEntity existingBusinessFunction) {
        if(StringTools.isNotEmpty(command.getDescription())) {
            existingBusinessFunction.setDescription(command.getDescription(), command.getLanguage());
        }

        if(StringTools.isNotEmpty(command.getName())) {
            existingBusinessFunction.setName(command.getName(), command.getLanguage());
        }
    }
}
