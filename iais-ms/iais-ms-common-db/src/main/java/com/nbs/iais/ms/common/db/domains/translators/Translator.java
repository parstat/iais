package com.nbs.iais.ms.common.db.domains.translators;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessFunction;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.*;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.PhaseName;
import com.nbs.iais.ms.common.enums.RoleType;


import java.util.Collection;
import java.util.Optional;

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
			if(agentInRole.getRole() == RoleType.OWNER) {
				translateMini(agentInRole.getAgent(), language).ifPresent(statisticalProgramDTO::setOwner);
			}
			if(agentInRole.getRole() == RoleType.MAINTAINER) {
				translateMini(agentInRole.getAgent(), language).ifPresent(statisticalProgramDTO::setMaintainer);
			}
			if(agentInRole.getRole() == RoleType.CONTACT) {
				translateMini(agentInRole.getAgent(), language).ifPresent(statisticalProgramDTO::setContact);
			}
		});

		translateLegislativeReferences(statisticalProgram.getLegislativeReference(), language)
				.ifPresent(statisticalProgramDTO::setLegislativeReferences);

		translateStatisticalStandards(statisticalProgram.getStatisticalStandardReferences(), language)
				.ifPresent(statisticalProgramDTO::setStatisticalStandards);


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

		if(agent == null) {
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

	public static <SS extends StatisticalStandardReference> Optional<DTOList<StatisticalStandardDTO>> translateStatisticalStandards(final Collection<SS> statisticalStandards, final Language language) {

		if(statisticalStandards == null || statisticalStandards.size() == 0) {
			return Optional.empty();
		}

		final DTOList<StatisticalStandardDTO> statisticalStandardDTOS = DTOList.empty(StatisticalStandardDTO.class);

		statisticalStandards.forEach(ss -> translate(ss, language)
				.ifPresent(statisticalStandardDTOS::add));

		return Optional.of(statisticalStandardDTOS);
	}



	public static <LR extends LegislativeReference> Optional<LegislativeReferenceDTO> translate(final LR legislativeReference,
																					  final Language language) {

		if(legislativeReference == null) {
			return Optional.empty();
		}

		final LegislativeReferenceDTO legislativeReferenceDTO = new LegislativeReferenceDTO(legislativeReference.getId());
		legislativeReferenceDTO.setApproval(legislativeReference.getApprovalDate());
		legislativeReferenceDTO.setNumber(legislativeReference.geNumber());
		legislativeReferenceDTO.setName(legislativeReference.getName(language));
		legislativeReferenceDTO.setDescription(legislativeReference.getDescription(language));
		legislativeReferenceDTO.setType(legislativeReference.getLegislativeType());
		legislativeReferenceDTO.setLocalId(legislativeReference.getLocalId());
		legislativeReferenceDTO.setVersion(legislativeReference.getVersion());
		legislativeReferenceDTO.setVersionDate(legislativeReference.getVersionDate());
		legislativeReferenceDTO.setVersionRationale(legislativeReference.getVersionRationale());
		legislativeReferenceDTO.setLink("/legislative/references/" + legislativeReference.getId().toString());

		return Optional.of(legislativeReferenceDTO);
	}

	public static <LR extends LegislativeReference> Optional<DTOList<LegislativeReferenceDTO>> translateLegislativeReferences(
			final Collection<LR> legislativeReferences, final Language language) {
		if(legislativeReferences == null || legislativeReferences.size() == 0) {
			return Optional.empty();
		}

		final DTOList<LegislativeReferenceDTO> legislativeReferenceDTOS = DTOList.empty(LegislativeReferenceDTO.class);

		legislativeReferences.forEach(lr ->
			translate(lr, language).ifPresent(legislativeReferenceDTOS::add));

		return Optional.of(legislativeReferenceDTOS);
	}
}
