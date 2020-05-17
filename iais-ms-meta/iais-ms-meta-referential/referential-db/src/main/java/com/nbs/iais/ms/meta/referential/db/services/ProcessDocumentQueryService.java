package com.nbs.iais.ms.meta.referential.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentRepository;

@Service
public class ProcessDocumentQueryService {

	@Autowired
	private ProcessDocumentRepository processDocumentRepository;

	/**
	 * Method to get all process documents or many process documents filtered by
	 * processDocumentation
	 *
	 * @param query to request
	 * @return GetProcessDocumentsQuery with the DTOList of requested process
	 *         documents
	 */
	public GetProcessDocumentsQuery getProcessDocuments(final GetProcessDocumentsQuery query) {

		if (query.getProcessDocumentation() != null) {

			Translator
					.translateProcessDocuments(
							processDocumentRepository.findByProcessDocumentation(
									new ProcessDocumentationEntity(query.getProcessDocumentation())),
							query.getLanguage())
					.ifPresent(query.getRead()::setData);
			return query;
		}

		Translator.translateProcessDocuments(processDocumentRepository.findAll(), query.getLanguage())
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single process documents by id
	 *
	 * @param query to request
	 * @return GetProcessDocumentQuery including requested process documents dto in
	 *         the read
	 */
	public GetProcessDocumentQuery getProcessDocument(final GetProcessDocumentQuery query) {

		processDocumentRepository.findById(query.getId()).flatMap(ss -> Translator.translate(ss, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}
}
