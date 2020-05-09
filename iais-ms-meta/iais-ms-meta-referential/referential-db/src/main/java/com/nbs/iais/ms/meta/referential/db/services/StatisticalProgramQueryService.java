package com.nbs.iais.ms.meta.referential.db.services;


import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;

@Service
public class StatisticalProgramQueryService {

    @Autowired
    private StatisticalProgramRepository statisticalProgramRepository;

    @Autowired
    private AgentRepository agentRepository;

    /**
     * Method to get many statistical programs
     *
     * @param query to request
     * @return GetStatisticalProgramsQuery including DTOList of the requested
     *         Statistical Programs
     */
    public GetStatisticalProgramsQuery getStatisticalPrograms(final GetStatisticalProgramsQuery query) {

        if (query.getLocalId() != null) {
            final Iterable<StatisticalProgramEntity> statisticalPrograms = statisticalProgramRepository
                    .findAllByLocalId(query.getLocalId());
            translate(statisticalPrograms, query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }

        if (StringTools.isNotEmpty(query.getName())) {
            final Iterable<StatisticalProgramEntity> sp = statisticalProgramRepository
                    .findAllByNameInLanguageContaining(query.getLanguage().getShortName(), query.getName());
            translate(sp, query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }
        if (query.getMaintainer() != null) {
            agentRepository.findById(query.getMaintainer())
                    .flatMap(agent -> translate(
                            statisticalProgramRepository.findAllByAgentInRole(agent, RoleType.MAINTAINER),
                            query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
            return query;
        }
        // return all if no parameter has been provided to the query
        final Iterable<StatisticalProgramEntity> statisticalPrograms = statisticalProgramRepository.findAll();
        translate(statisticalPrograms, query.getLanguage()).ifPresent(query.getRead()::setData);
        return query;
    }


    /**
     * Method to get single statistical program
     *
     * @param query to request
     * @return GetStatisticalProgramQuery including the requested statistical
     *         program dto in the read
     */
    public GetStatisticalProgramQuery getStatisticalProgram(final GetStatisticalProgramQuery query) {

        if (query.getId() != null) { // get by id
            statisticalProgramRepository.findById(query.getId()).flatMap(sp -> translate(sp, query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
            return query;
        }
        // get by localId and version
        if (StringTools.isNotEmpty(query.getLocalId()) && StringTools.isNotEmpty(query.getVersion())) {
            statisticalProgramRepository.findByLocalIdAndVersion(query.getLocalId(), query.getVersion())
                    .flatMap(sp -> translate(sp, query.getLanguage())).ifPresent(query.getRead()::setData);
            return query;
        }

        // get latest version
        statisticalProgramRepository.findAllTopByLocalIdOrderByVersionDateDesc(query.getLocalId())
                .flatMap(sp -> translate(sp, query.getLanguage())).ifPresent(query.getRead()::setData);

        return query;
    }
}
