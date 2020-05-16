package com.nbs.iais.ms.meta.referential.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;

@Service
public class BusinessFunctionQueryService {

  
    @Autowired
    private BusinessFunctionRepository businessFunctionRepository;

    /**
     * Method to get a single business function
     *
     * @param query to request
     * @return GetBusinessFunctionQuery
     */
    public GetBusinessFunctionQuery getBusinessFunction(final GetBusinessFunctionQuery query) {

        if (query.getId() != null) { // get by id
            businessFunctionRepository.findById(query.getId())
                    .flatMap(bf -> Translator.translate(bf, query.getLanguage())).ifPresent(query.getRead()::setData);
            return query;
        }
        businessFunctionRepository.findByLocalIdAndVersion(query.getLocalId(), query.getVersion())
                .flatMap(bf -> Translator.translate(bf, query.getLanguage())).ifPresent(query.getRead()::setData);
        return query;
    }

    /**
     * Method to get many business functions
     *
     * @param query to request
     * @return GetBusinessFunctionsQuery
     */
    public GetBusinessFunctionsQuery getBusinessFunctions(final GetBusinessFunctionsQuery query) {
        if (StringTools.isNotEmpty(query.getName())) {
            Translator
                    .translateBusinessFunctions(businessFunctionRepository.findAllByNameInLanguageContaining(
                            query.getLanguage().getShortName(), query.getName()), query.getLanguage())
                    .ifPresent(query.getRead()::setData);
        }

        if (query.getPhase() > 0 && query.getPhase() <= 8) {
            Translator.translateBusinessFunctions(
                    businessFunctionRepository.findAllByLocalIdStartingWith(String.valueOf(query.getPhase())),
                    query.getLanguage()).ifPresent(query.getRead()::setData);
        }

        return query;
    }
}
