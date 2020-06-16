package com.nbs.iais.ms.common.db.domains.translators;

import java.util.ArrayList;
import java.util.Optional;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocument;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessQuality;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.*;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.BusinessFunctionMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.ProcessDocumentationMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.StatisticalProgramMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.PhaseName;
import com.nbs.iais.ms.common.enums.RoleType;

public class Translator {

	private String rootUrl;

	public static <A extends Account> Optional<AccountDTO> translate(final A account) {

		if (account == null) {
			return Optional.empty();
		}
		final AccountDTO accountDTO = new AccountDTO(account.getId());

		accountDTO.setUsername(account.getUsername());
		accountDTO.setRole(account.getRole());
		accountDTO.setStatus(account.getStatus());
		accountDTO.setName(account.getName());
		accountDTO.setEmail(account.getEmail());
		accountDTO.setLink("/accounts/" + account.getId().toString());
		return Optional.of(accountDTO);

	}

	public static <A extends Account> Optional<DTOList<AccountDTO>> translate(final Iterable<A> accounts) {

		if (!accounts.iterator().hasNext()) {
			return Optional.empty();
		}
		final DTOList<AccountDTO> accountDTOS = DTOList.empty(AccountDTO.class);

		accounts.forEach(accountEntity -> translate(accountEntity).ifPresent(accountDTOS::add));

		return Optional.of(accountDTOS);
	}

	public static <SP extends StatisticalProgram> Optional<DTOList<StatisticalProgramDTO>> translate(
			final Iterable<SP> statisticalPrograms, final Language language) {
		if (statisticalPrograms == null || !statisticalPrograms.iterator().hasNext()) {
			return Optional.empty();
		}
		final DTOList<StatisticalProgramDTO> statisticalProgramDTOS = DTOList.empty(StatisticalProgramDTO.class);

		statisticalPrograms.forEach(sp -> translate(sp, language).ifPresent(statisticalProgramDTOS::add));

		return Optional.of(statisticalProgramDTOS);

	}

	public static <BF extends BusinessFunction> Optional<BusinessFunctionDTO> translate(final BF businessFunction,
			final Language language) {

		if (businessFunction == null) {
			return Optional.empty();
		}

		final BusinessFunctionDTO businessFunctionDTO = new BusinessFunctionDTO(businessFunction.getId());
		businessFunctionDTO.setName(businessFunction.getName(language));
		businessFunctionDTO.setDescription(businessFunction.getDescription(language));
		businessFunctionDTO.setLocalId(businessFunction.getLocalId());
		businessFunctionDTO.setVersion(businessFunction.getVersion());
		businessFunctionDTO.setVersionDate(businessFunction.getVersionDate());
		businessFunctionDTO.setVersionRationale(businessFunction.getVersionRationale());
		businessFunctionDTO.setPhaseId(Integer.parseInt(businessFunction.getLocalId().substring(0, 1)));
		businessFunctionDTO.setPhase(PhaseName.fromId(businessFunctionDTO.getPhaseId()).translate(language));
		businessFunctionDTO.setLink("/metadata/referential/gsim/function/view/" + businessFunction.getId().toString());

		return Optional.of(businessFunctionDTO);

	}

	public static <BF extends BusinessFunction> Optional<DTOList<BusinessFunctionDTO>> translateBusinessFunctions(
			final Iterable<BF> businessFunctions, final Language language) {

		if (businessFunctions == null || !businessFunctions.iterator().hasNext()) {
			return Optional.empty();
		}
		final DTOList<BusinessFunctionDTO> businessFunctionDTOS = DTOList.empty(BusinessFunctionDTO.class);
		businessFunctions.forEach(bf -> translate(bf, language).ifPresent(businessFunctionDTOS::add));
		return Optional.of(businessFunctionDTOS);
	}

	public static <BS extends BusinessService> Optional<BusinessServiceDTO> translate(final BS businessService,
																					  final Language language) {

		if(businessService == null) {
			return Optional.empty();
		}

		final BusinessServiceDTO businessServiceDTO = new BusinessServiceDTO(businessService.getId());

		businessServiceDTO.setName(businessService.getName(language));
		businessServiceDTO.setDescription(businessService.getDescription(language));
		businessServiceDTO.setLocalId(businessService.getLocalId());
		businessServiceDTO.setVersion(businessService.getVersion());
		businessServiceDTO.setVersionDate(businessService.getVersionDate());
		businessServiceDTO.setVersionRationale(businessService.getVersionRationale());
		businessServiceDTO.setLink("/metadata/referential/gsim/service/view/" + businessService.getId().toString());
		businessServiceDTO.setInterfaces(new ArrayList<>());
		if(businessService.getServiceInterfaces() != null && businessService.getServiceInterfaces().size() > 0) {
			businessService.getServiceInterfaces().forEach(serviceInterface ->
					businessServiceDTO.getInterfaces().add(serviceInterface));
		}

		return Optional.of(businessServiceDTO);
	}

	public static <BS extends BusinessService> Optional<DTOList<BusinessServiceDTO>> translateBusinessServices(
			final Iterable<BS> businessServices, final Language language) {

		if(businessServices == null || !businessServices.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<BusinessServiceDTO> businessServiceDTOS = DTOList.empty(BusinessServiceDTO.class);

		businessServices.forEach(businessService ->
				translate(businessService, language).ifPresent(businessServiceDTOS::add));

		return Optional.of(businessServiceDTOS);
	}

	public static <SP extends StatisticalProgram> Optional<StatisticalProgramDTO> translate(final SP statisticalProgram,
			final Language language) {

		if (statisticalProgram == null) {
			return Optional.empty();
		}

		final StatisticalProgramDTO statisticalProgramDTO = new StatisticalProgramDTO(statisticalProgram.getId());
		statisticalProgramDTO.setName(statisticalProgram.getName(language));
		statisticalProgramDTO.setDescription(statisticalProgram.getDescription(language));
		statisticalProgramDTO.setAcronym(statisticalProgram.getAcronym(language));
		statisticalProgramDTO.setProgramStatus(statisticalProgram.getProgramStatus());
		statisticalProgramDTO.setSourceOfFunding(statisticalProgram.getSourceOfFounding());
		statisticalProgramDTO.setDateInitiated(statisticalProgram.getDateInitiated());
		statisticalProgramDTO.setDateEnded(statisticalProgram.getDateEnded());
		statisticalProgramDTO.setLocalId(statisticalProgram.getLocalId());
		statisticalProgramDTO.setVersion(statisticalProgram.getVersion());
		statisticalProgramDTO.setVersionDate(statisticalProgram.getVersionDate());
		statisticalProgramDTO.setVersionRationale(statisticalProgram.getVersionRationale());
		statisticalProgramDTO.setLink("/metadata/referential/view/" + statisticalProgram.getId().toString());
		statisticalProgramDTO.setBudget(statisticalProgram.getBudget());
		statisticalProgram.getAdministrators().forEach(agentInRole -> {
			if (agentInRole.getRole() == RoleType.OWNER) {
				translateMini(agentInRole.getAgent(), language).ifPresent(statisticalProgramDTO::setOwner);
			}
			if (agentInRole.getRole() == RoleType.MAINTAINER) {
				translateMini(agentInRole.getAgent(), language).ifPresent(statisticalProgramDTO::setMaintainer);
			}
			if (agentInRole.getRole() == RoleType.CONTACT) {
				translateMini(agentInRole.getAgent(), language).ifPresent(statisticalProgramDTO::setContact);
			}
		});

		translateLegislativeReferences(statisticalProgram.getLegislativeReference(), language)
				.ifPresent(statisticalProgramDTO::setLegislativeReferences);

		translateStatisticalStandards(statisticalProgram.getStatisticalStandardReferences(), language)
				.ifPresent(statisticalProgramDTO::setStatisticalStandards);

		translateProcessDocumentationsMini(statisticalProgram.getProcessDocumentations(), language)
				.ifPresent(statisticalProgramDTO::setProcessDocumentations);

		return Optional.of(statisticalProgramDTO);
	}

	public static <AG extends Agent> Optional<AgentDTO> translate(final AG agent, final Language language) {

		if (agent == null) {
			return Optional.empty();
		}

		final AgentDTO agentDTO = new AgentDTO(agent.getId());
		agentDTO.setName(agent.getName(language));
		agentDTO.setDescription(agent.getDescription(language));
		agentDTO.setAccount(agent.getAccount());
		agentDTO.setType(agent.getType());
		translateMini(agent.getParent(), language).ifPresent(agentDTO::setParent);
		agentDTO.setChildren(getChildren(agent, language));
		agentDTO.setLocalId(agent.getLocalId());
		agentDTO.setVersion(agent.getVersion());
		agentDTO.setVersionDate(agent.getVersionDate());
		agentDTO.setVersionRationale(agent.getVersionRationale());
		agentDTO.setLink("/metadata/referential/gsim/agent/view/" + agent.getId().toString());

		return Optional.of(agentDTO);
	}

	public static Optional<AgentMiniDTO> translateMini(final Agent agent, final Language language) {

		if (agent == null) {
			return Optional.empty();
		}
		AgentMiniDTO agentMiniDTO = new AgentMiniDTO(agent.getId());
		agentMiniDTO.setLink("/metadata/referential/gsim/agent/view/" + agent.getId());
		agentMiniDTO.setType(agent.getType());
		agentMiniDTO.setName(agent.getName(language));
		return Optional.of(agentMiniDTO);
	}

	public static <AG extends Agent> Optional<DTOList<AgentDTO>> translateAgents(final Iterable<AG> agents,
			final Language language) {
		if (agents == null || !agents.iterator().hasNext()) {
			return Optional.empty();
		}
		final DTOList<AgentDTO> agentDTOS = DTOList.empty(AgentDTO.class);

		agents.forEach(sp -> translate(sp, language).ifPresent(agentDTOS::add));

		return Optional.of(agentDTOS);

	}

	private static <AG extends Agent> DTOList<AgentMiniDTO> getChildren(AG agent, final Language language) {

		DTOList<AgentMiniDTO> children = DTOList.empty(AgentMiniDTO.class);
		if (agent.getChildren() != null) {
			agent.getChildren().forEach(child -> {
				AgentMiniDTO agentMini = new AgentMiniDTO(child.getId());
				agentMini.setLink("/metadata/referential/gsim/agent/view/" + child.getId().toString());
				agentMini.setName(child.getName(language));
				agentMini.setType(child.getType());
				children.add(agentMini);
			});
		}
		return children;
	}

	public static <SS extends StatisticalStandardReference> Optional<StatisticalStandardDTO> translate(
			final SS statisticalStandard, final Language language) {

		if (statisticalStandard == null) {
			return Optional.empty();
		}

		final StatisticalStandardDTO statisticalStandardDTO = new StatisticalStandardDTO(statisticalStandard.getId());
		statisticalStandardDTO.setName(statisticalStandard.getName(language));
		statisticalStandardDTO.setDescription(statisticalStandard.getDescription(language));

		statisticalStandardDTO.setType(statisticalStandard.getType());
		statisticalStandardDTO.setLocalId(statisticalStandard.getLocalId());
		statisticalStandardDTO.setVersion(statisticalStandard.getVersion());
		statisticalStandardDTO.setVersionDate(statisticalStandard.getVersionDate());
		statisticalStandardDTO.setVersionRationale(statisticalStandard.getVersionRationale());
		statisticalStandardDTO.setExternalLink(statisticalStandard.getLink(language));
		statisticalStandardDTO.setLink("/metadata/referential/gsim/standard/view/" + statisticalStandard.getId().toString());

		return Optional.of(statisticalStandardDTO);
	}

	public static <SS extends StatisticalStandardReference> Optional<DTOList<StatisticalStandardDTO>> translateStatisticalStandards(
			final Iterable<SS> statisticalStandards, final Language language) {

		if (statisticalStandards == null || !statisticalStandards.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<StatisticalStandardDTO> statisticalStandardDTOS = DTOList.empty(StatisticalStandardDTO.class);

		statisticalStandards.forEach(ss -> translate(ss, language).ifPresent(statisticalStandardDTOS::add));

		return Optional.of(statisticalStandardDTOS);
	}

	public static <LR extends LegislativeReference> Optional<LegislativeReferenceDTO> translate(
			final LR legislativeReference, final Language language) {

		if (legislativeReference == null) {
			return Optional.empty();
		}

		final LegislativeReferenceDTO legislativeReferenceDTO = new LegislativeReferenceDTO(
				legislativeReference.getId());
		legislativeReferenceDTO.setApproval(legislativeReference.getApprovalDate());
		legislativeReferenceDTO.setLocalId(legislativeReference.getLocalId());
		legislativeReferenceDTO.setName(legislativeReference.getName(language));
		legislativeReferenceDTO.setDescription(legislativeReference.getDescription(language));
		legislativeReferenceDTO.setExternalLink(legislativeReference.getLink(language));
		legislativeReferenceDTO.setType(legislativeReference.getLegislativeType());
		legislativeReferenceDTO.setLocalId(legislativeReference.getLocalId());
		legislativeReferenceDTO.setVersion(legislativeReference.getVersion());
		legislativeReferenceDTO.setVersionDate(legislativeReference.getVersionDate());
		legislativeReferenceDTO.setVersionRationale(legislativeReference.getVersionRationale());
		legislativeReferenceDTO.setLink("/metadata/referential/gsim/regulation/view/" + legislativeReference.getId().toString());

		return Optional.of(legislativeReferenceDTO);
	}

	public static <LR extends LegislativeReference> Optional<DTOList<LegislativeReferenceDTO>> translateLegislativeReferences(
			final Iterable<LR> legislativeReferences, final Language language) {

		if (legislativeReferences == null || !legislativeReferences.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<LegislativeReferenceDTO> legislativeReferenceDTOS = DTOList.empty(LegislativeReferenceDTO.class);

		legislativeReferences.forEach(lr -> translate(lr, language).ifPresent(legislativeReferenceDTOS::add));

		return Optional.of(legislativeReferenceDTOS);
	}

	public static <PD extends ProcessDocument> Optional<ProcessDocumentDTO> translate(final PD processDocument,
			final Language language) {

		if (processDocument == null) {
			return Optional.empty();
		}

		final ProcessDocumentDTO processDocumentDTO = new ProcessDocumentDTO(processDocument.getId());

		processDocumentDTO.setName(processDocument.getName(language));
		processDocumentDTO.setDescription(processDocument.getDescription(language));
		processDocumentDTO.setExternaLink(processDocument.getLink(language));
		processDocumentDTO.setMediaType(processDocument.getMediaType());
		processDocumentDTO.setLocalId(processDocument.getLocalId());
		processDocumentDTO.setVersion(processDocument.getVersion());
		processDocumentDTO.setVersionDate(processDocument.getVersionDate());
		processDocumentDTO.setVersionRationale(processDocument.getVersionRationale());
		processDocumentDTO.setLink("/process/documents/" + processDocument.getId().toString());

		return Optional.of(processDocumentDTO);
	}

	public static <PD extends ProcessDocument> Optional<DTOList<ProcessDocumentDTO>> translateProcessDocuments(
			final Iterable<PD> processDocuments, final Language language) {

		if (processDocuments == null || !processDocuments.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessDocumentDTO> processDocumentDTOS = DTOList.empty(ProcessDocumentDTO.class);

		processDocuments.forEach(pd -> translate(pd, language).ifPresent(processDocumentDTOS::add));

		return Optional.of(processDocumentDTOS);
	}

	public static <PD extends ProcessMethod> Optional<ProcessMethodDTO> translate(final PD processMethod,
			final Language language) {

		if (processMethod == null) {
			return Optional.empty();
		}

		final ProcessMethodDTO processMethodDTO = new ProcessMethodDTO(processMethod.getId());

		processMethodDTO.setName(processMethod.getName(language));
		processMethodDTO.setDescription(processMethod.getDescription(language));

		processMethodDTO.setLocalId(processMethod.getLocalId());
		processMethodDTO.setVersion(processMethod.getVersion());
		processMethodDTO.setVersionDate(processMethod.getVersionDate());
		processMethodDTO.setVersionRationale(processMethod.getVersionRationale());

		return Optional.of(processMethodDTO);
	}

	public static <PD extends ProcessMethod> Optional<DTOList<ProcessMethodDTO>> translateProcessMethods(
			final Iterable<PD> processMethods, final Language language) {

		if (processMethods == null || !processMethods.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessMethodDTO> processMethodDTOS = DTOList.empty(ProcessMethodDTO.class);

		processMethods.forEach(pd -> translate(pd, language).ifPresent(processMethodDTOS::add));

		return Optional.of(processMethodDTOS);
	}

	public static <LR extends ProcessQuality> Optional<ProcessQualityDTO> translate(final LR processQuality,
			final Language language) {

		if (processQuality == null) {
			return Optional.empty();
		}

		final ProcessQualityDTO processQualityDTO = new ProcessQualityDTO(processQuality.getId());

		processQualityDTO.setName(processQuality.getName(language));
		processQualityDTO.setDescription(processQuality.getDescription(language));

		processQualityDTO.setType(processQuality.getQualityType());
		processQualityDTO.setLocalId(processQuality.getLocalId());
		processQualityDTO.setVersion(processQuality.getVersion());
		processQualityDTO.setVersionDate(processQuality.getVersionDate());
		processQualityDTO.setVersionRationale(processQuality.getVersionRationale());

		return Optional.of(processQualityDTO);
	}

	public static <PQ extends ProcessQuality> Optional<DTOList<ProcessQualityDTO>> translateProcessQualities(
			final Iterable<PQ> processQualitys, final Language language) {

		if (processQualitys == null || !processQualitys.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessQualityDTO> processQualityDTOS = DTOList.empty(ProcessQualityDTO.class);

		processQualitys.forEach(pq -> translate(pq, language).ifPresent(processQualityDTOS::add));

		return Optional.of(processQualityDTOS);
	}

  
	public static <SS extends ProcessDocumentation> Optional<ProcessDocumentationDTO> translate(
			final SS processDocumentation, final Language language) {

		if (processDocumentation == null) {
			return Optional.empty();
		}

		final ProcessDocumentationDTO processDocumentationDTO = new ProcessDocumentationDTO(
				processDocumentation.getId());
		processDocumentationDTO.setName(processDocumentation.getName(language));
		processDocumentationDTO.setDescription(processDocumentation.getDescription(language));
		processDocumentationDTO.setLocalId(processDocumentation.getLocalId());
		processDocumentationDTO.setVersion(processDocumentation.getVersion());
		processDocumentationDTO.setVersionDate(processDocumentation.getVersionDate());
		processDocumentationDTO.setVersionRationale(processDocumentation.getVersionRationale());

		processDocumentationDTO.setFrequency(processDocumentation.getFrequency());
		translateMini(processDocumentation.getNextBusinessFunction(), language).ifPresent(
				processDocumentationDTO::setNextSubPhase);

		if(processDocumentation.getAdministrators() != null && processDocumentation.getAdministrators().size() > 0) {
			processDocumentation.getAdministrators().forEach(agentInRole -> {

				if (agentInRole.getRole() == RoleType.OWNER) {
					translateMini(agentInRole.getAgent(), language).ifPresent(processDocumentationDTO::setOwner);
				}
				if (agentInRole.getRole() == RoleType.MAINTAINER) {
					translateMini(agentInRole.getAgent(), language).ifPresent(processDocumentationDTO::setMaintainer);
				}

			});
		}

		translateMini(processDocumentation.getBusinessFunction(), language)
				.ifPresent(processDocumentationDTO::setBusinessFunction);

		translateMini(processDocumentation.getStatisticalProgram(), language)
				.ifPresent(processDocumentationDTO::setStatisticalProgram);
		
		translateStatisticalStandards(processDocumentation.getStandardsUsed(), language)
		.ifPresent(processDocumentationDTO::setStandards);


		translateProcessDocuments(processDocumentation.getProcessDocuments(), language)
				.ifPresent(processDocumentationDTO::setDocuments);

		translateProcessMethods(processDocumentation.getProcessMethods(), language)
				.ifPresent(processDocumentationDTO::setProcessMethods);

		translateProcessQualities(processDocumentation.getProcessQualityIndicators(), language)
				.ifPresent(processDocumentationDTO::setProcessQualityList);
		
		translateInputSpecifications(processDocumentation.getProcessInputs(), language)
		.ifPresent(processDocumentationDTO::setProcessInputSpecifications);
		
		translateOutputSpecifications(processDocumentation.getProcessOutputs(), language)
		.ifPresent(processDocumentationDTO::setProcessOutputSpecifications);

		return Optional.of(processDocumentationDTO);
	}

	public static <PI extends ProcessInputSpecification> Optional<ProcessInputSpecificationDTO> translate(final PI processInputSpecification,
			final Language language) {

		if (processInputSpecification == null) {
			return Optional.empty();
		}

		final ProcessInputSpecificationDTO processInputSpecificationDTO = new ProcessInputSpecificationDTO(processInputSpecification.getId());

		processInputSpecificationDTO.setName(processInputSpecification.getName(language));
		processInputSpecificationDTO.setDescription(processInputSpecification.getDescription(language));

		processInputSpecificationDTO.setProcessInputTypes(processInputSpecification.getProcessInputTypes());
		processInputSpecificationDTO.setLocalId(processInputSpecification.getLocalId());
		processInputSpecificationDTO.setVersion(processInputSpecification.getVersion());
		processInputSpecificationDTO.setVersionDate(processInputSpecification.getVersionDate());
		processInputSpecificationDTO.setVersionRationale(processInputSpecification.getVersionRationale());
	
		return Optional.of(processInputSpecificationDTO);
	}

	public static <PI extends ProcessInputSpecification> Optional<DTOList<ProcessInputSpecificationDTO>> translateInputSpecifications(
			final Iterable<PI> processInputSpecifications, final Language language) {

		if (processInputSpecifications == null || !processInputSpecifications.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessInputSpecificationDTO> processInputSpecificationDTOS = DTOList.empty(ProcessInputSpecificationDTO.class);

		processInputSpecifications.forEach(pi -> translate(pi, language).ifPresent(processInputSpecificationDTOS::add));

		return Optional.of(processInputSpecificationDTOS);
	}
	

	public static <PO extends ProcessOutputSpecification> Optional<ProcessOutputSpecificationDTO> translate(final PO processOutputSpecification,
			final Language language) {

		if (processOutputSpecification == null) {
			return Optional.empty();
		}

		final ProcessOutputSpecificationDTO processOutputSpecificationDTO = new ProcessOutputSpecificationDTO(processOutputSpecification.getId());

		processOutputSpecificationDTO.setName(processOutputSpecification.getName(language));
		processOutputSpecificationDTO.setDescription(processOutputSpecification.getDescription(language));

		processOutputSpecificationDTO.setProcessOutputTypes(processOutputSpecification.getProcessOutputTypes());
		processOutputSpecificationDTO.setLocalId(processOutputSpecification.getLocalId());
		processOutputSpecificationDTO.setVersion(processOutputSpecification.getVersion());
		processOutputSpecificationDTO.setVersionDate(processOutputSpecification.getVersionDate());
		processOutputSpecificationDTO.setVersionRationale(processOutputSpecification.getVersionRationale());
	
		return Optional.of(processOutputSpecificationDTO);
	}

	public static <PO extends ProcessOutputSpecification> Optional<DTOList<ProcessOutputSpecificationDTO>> translateOutputSpecifications(
			final Iterable<PO> processOutputSpecifications, final Language language) {

		if (processOutputSpecifications == null || !processOutputSpecifications.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessOutputSpecificationDTO> processOutputSpecificationDTOS = DTOList.empty(ProcessOutputSpecificationDTO.class);

		processOutputSpecifications.forEach(po -> translate(po, language).ifPresent(processOutputSpecificationDTOS::add));

		return Optional.of(processOutputSpecificationDTOS);
	}
	
	public static <SS extends ProcessDocumentation> Optional<DTOList<ProcessDocumentationDTO>> translateProcessDocumentations(
			final Iterable<SS> processDocumentations, final Language language) {

		if (processDocumentations == null || !processDocumentations.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessDocumentationDTO> processDocumentationDTOS = DTOList.empty(ProcessDocumentationDTO.class);

		processDocumentations.forEach(ss -> translate(ss, language).ifPresent(processDocumentationDTOS::add));

		return Optional.of(processDocumentationDTOS);
	}

	public static <SS extends ProcessDocumentation> Optional<ProcessDocumentationMiniDTO> translateMini(
			final SS processDocumentation, final Language language) {

		if (processDocumentation == null) {
			return Optional.empty();
		}

		final ProcessDocumentationMiniDTO processDocumentationMiniDTO = new ProcessDocumentationMiniDTO(
				processDocumentation.getId());
		processDocumentationMiniDTO.setName(processDocumentation.getName(language));
		processDocumentationMiniDTO.setDescription(processDocumentation.getDescription(language));
		processDocumentationMiniDTO.setFrequency(processDocumentation.getFrequency());
		processDocumentationMiniDTO.setLink("/metadata/referential/documentation/view/" + processDocumentation.getId());
		if(processDocumentation.getNextBusinessFunction() != null) {
			processDocumentationMiniDTO.setNextSubPhase(processDocumentation.getNextBusinessFunction().getLocalId());
		}
		processDocumentationMiniDTO
				.setBusinessFunction(translateMini(processDocumentation.getBusinessFunction(), language).orElse(null));
		processDocumentationMiniDTO.setStatisticalProgram(
				translateMini(processDocumentation.getStatisticalProgram(), language).orElse(null));

		processDocumentation.getAdministrators().forEach(agentInRole -> {

			if (agentInRole.getRole() == RoleType.MAINTAINER) {
				translateMini(agentInRole.getAgent(), language).ifPresent(processDocumentationMiniDTO::setMaintainer);
			}

		});

		return Optional.of(processDocumentationMiniDTO);
	}

	public static <SS extends StatisticalProgram> Optional<StatisticalProgramMiniDTO> translateMini(
			final SS statisticalProgram, final Language language) {

		if (statisticalProgram == null) {
			return Optional.empty();
		}

		final StatisticalProgramMiniDTO statisticalProgramMiniDTO = new StatisticalProgramMiniDTO(
				statisticalProgram.getId());
		statisticalProgramMiniDTO.setName(statisticalProgram.getName(language));
		statisticalProgramMiniDTO.setDescription(statisticalProgram.getDescription(language));
		statisticalProgramMiniDTO.setAcronym(statisticalProgram.getAcronym(language));
		statisticalProgramMiniDTO.setLink("/metadata/referential/view/" + statisticalProgram.getId().toString());
		return Optional.of(statisticalProgramMiniDTO);
	}

	public static <SS extends BusinessFunction> Optional<BusinessFunctionMiniDTO> translateMini(
			final SS businessFunction, final Language language) {

		if (businessFunction == null) {
			return Optional.empty();
		}

		final BusinessFunctionMiniDTO businessFunctionMiniDTO = new BusinessFunctionMiniDTO(businessFunction.getId());
		businessFunctionMiniDTO.setName(businessFunction.getName(language));
		businessFunctionMiniDTO.setLocalId(businessFunction.getLocalId());
		businessFunctionMiniDTO.setLink("/metadata/referential/gsim/function/view/" + businessFunction.getId());
		return Optional.of(businessFunctionMiniDTO);
	}

	public static <SS extends ProcessDocumentation> Optional<DTOList<ProcessDocumentationMiniDTO>> translateProcessDocumentationsMini(
			final Iterable<SS> processDocumentations, final Language language) {

		if (processDocumentations == null || !processDocumentations.iterator().hasNext()) {
			return Optional.empty();
		}

		final DTOList<ProcessDocumentationMiniDTO> processDocumentationMiniDTOS = DTOList
				.empty(ProcessDocumentationMiniDTO.class);

		processDocumentations.forEach(ss -> translateMini(ss, language).ifPresent(processDocumentationMiniDTOS::add));

		return Optional.of(processDocumentationMiniDTOS);
	}
}
