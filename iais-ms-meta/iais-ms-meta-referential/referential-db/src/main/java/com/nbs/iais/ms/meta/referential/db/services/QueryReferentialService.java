package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;

import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramQuery;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;

@Service
public class QueryReferentialService {

	@Autowired
    private StatisticalProgramRepository statisticalProgramRepository;

	@Autowired
	private BusinessFunctionRepository businessFunctionRepository;

	public GetStatisticalProgramsQuery getStatisticalPrograms(final GetStatisticalProgramsQuery query) {

		final Iterable<StatisticalProgramEntity> statisticalPrograms = statisticalProgramRepository.findAll();
		translate(statisticalPrograms, query.getLanguage())
				.ifPresent(query.getRead()::setData);
		return query;
	}

	public GetStatisticalProgramQuery getStatisticalProcess(final GetStatisticalProgramQuery query) {

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

}
