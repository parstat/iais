package com.nbs.iais.ms.meta.referential.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.output.specification.GetProcessOutputSpecificationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.output.specification.GetProcessOutputSpecificationsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessOutputSpecificationRepository;

@Service
public class ProcessOutputSpecificationQueryService {

	@Autowired
	private ProcessOutputSpecificationRepository processOutputSpecificationRepository;

	/**
	 * Method to get all process OutputSpecifications or many process
	 * OutputSpecifications filtered by processDocumentation
	 *
	 * @param query to request
	 * @return GetProcessOutputSpecificationsQuery with the DTOList of requested
	 *         process OutputSpecifications
	 */
	public GetProcessOutputSpecificationsQuery getProcessOutputSpecifications(
			final GetProcessOutputSpecificationsQuery query) {

		if (query.getProcessDocumentation() != null) {

			Translator
					.translateOutputSpecifications(
							processOutputSpecificationRepository.findByProcessDocumentation(
									new ProcessDocumentationEntity(query.getProcessDocumentation())),
							query.getLanguage())
					.ifPresent(query.getRead()::setData);
			return query;
		}

		Translator.translateOutputSpecifications(processOutputSpecificationRepository.findAll(), query.getLanguage())
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single process OutputSpecifications by id
	 *
	 * @param query to request
	 * @return GetProcessOutputSpecificationQuery including requested process
	 *         OutputSpecifications dto in the read
	 */
	public GetProcessOutputSpecificationQuery getProcessOutputSpecification(
			final GetProcessOutputSpecificationQuery query) {

		processOutputSpecificationRepository.findById(query.getId())
				.flatMap(ss -> Translator.translate(ss, query.getLanguage())).ifPresent(query.getRead()::setData);

		return query;
	}
}
