package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;
import static com.nbs.iais.ms.common.db.domains.translators.Translator.translateAgents;

import java.util.List;

@Service
public class QueryReferentialService {

	@Autowired
    private StatisticalProgramRepository statisticalProgramRepository;

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private BusinessFunctionRepository businessFunctionRepository;
	
	@Autowired
	private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;
	
	@Autowired
	private LegislativeReferenceRepository legislativeReferenceRepository;


	/**
	 * Method to get many statistical programs
	 * @param query to request
	 * @return GetStatisticalProgramsQuery including DTOList of the requested Statistical Programs
	 */
	public GetStatisticalProgramsQuery getStatisticalPrograms(final GetStatisticalProgramsQuery query) {

		if (query.getLocalId() != null) {
			final Iterable<StatisticalProgramEntity> statisticalPrograms =
					statisticalProgramRepository.findAllByLocalId(query.getLocalId());
			translate(statisticalPrograms, query.getLanguage()).ifPresent(query.getRead()::setData);
			return query;
		}

		if(StringTools.isNotEmpty(query.getName())) {
			final Iterable<StatisticalProgramEntity> sp = statisticalProgramRepository
					.findAllByNameInLanguageContaining(query.getLanguage().getShortName(), query.getName());
			translate(sp, query.getLanguage()).ifPresent(query.getRead()::setData);
			return query;
		}
		if(query.getMaintainer() != null) {
			agentRepository.findById(query.getMaintainer()).flatMap(agent ->
					translate(statisticalProgramRepository.findAllByAgentInRole(agent, RoleType.MAINTAINER),
					query.getLanguage())).ifPresent(query.getRead()::setData);
			return query;
		}
		//return all if no parameter has been provided to the query
		final Iterable<StatisticalProgramEntity> statisticalPrograms = statisticalProgramRepository.findAll();
		translate(statisticalPrograms, query.getLanguage())
				.ifPresent(query.getRead()::setData);
		return query;
	}

	/**
	 * Method to get single statistical program
	 * @param query to request
	 * @return GetStatisticalProgramQuery including the requested statistical program dto in the read
	 */
	public GetStatisticalProgramQuery getStatisticalProgram(final GetStatisticalProgramQuery query) {

		if(query.getId() != null) { //get by id
			statisticalProgramRepository.findById(query.getId())
					.flatMap(sp -> translate(sp, query.getLanguage()))
					.ifPresent(query.getRead()::setData);
			return query;
		}
		//get by localId and version
		if(StringTools.isNotEmpty(query.getLocalId()) && StringTools.isNotEmpty(query.getVersion())) {
			statisticalProgramRepository.findByLocalIdAndVersion(query.getLocalId(), query.getVersion())
					.flatMap(sp -> translate(sp, query.getLanguage()))
					.ifPresent(query.getRead()::setData);
			return query;
		}

		//get latest version
		statisticalProgramRepository.findAllTopByLocalIdOrderByVersionDateDesc(query.getLocalId())
				.flatMap(sp -> translate(sp, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single business function
	 * @param query to request
	 * @return GetBusinessFunctionQuery
	 */
	public GetBusinessFunctionQuery getBusinessFunction(final GetBusinessFunctionQuery query) {

		if(query.getId() != null) { //get by id
			businessFunctionRepository.findById(query.getId())
					.flatMap(bf -> Translator.translate(bf, query.getLanguage()))
					.ifPresent(query.getRead()::setData);
			return query;
		}
		businessFunctionRepository.findByLocalIdAndVersion(query.getLocalId(), query.getVersion())
					.flatMap(bf -> Translator.translate(bf, query.getLanguage()))
					.ifPresent(query.getRead()::setData);
		return query;
	}

	/**
	 * Method to get many business functions
	 * @param query to request
	 * @return GetBusinessFunctionsQuery
	 */
	public GetBusinessFunctionsQuery getBusinessFunctions(final GetBusinessFunctionsQuery query) {
		if(StringTools.isNotEmpty(query.getName())) {
			Translator.translateBusinessFunctions(businessFunctionRepository
					.findAllByNameInLanguageContaining(query.getLanguage().getShortName(), query.getName()), query.getLanguage())
					.ifPresent(query.getRead()::setData);
		}

		if(query.getPhase() > 0 && query.getPhase() <= 8) {
			Translator.translateBusinessFunctions(businessFunctionRepository
					.findAllByLocalIdStartingWith(String.valueOf(query.getPhase())), query.getLanguage())
					.ifPresent(query.getRead()::setData);
		}

		return query;
	}

	// Agent section

		/**
		 * Method to get all agents or many agents filtered by agentType, parent, name
		 * valued
		 * 
		 * @param query to request
		 * @return GetAgentsQuery with the DTOList of requested agents
		 */
		public GetAgentsQuery getAgents(final GetAgentsQuery query) {
			final AgentEntity parent = (query.getParent() == null ? null
					: agentRepository.findById(query.getParent()).orElse(null));
			final Iterable<AgentEntity> agents = agentRepository.findAllOrBySelectedNameAndTypeAndParent(query.getAgentType(),
					parent, query.getName(), query.getLanguage().getShortName());
			translateAgents(agents, query.getLanguage()).ifPresent(query.getRead()::setData);
			return query;
		}

		/**
		 * Method to get a single agent by id, accountId and   by email (localId)
		 * 
		 * @param query to request
		 * @return GetAgentQuery including requested agent dto in the read
		 */
		public GetAgentQuery getAgent(final GetAgentQuery query) {
			if (query.getId() != null) {
				agentRepository.findById(query.getId()).flatMap(sp -> translate(sp, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
			if (query.getAccountId() != null) {
				agentRepository.findByAccount(query.getAccountId()).flatMap(sp -> translate(sp, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
			if (StringTools.isNotEmpty(query.getLocalId())) {
				agentRepository.findByLocalId(query.getLocalId()).flatMap(sp -> translate(sp, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
			return query;
		}

		

		/** 
		 * Method to get all statistical standards or many statistical standards filtered by type, name  values
		 * 
		 * @param query to request
		 * @return GetStatisticalStandardsQuery with the DTOList of requested statistical standards
		 */
		public GetStatisticalStandardsQuery getStatisticalStandards(final GetStatisticalStandardsQuery query) {
			//FIXME if filter is combined and not exclusive keep it like this, else divide in two
			final List<StatisticalStandardReferenceEntity> standards =  statisticalStandardReferenceRepository.findAllOrBySelectedNameAndType(query.getType(),
					 query.getName(), query.getLanguage().getShortName());
			Translator.translateStatisticalStandards(standards, query.getLanguage()).ifPresent(query.getRead()::setData);
			
			return query;
		}

		/** 
		 * Method to get a single statistical standards by id or localId
		 * 
		 * @param query to request
		 * @return GetStatisticalStandardQuery including requested  statistical standards dto in the read
		 */
		public GetStatisticalStandardQuery getStatisticalStandard(final GetStatisticalStandardQuery query) {
			if (query.getId() != null) {
				statisticalStandardReferenceRepository.findById(query.getId()).flatMap(ss -> translate(ss, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
			//FIXME combine it with version or just remove it
		 if (StringTools.isNotEmpty(query.getLocalId())) {
			 statisticalStandardReferenceRepository.findByLocalId(query.getLocalId()).flatMap(ss -> translate(ss, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
			return query;
		}

		
		/**  FIXME FRANCESCO
		 * Method to get all legislative references or many legislative references filtered by type, name  valued
		 * 
		 * @param query to request
		 * @return GetLegislativeReferencesQuery with the DTOList of requested legislative references
		 */
		public GetLegislativeReferencesQuery getLegislativeReferences(final GetLegislativeReferencesQuery query) {

			final List<LegislativeReferenceEntity> references =  legislativeReferenceRepository.findAllOrBySelectedNameAndType(query.getType(),
					 query.getName(), query.getLanguage().getShortName());
			Translator.translateLegislativeReferences(references, query.getLanguage()).ifPresent(query.getRead()::setData);
			
			return query;
		}

		/**  FIXME FRANCESCO
		 * Method to get a single legislative references by id or localId
		 * 
		 * @param query to request
		 * @return GetLegislativeReferenceQuery including requested  legislative references dto in the read
		 */
		public GetLegislativeReferenceQuery getLegislativeReference(final GetLegislativeReferenceQuery query) {
			if (query.getId() != null) {
				legislativeReferenceRepository.findById(query.getId()).flatMap(ss -> translate(ss, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
		 if (StringTools.isNotEmpty(query.getLocalId())) {
			 legislativeReferenceRepository.findByLocalId(query.getLocalId()).flatMap(ss -> translate(ss, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
			}
			return query;
		}	
}
