package com.nbs.iais.ms.meta.referential.db.services;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;
import static com.nbs.iais.ms.common.db.domains.translators.Translator.translateAgents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;

@Service
public class ProcessDocumentationQueryService {

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
	
	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;



	// Agent section




	/**
	 * Method to get all process documentations or many process documentations
	 * filtered by frequency, name
	 * 
	 * @param query to request
	 * @return GetProcessDocumentationsQuery with the DTOList of requested
	 *         process documentations
	 */
	public GetProcessDocumentationsQuery getProcessDocumentations(final GetProcessDocumentationsQuery query) {

		if (StringTools.isNotEmpty(query.getName())) {
			Translator.translateProcessDocumentations(processDocumentationRepository
					.findAllByNameInLanguageContaining(query.getLanguage().getShortName(), query.getName()),
					query.getLanguage()).ifPresent(query.getRead()::setData);
			return query;
		}
		if (query.getFrequency() != null) {
			Translator.translateProcessDocumentations(processDocumentationRepository.findByFrequency(query.getFrequency()),
					query.getLanguage()).ifPresent(query.getRead()::setData);
			return query;
		}
		Translator.translateProcessDocumentations(processDocumentationRepository.findAll(), query.getLanguage())
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single process documentations by id
	 * 
	 * @param query to request
	 * @return GetProcessDocumentationQuery including requested process documentations
	 *         dto in the read
	 */
	public GetProcessDocumentationQuery getProcessDocumentation(final GetProcessDocumentationQuery query) {

		processDocumentationRepository.findById(query.getId()).flatMap(ss -> translate(ss, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}

	
}
