package com.nbs.iais.ms.common.db.domains.translators;

import java.util.Optional;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessFunction;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalStandardDTO;
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
		statisticalProgramDTO.setLink("/statistical/programs/" + statisticalProgram.getId());
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
		agentDTO.setLink("/agents/" + agent.getId());

		return Optional.of(agentDTO);
	}

	public static Optional<AgentMiniDTO> translateMini(final Agent agent, final Language language) {

		if (agent == null) {
			return Optional.empty();
		}
		AgentMiniDTO agentMiniDTO = new AgentMiniDTO(agent.getId());
		agentMiniDTO.setLink("/agents/" + agent.getId());
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
				agentMini.setLink("/agents/" + child.getId());
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
		statisticalStandardDTO.setLink("/statistical/standards/" + statisticalStandard.getId());

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
		legislativeReferenceDTO.setNumber(legislativeReference.geNumber());
		legislativeReferenceDTO.setName(legislativeReference.getName(language));
		legislativeReferenceDTO.setDescription(legislativeReference.getDescription(language));
		legislativeReferenceDTO.setExternaLink(legislativeReference.getLink(language));
		legislativeReferenceDTO.setType(legislativeReference.getLegislativeType());
		legislativeReferenceDTO.setLocalId(legislativeReference.getLocalId());
		legislativeReferenceDTO.setVersion(legislativeReference.getVersion());
		legislativeReferenceDTO.setVersionDate(legislativeReference.getVersionDate());
		legislativeReferenceDTO.setVersionRationale(legislativeReference.getVersionRationale());
		legislativeReferenceDTO.setLink("/legislative/references/" + legislativeReference.getId().toString());

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
		return Optional.of(processDocumentationDTO);
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
		processDocumentationMiniDTO.setLink("/process/documentations/" + processDocumentation.getId());
		processDocumentationMiniDTO
				.setBusinessFunction(translateMini(processDocumentation.getBusinessFunction(), language).orElse(null));
		processDocumentationMiniDTO
				.setStatisticalProgram(translateMini(processDocumentation.getStatisticalProgram(), language).orElse(null));

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
		statisticalProgramMiniDTO.setLink("/statistical/programs/" + statisticalProgram.getId());
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
		businessFunctionMiniDTO.setLink("/business/programs/" + businessFunction.getId());
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
