package com.nbs.iais.ms.meta.referential.db.services;


import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;

@Service
public class LegislativeReferenceQueryService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private LegislativeReferenceRepository legislativeReferenceRepository;

    /**
     * Method to get all legislative references or many legislative references
     * filtered by type, name
     *
     * @param query to request
     * @return GetLegislativeReferencesQuery with the DTOList of requested
     *         legislative references
     */
    public GetLegislativeReferencesQuery getLegislativeReferences(final GetLegislativeReferencesQuery query) {

        if (StringTools.isNotEmpty(query.getName())) {
            Translator
                    .translateLegislativeReferences(legislativeReferenceRepository.findAllByNameInLanguageContaining(
                            query.getLanguage().getShortName(), query.getName()), query.getLanguage())
                    .ifPresent(query.getRead()::setData);
            return query;
        }
        if (query.getType() != null) {
            Translator.translateLegislativeReferences(legislativeReferenceRepository.findByType(query.getType()),
                    query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }

        Translator.translateLegislativeReferences(legislativeReferenceRepository.findAll(), query.getLanguage())
                .ifPresent(query.getRead()::setData);

        return query;
    }

    /**
     * Method to get a single legislative references by id
     *
     * @param query to request
     * @return GetLegislativeReferenceQuery including requested legislative
     *         references dto in the read
     */
    public GetLegislativeReferenceQuery getLegislativeReference(final GetLegislativeReferenceQuery query) {

        legislativeReferenceRepository.findById(query.getId()).flatMap(ss -> translate(ss, query.getLanguage()))
                .ifPresent(query.getRead()::setData);

        return query;
    }

}
