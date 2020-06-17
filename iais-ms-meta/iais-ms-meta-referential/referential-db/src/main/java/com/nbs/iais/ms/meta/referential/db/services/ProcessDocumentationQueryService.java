package com.nbs.iais.ms.meta.referential.db.services;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;

@Service
public class ProcessDocumentationQueryService {

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;


	/**
	 * Method to get all process documentations
	 * 
	 * @param query to request
	 * @return GetProcessDocumentationsQuery with the DTOList of requested process
	 *         documentations
	 */
	public GetProcessDocumentationsQuery getProcessDocumentations(final GetProcessDocumentationsQuery query) {

		Translator.translateProcessDocumentations(processDocumentationRepository.findAll(), query.getLanguage())
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single process documentations by id
	 * 
	 * @param query to request
	 * @return GetProcessDocumentationQuery including requested process
	 *         documentations dto in the read
	 */
	public GetProcessDocumentationQuery getProcessDocumentation(final GetProcessDocumentationQuery query) {

		processDocumentationRepository.findById(query.getId()).flatMap(pd -> translate(pd, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}

}
