package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service.GetBusinessServiceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service.GetBusinessServicesQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceQueryService {

    private final static Logger LOG = LoggerFactory.getLogger(BusinessServiceQueryService.class);

    @Autowired
    private BusinessServiceRepository businessServiceRepository;


    public GetBusinessServiceQuery getBusinessService(final GetBusinessServiceQuery query) {

        if(query.getId() != null) {
            businessServiceRepository.findById(query.getId()).flatMap(businessServiceEntity ->
                    Translator.translate(businessServiceEntity, query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
            return query;
        }

        if(StringTools.isNotEmpty(query.getLocalId()) && StringTools.isNotEmpty(query.getVersion())) {
            businessServiceRepository.findByLocalIdAndVersion(query.getLocalId(), query.getVersion())
                    .flatMap(businessServiceEntity ->
                            Translator.translate(businessServiceEntity, query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
            return query;
        }

        businessServiceRepository.findAllTopByLocalIdOrderByVersionDateDesc(query.getLocalId())
                .flatMap(businessServiceEntity -> Translator.translate(businessServiceEntity, query.getLanguage()))
                .ifPresent(query.getRead()::setData);
        return query;
    }

    public GetBusinessServicesQuery getBusinessServices(final GetBusinessServicesQuery query) {

        if(StringTools.isNotEmpty(query.getLocalId())) {
            Translator.translateBusinessServices(businessServiceRepository.findAllByLocalId(query.getLocalId()),
                    query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }

        if(StringTools.isNotEmpty(query.getName())) {
            Translator.translateBusinessServices(businessServiceRepository.findAllByNameInLanguageContaining(
                    query.getLanguage().getShortName(), query.getName()), query.getLanguage())
                    .ifPresent(query.getRead()::setData);
            return query;
        }

        Translator.translateBusinessServices(businessServiceRepository.findAll(), query.getLanguage())
                .ifPresent(query.getRead()::setData);
        return query;
    }
}
