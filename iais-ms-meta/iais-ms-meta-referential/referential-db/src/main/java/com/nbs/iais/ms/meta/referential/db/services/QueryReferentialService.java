package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;

import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramQuery;

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


	public GetStatisticalProgramsQuery getStatisticalPrograms(final GetStatisticalProgramsQuery query) {

		final Iterable<StatisticalProgramEntity> statisticalPrograms = statisticalProgramRepository.findAll();
		translate(statisticalPrograms, query.getLanguage())
				.ifPresent(query.getRead()::setData);
		return query;
	}

	public GetStatisticalProgramQuery getStatisticalProgram(final GetStatisticalProgramQuery query) {

		statisticalProgramRepository.findById(query.getId())
				.flatMap(sp -> translate(sp, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}

	public GetBusinessFunctionQuery getBusinessFunction(final GetBusinessFunctionQuery query) {
		if(query.getId() != null) {
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
	public GetAgentsQuery getAgents(final GetAgentsQuery query) {

		final Iterable<AgentEntity> agents = agentRepository.findAll();
		translateAgents(agents, query.getLanguage())
				.ifPresent(query.getRead()::setData);
		return query;
	}

	public GetAgentQuery getAgent(final GetAgentQuery query) {

		agentRepository.findById(query.getId())
				.flatMap(sp -> translate(sp, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}

}
