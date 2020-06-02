package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessMethodQueryService {

    @Autowired
    private ProcessMethodRepository processMethodRepository;

    /**
     * Method to get a processMethod by id
     * @param query the query to use for requesting
     * @return GetProcessMethodQuery including the requested processMethod in the read property
     */
    public GetProcessMethodQuery getProcessMethod(final GetProcessMethodQuery query) {

        processMethodRepository.findById(query.getId())
                .flatMap(pm -> Translator.translate(pm, query.getLanguage())).ifPresent(query.getRead()::setData);

        return query;
    }

    /**
     * Method to get many process method (all or filtered by name)
     * @param query the query get the process methods
     * @return GetProcessMethodsQuery including a DTOList in the read property
     */
    public GetProcessMethodsQuery getProcessMethods(final GetProcessMethodsQuery query) {

        if(StringTools.isNotEmpty(query.getName())) {
            Translator.translateProcessMethods(processMethodRepository
                    .findAllByNameInLanguageContaining(query.getLanguage().getShortName(), query.getName())
                    , query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }
        //return all
        Translator.translateProcessMethods(processMethodRepository.findAll(), query.getLanguage())
                .ifPresent(query.getRead()::setData);
        return query;
    }


}
