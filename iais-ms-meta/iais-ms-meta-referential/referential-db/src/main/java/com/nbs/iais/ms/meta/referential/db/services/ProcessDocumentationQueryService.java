package com.nbs.iais.ms.meta.referential.db.services;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;

import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;

@Service
public class ProcessDocumentationQueryService {

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;

	@Autowired
	private BusinessFunctionRepository businessFunctionRepository;

	@Autowired
	private StatisticalProgramRepository statisticalProgramRepository;


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

		if(query.getId() != null) {
			processDocumentationRepository.findById(query.getId()).flatMap(pd -> translate(pd, query.getLanguage()))
					.ifPresent(query.getRead()::setData);
		}

		if(query.getBusinessFunction() != null && query.getStatisticalProgram() != null) {
			final StatisticalProgramEntity statisticalProgram = statisticalProgramRepository
					.findById(query.getStatisticalProgram()).orElseThrow(()
							-> new EntityException(ExceptionCodes.STATISTICAL_PROGRAM_NOT_FOUND));
			final BusinessFunctionEntity businessFunction = businessFunctionRepository
					.findById(query.getBusinessFunction()).orElseThrow(() ->
							new EntityException(ExceptionCodes.BUSINESS_FUNCTION_NOT_FOUND));
			processDocumentationRepository.findAllTopByStatisticalProgramAndBusinessFunctionOrderByVersionDateDesc(statisticalProgram, businessFunction)
					.flatMap(processDocumentation -> translate(processDocumentation, query.getLanguage()))
						.ifPresent(query.getRead()::setData);
		}
		return query;
	}

}
