package com.nbs.iais.ms.meta.referential.db.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service.CreateBusinessServiceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service.UpdateBusinessServiceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.CreateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.CreateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.UpdateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.UpdateProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.CreateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.UpdateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.AddProcessDocumentationOutputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.UpdateOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.CreateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.UpdateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.*;


public class CommandTranslator {

	public static StatisticalProgramEntity translate(final CreateStatisticalProgramCommand command) {

		final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			statisticalProgram.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getAcronym())) {
			statisticalProgram.setAcronym(command.getAcronym(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			statisticalProgram.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
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

		if (StringTools.isNotEmpty(command.getName())) {
			statisticalProgram.setName(command.getName(), command.getLanguage());
		} else {
			previousVersion.getName().getMap().forEach((language, text) ->
					statisticalProgram.setName(text, Language.getLanguage(language)));
		}

		if (StringTools.isNotEmpty(command.getAcronym())) {
			statisticalProgram.setAcronym(command.getAcronym(), command.getLanguage());
		} else {
			previousVersion.getAcronym().getMap().forEach((language, text) ->
					statisticalProgram.setAcronym(text, Language.getLanguage(language)));
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			statisticalProgram.setDescription(command.getDescription(), command.getLanguage());
		} else {
			previousVersion.getDescription().getMap().forEach((language, text) ->
					statisticalProgram.setDescription(text, Language.getLanguage(language)));
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
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
		statisticalProgram.setCreator(JWT.decode(command.getJwt()).getClaim("user").asLong());
		statisticalProgram.setCreatedTimestamp(Instant.now());

		return statisticalProgram;
	}

	public static void translate(final UpdateStatisticalProgramCommand command, final StatisticalProgramEntity sp) {

		if (StringTools.isNotEmpty(command.getName())) {
			sp.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			sp.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getAcronym())) {
			sp.setAcronym(command.getAcronym(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getVersionRationale())) {
			sp.setVersionRationale(command.getVersionRationale());
		}

		if (command.getBudget() > 0.0) {
			sp.setBudget(command.getBudget());
		}

		if (command.getVersionDate() != null) {
			sp.setVersionDate(command.getVersionDate());
		}

		if (command.getDateInitiated() != null) {
			sp.setDateInitiated(command.getDateInitiated());
		}

		if (command.getDateEnded() != null) {
			sp.setDateEnded(command.getDateEnded());
		}

		if (StringTools.isNotEmpty(command.getSourceOfFunding())) {
			sp.setSourceOfFunding(command.getSourceOfFunding());
		}

		if (command.getStatus() != null) {
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
		agentEntity.setVersionRationale("First Version");

		return agentEntity;
	}

	public static void translate(final UpdateAgentCommand command, final AgentEntity existingAgent) {
		if (StringTools.isNotEmpty(command.getDescription())) {
			existingAgent.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getName())) {
			existingAgent.setName(command.getName(), command.getLanguage());
		}

		if (command.getType() != null) {
			existingAgent.setType(command.getType());
		}
		if (command.getAccount() != null) {
			existingAgent.setAccount(command.getAccount());
		}
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

	public static void translate(final UpdateBusinessFunctionCommand command,
			final BusinessFunctionEntity existingBusinessFunction) {
		if (StringTools.isNotEmpty(command.getDescription())) {
			existingBusinessFunction.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getName())) {
			existingBusinessFunction.setName(command.getName(), command.getLanguage());
		}
	}

	public static StatisticalStandardReferenceEntity translate(final CreateStatisticalStandardCommand command) {

		final StatisticalStandardReferenceEntity statisticalStandard = new StatisticalStandardReferenceEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			statisticalStandard.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			statisticalStandard.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			statisticalStandard.setLocalId(command.getLocalId());
		}

		if(StringTools.isNotEmpty(command.getLink())) {
			statisticalStandard.setLink(command.getLink(), command.getLanguage());
		}

		statisticalStandard.setType(command.getType());

		statisticalStandard.setVersion(command.getVersion());
		statisticalStandard.setVersionDate(LocalDateTime.now());
		statisticalStandard.setVersionRationale(command.getVersionRationale());

		return statisticalStandard;
	}

	public static StatisticalStandardReferenceEntity translate(final UpdateStatisticalStandardCommand command,
			StatisticalStandardReferenceEntity statisticalStandard) {

		if (StringTools.isNotEmpty(command.getName())) {
			statisticalStandard.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			statisticalStandard.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			statisticalStandard.setLocalId(command.getLocalId());
		}
		if (command.getType() != null) {
			statisticalStandard.setType(command.getType());
		}
		if (StringTools.isNotEmpty(command.getVersion())) {
			statisticalStandard.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			statisticalStandard.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			statisticalStandard.setVersionRationale(command.getVersionRationale());
		}
		return statisticalStandard;
	}

	public static LegislativeReferenceEntity translate(final CreateLegislativeReferenceCommand command) {

		final LegislativeReferenceEntity legislativeReference = new LegislativeReferenceEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			legislativeReference.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			legislativeReference.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			legislativeReference.setLocalId(command.getLocalId());
		}

		if (StringTools.isNotEmpty(command.getLink())) {
			legislativeReference.setLink(command.getLink(), command.getLanguage());
		}

		legislativeReference.setApprovalDate(command.getApprovalDate());
		legislativeReference.setVersion(command.getVersion());

		legislativeReference.setLegislativeType(command.getType());

		legislativeReference.setVersion(command.getVersion());
		legislativeReference.setVersionDate(LocalDateTime.now());
		legislativeReference.setVersionRationale(command.getVersionRationale());

		return legislativeReference;
	}

	public static LegislativeReferenceEntity translate(final UpdateLegislativeReferenceCommand command,
			LegislativeReferenceEntity legislativeReference) {

		if (StringTools.isNotEmpty(command.getName())) {
			legislativeReference.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			legislativeReference.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLink())) {
			legislativeReference.setLink(command.getLink(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			legislativeReference.setLocalId(command.getLocalId());
		}
		if (command.getType() != null) {
			legislativeReference.setLegislativeType(command.getType());
		}

		if (command.getApprovalDate() != null) {
			legislativeReference.setApprovalDate(command.getApprovalDate());
		}

		if (StringTools.isNotEmpty(command.getVersion())) {
			legislativeReference.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			legislativeReference.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			legislativeReference.setVersionRationale(command.getVersionRationale());
		}
		return legislativeReference;
	}

	public static ProcessDocumentationEntity translate(final CreateProcessDocumentationCommand command) {

		final ProcessDocumentationEntity processDocumentation = new ProcessDocumentationEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			processDocumentation.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processDocumentation.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processDocumentation.setLocalId(command.getLocalId());
		}

		if (command.getFrequency() != null) {
			processDocumentation.setFrequency(command.getFrequency());
		}

		processDocumentation.setVersion(command.getVersion());
		processDocumentation.setVersionDate(LocalDateTime.now());
		processDocumentation.setVersionRationale(command.getVersionRationale());

		return processDocumentation;
	}

	public static ProcessDocumentationEntity translate(final AddProcessDocumentationVersionCommand command,
													   final ProcessDocumentationEntity currentVersion) {

		final ProcessDocumentationEntity processDocumentation = new ProcessDocumentationEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			processDocumentation.setName(command.getName(), command.getLanguage());
		} else {
			currentVersion.getName().getMap().forEach((language, text) ->
				processDocumentation.setName(text, Language.getLanguage(language)));

		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processDocumentation.setDescription(command.getDescription(), command.getLanguage());
		} else {
			currentVersion.getDescription().getMap().forEach((language, text) ->
					processDocumentation.setDescription(text, Language.getLanguage(language)));
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processDocumentation.setLocalId(command.getLocalId());
		}

		if (command.getFrequency() != null) {
			processDocumentation.setFrequency(command.getFrequency());
		} else {
			processDocumentation.setFrequency(currentVersion.getFrequency());
		}

		processDocumentation.setVersion(command.getVersion());
		if (command.getVersionDate() != null) {
			processDocumentation.setVersionDate(command.getVersionDate());
		}
		if (StringTools.isNotEmpty(command.getVersionRationale())) {
			processDocumentation.setVersionRationale(command.getVersionRationale());
		}

		return processDocumentation;
	}

	public static ProcessDocumentationEntity translate(final UpdateProcessDocumentationCommand command,
			ProcessDocumentationEntity processDocumentation) {

		if (StringTools.isNotEmpty(command.getName())) {
			processDocumentation.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processDocumentation.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processDocumentation.setLocalId(command.getLocalId());
		}
		if (StringTools.isNotEmpty(command.getVersion())) {
			processDocumentation.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			processDocumentation.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			processDocumentation.setVersionRationale(command.getVersionRationale());
		}
		if (command.getFrequency() != null) {
			processDocumentation.setFrequency(command.getFrequency());
		}

		return processDocumentation;
	}
	
	public static ProcessDocumentEntity translate(final CreateProcessDocumentCommand command) {

		final ProcessDocumentEntity processDocument = new ProcessDocumentEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			processDocument.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processDocument.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processDocument.setLocalId(command.getLocalId());
		}
		if (StringTools.isNotEmpty(command.getLink())) {
			processDocument.setLink(command.getLink(), command.getLanguage());
		}

		processDocument.setMediaType(command.getType());

		processDocument.setVersion(command.getVersion());
		processDocument.setVersionDate(LocalDateTime.now());
		processDocument.setVersionRationale(command.getVersionRationale());

		return processDocument;
	}

	public static ProcessDocumentEntity translate(final UpdateProcessDocumentCommand command,
			ProcessDocumentEntity processDocument) {

		if (StringTools.isNotEmpty(command.getName())) {
			processDocument.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processDocument.setDescription(command.getDescription(), command.getLanguage());
		}
		if (StringTools.isNotEmpty(command.getLink())) {
			processDocument.setLink(command.getLink(), command.getLanguage());
		}
		if (StringTools.isNotEmpty(command.getLocalId())) {
			processDocument.setLocalId(command.getLocalId());
		}
		if (command.getType() != null) {
			processDocument.setMediaType(command.getType());
		}
		if (StringTools.isNotEmpty(command.getVersion())) {
			processDocument.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			processDocument.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			processDocument.setVersionRationale(command.getVersionRationale());
		}
		return processDocument;
	}
	
	public static ProcessQualityEntity translate(final CreateProcessQualityCommand command) {

		final ProcessQualityEntity processQuality = new ProcessQualityEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			processQuality.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processQuality.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processQuality.setLocalId(command.getLocalId());
		}
   
		processQuality.setQualityType(command.getType());

		processQuality.setVersion(command.getVersion());
		processQuality.setVersionDate(LocalDateTime.now());
		processQuality.setVersionRationale(command.getVersionRationale());

		return processQuality;
	}

	public static ProcessQualityEntity translate(final UpdateProcessQualityCommand command,
			ProcessQualityEntity processQuality) {

		if (StringTools.isNotEmpty(command.getName())) {
			processQuality.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processQuality.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processQuality.setLocalId(command.getLocalId());
		}
		if (command.getType() != null) {
			processQuality.setQualityType(command.getType());
		}
		if (StringTools.isNotEmpty(command.getVersion())) {
			processQuality.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			processQuality.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			processQuality.setVersionRationale(command.getVersionRationale());
		}
		return processQuality;
	}
	
	public static ProcessInputSpecificationEntity translate(final AddProcessDocumentationInputCommand command) {

		final ProcessInputSpecificationEntity processInputSpecification = new ProcessInputSpecificationEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			processInputSpecification.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processInputSpecification.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processInputSpecification.setLocalId(command.getLocalId());
		} else {
			processInputSpecification.setLocalId(UUID.randomUUID().toString());
		}
		processInputSpecification.setVersion(command.getVersion());
		processInputSpecification.setVersionDate(command.getVersionDate());
		processInputSpecification.setVersionRationale(command.getVersionRationale());

		return processInputSpecification;
	}

	public static ProcessInputSpecificationEntity translate(final UpdateProcessDocumentationInputCommand command,
			ProcessInputSpecificationEntity processInputSpecification) {

		if (StringTools.isNotEmpty(command.getName())) {
			processInputSpecification.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processInputSpecification.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processInputSpecification.setLocalId(command.getLocalId());
		}
	 	if (StringTools.isNotEmpty(command.getVersion())) {
			processInputSpecification.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			processInputSpecification.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			processInputSpecification.setVersionRationale(command.getVersionRationale());
		}
		return processInputSpecification;
	}
	
	public static ProcessOutputSpecificationEntity translate(final AddProcessDocumentationOutputCommand command) {

		final ProcessOutputSpecificationEntity processOutputSpecification = new ProcessOutputSpecificationEntity();

		if (StringTools.isNotEmpty(command.getName())) {
			processOutputSpecification.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processOutputSpecification.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processOutputSpecification.setLocalId(command.getLocalId());
		} else {
			processOutputSpecification.setLocalId(UUID.randomUUID().toString());
		}
   
		processOutputSpecification.setVersion(command.getVersion());
		processOutputSpecification.setVersionDate(LocalDateTime.now());
		processOutputSpecification.setVersionRationale(command.getVersionRationale());

		return processOutputSpecification;
	}

	public static ProcessOutputSpecificationEntity translate(final UpdateOutputSpecificationCommand command,
			ProcessOutputSpecificationEntity processOutputSpecification) {

		if (StringTools.isNotEmpty(command.getName())) {
			processOutputSpecification.setName(command.getName(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getDescription())) {
			processOutputSpecification.setDescription(command.getDescription(), command.getLanguage());
		}

		if (StringTools.isNotEmpty(command.getLocalId())) {
			processOutputSpecification.setLocalId(command.getLocalId());
		}
	
		if (StringTools.isNotEmpty(command.getVersion())) {
			processOutputSpecification.setVersion(command.getVersion());
		}
		if (command.getVersionDate() != null) {
			processOutputSpecification.setVersionDate(command.getVersionDate());
		}
		if (command.getVersionRationale() != null) {
			processOutputSpecification.setVersionRationale(command.getVersionRationale());
		}
		return processOutputSpecification;
	}

	public static BusinessServiceEntity translate(final CreateBusinessServiceCommand command) {

		final BusinessServiceEntity businessService = new BusinessServiceEntity();

		businessService.setName(command.getName(), command.getLanguage());
		businessService.setDescription(command.getDescription(), command.getLanguage());
		businessService.setLocalId(command.getLocalId());
		businessService.setVersion(command.getVersion());
		businessService.setVersionRationale(command.getVersionRationale());
		businessService.setVersionDate(command.getVersionDate());

		return businessService;
	}

	public static void translate(final UpdateBusinessServiceCommand command, final BusinessServiceEntity businessService) {

		if(StringTools.isNotEmpty(command.getName())) {
			businessService.setName(command.getName(), command.getLanguage());
		}

		if(StringTools.isNotEmpty(command.getDescription())) {
			businessService.setDescription(command.getDescription(), command.getLanguage());
		}

		if(StringTools.isNotEmpty(command.getVersion())) {
			businessService.setVersion(command.getVersion());
		}

		if(StringTools.isNotEmpty(command.getVersionRationale())) {
			businessService.setVersionRationale(command.getVersionRationale());
		}

		if(command.getVersionDate() != null) {
			businessService.setVersionDate(command.getVersionDate());
		}

	}

	public static ProcessMethodEntity translate(final CreateProcessMethodCommand command) {

		final ProcessMethodEntity processMethod = new ProcessMethodEntity();
		processMethod.setName(command.getName(), command.getLanguage());
		processMethod.setDescription(command.getDescription(), command.getLanguage());
		processMethod.setLocalId(command.getLocalId());
		processMethod.setVersion(command.getVersion());
		processMethod.setVersionRationale(command.getVersionRationale());
		processMethod.setVersionDate(command.getVersionDate());

		return processMethod;
	}

	public static void translate(final UpdateProcessMethodCommand command, final ProcessMethodEntity processMethod) {

		if(StringTools.isNotEmpty(command.getName())) {
			processMethod.setName(command.getName(), command.getLanguage());
		}

		if(StringTools.isNotEmpty(command.getDescription())) {
			processMethod.setDescription(command.getDescription(), command.getLanguage());
		}

		if(StringTools.isNotEmpty(command.getLocalId())) {
			processMethod.setLocalId(command.getLocalId());
		}
	}
}
