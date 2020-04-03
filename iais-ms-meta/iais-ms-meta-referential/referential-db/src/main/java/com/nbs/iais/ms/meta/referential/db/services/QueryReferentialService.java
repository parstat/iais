package com.nbs.iais.ms.meta.referential.db.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.meta.referential.db.domains.StatisticalProcessEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProcessRepository;
import com.nbs.iais.ms.meta.referential.db.utils.Translator;

import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessQuery;
import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessesQuery;

@Service
public class QueryReferentialService {

	@Autowired
	StatisticalProcessRepository statisticalProcessRepository;

	public GetStatisticalProcessesQuery getStatisticalProcesses(final GetStatisticalProcessesQuery query) {

		final Iterable<StatisticalProcessEntity> sProcesses = statisticalProcessRepository.findAll();
		query.getRead().setData(Translator.translate(sProcesses));
		return query;
	}

	public GetStatisticalProcessQuery getStatisticalProcess(final GetStatisticalProcessQuery query) {

		final Optional<StatisticalProcessEntity> statisticalProcessEntity = statisticalProcessRepository
				.findById(query.getId());
		if (statisticalProcessEntity.isPresent()) {
			query.getRead().setData(Translator.translate(statisticalProcessEntity.get()));
		} else
			query.getRead().setData(null);
		return query;
	}

}
