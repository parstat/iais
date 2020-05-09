package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;

@Service
public class StatisticalStandardQueryService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;

    /**
     * Method to get all statistical standards or many statistical standards
     * filtered by type, name
     *
     * @param query to request
     * @return GetStatisticalStandardsQuery with the DTOList of requested
     *         statistical standards
     */
    public GetStatisticalStandardsQuery getStatisticalStandards(final GetStatisticalStandardsQuery query) {

        if (StringTools.isNotEmpty(query.getName())) {
            Translator.translateStatisticalStandards(statisticalStandardReferenceRepository
                            .findAllByNameInLanguageContaining(query.getLanguage().getShortName(), query.getName()),
                    query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }
        if (query.getType() != null) {
            Translator.translateStatisticalStandards(statisticalStandardReferenceRepository.findByType(query.getType()),
                    query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }

        Translator.translateStatisticalStandards(statisticalStandardReferenceRepository.findAll(), query.getLanguage())
                .ifPresent(query.getRead()::setData);

        return query;
    }

    /**
     * Method to get a single statistical standards by id
     *
     * @param query to request
     * @return GetStatisticalStandardQuery including requested statistical standards
     *         dto in the read
     */
    public GetStatisticalStandardQuery getStatisticalStandard(final GetStatisticalStandardQuery query) {

        statisticalStandardReferenceRepository.findById(query.getId()).flatMap(ss -> translate(ss, query.getLanguage()))
                .ifPresent(query.getRead()::setData);

        return query;
    }
}
