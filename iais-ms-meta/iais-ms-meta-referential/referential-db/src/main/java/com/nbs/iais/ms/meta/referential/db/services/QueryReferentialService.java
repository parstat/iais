package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;

import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;
import static com.nbs.iais.ms.common.db.domains.translators.Translator.translateAgents;

@Service
public class QueryReferentialService {

	@Autowired
    private StatisticalProgramRepository statisticalProgramRepository;

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private BusinessFunctionRepository businessFunctionRepository;




	/**
	 * FIXME FRANCESCO add different filters as listed in the query
	 * Method to get many statistical programs
	 * @param query to request
	 * @return GetStatisticalProgramsQuery including DTOList of the requested Statistical Programs
	 */
	public GetStatisticalProgramsQuery getStatisticalPrograms(final GetStatisticalProgramsQuery query) {

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
	 * FIXME FRANCESCO add different filters, agentType, parent, name
	 * Method to get many agents
	 * @param query to request
	 * @return GetAgentsQuery with the DTOList of requested agents
	 */
	public GetAgentsQuery getAgents(final GetAgentsQuery query) {

		final Iterable<AgentEntity> agents = agentRepository.findAll();
		translateAgents(agents, query.getLanguage())
				.ifPresent(query.getRead()::setData);
		return query;
	}

	/**
	 * FIXME FRANCESCO add also by accountId and/or (not sure) by email (localId)
	 * Method to get a single agent
	 * @param query to request
	 * @return GetAgentQuery including requested agent dto in the read
	 */
	public GetAgentQuery getAgent(final GetAgentQuery query) {

		agentRepository.findById(query.getId())
				.flatMap(sp -> translate(sp, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}

}
