package com.nbs.iais.ms.meta.referential.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.quality.GetProcessQualitiesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.quality.GetProcessQualityQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessQualityRepository;

@Service
public class ProcessQualityQueryService {

	@Autowired
	private ProcessQualityRepository processQualityRepository;

	/**
	 * Method to get all process quality indicators or many process quality indicators  filtered by
	 * processDocumentation
	 *
	 * @param query to request
	 * @return GetProcessQualitiesQuery with the DTOList of requested process
	 *         documents
	 */
	public GetProcessQualitiesQuery getProcessQualitiesQuery(final GetProcessQualitiesQuery query) {

		if (query.getProcessDocumentation() != null) {

			Translator
					.translateProcessQualities(
							processQualityRepository.findByProcessDocumentation(
									new ProcessDocumentationEntity(query.getProcessDocumentation())),
							query.getLanguage())
					.ifPresent(query.getRead()::setData);
			return query;
		}

		Translator.translateProcessQualities(processQualityRepository.findAll(), query.getLanguage())
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single process quality by id
	 *
	 * @param query to request
	 * @return GetProcessDocumentQuery including requested process documents dto in
	 *         the read
	 */
	public GetProcessQualityQuery getProcessQualityQuery(final GetProcessQualityQuery query) {

		processQualityRepository.findById(query.getId()).flatMap(ss -> Translator.translate(ss, query.getLanguage()))
				.ifPresent(query.getRead()::setData);

		return query;
	}
}
