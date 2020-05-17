package com.nbs.iais.ms.meta.referential.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.input.specification.GetProcessInputSpecificationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.input.specification.GetProcessInputSpecificationsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessInputSpecificationRepository;

@Service
public class ProcessInputSpecificationQueryService {

	@Autowired
	private ProcessInputSpecificationRepository processInputSpecificationRepository;

	/**
	 * Method to get all process InputSpecifications or many process
	 * InputSpecifications filtered by processDocumentation
	 *
	 * @param query to request
	 * @return GetProcessInputSpecificationsQuery with the DTOList of requested
	 *         process InputSpecifications
	 */
	public GetProcessInputSpecificationsQuery getProcessInputSpecifications(
			final GetProcessInputSpecificationsQuery query) {

		if (query.getProcessDocumentation() != null) {

			Translator
					.translateInputSpecifications(
							processInputSpecificationRepository.findByProcessDocumentation(
									new ProcessDocumentationEntity(query.getProcessDocumentation())),
							query.getLanguage())
					.ifPresent(query.getRead()::setData);
			return query;
		}

		Translator.translateInputSpecifications(processInputSpecificationRepository.findAll(), query.getLanguage())
				.ifPresent(query.getRead()::setData);

		return query;
	}

	/**
	 * Method to get a single process InputSpecifications by id
	 *
	 * @param query to request
	 * @return GetProcessInputSpecificationQuery including requested process
	 *         InputSpecifications dto in the read
	 */
	public GetProcessInputSpecificationQuery getProcessInputSpecification(
			final GetProcessInputSpecificationQuery query) {

		processInputSpecificationRepository.findById(query.getId())
				.flatMap(ss -> Translator.translate(ss, query.getLanguage())).ifPresent(query.getRead()::setData);

		return query;
	}
}
