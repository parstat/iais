package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.db.repositories.AgentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nbs.iais.ms.common.db.domains.translators.Translator.translate;
import static com.nbs.iais.ms.common.db.domains.translators.Translator.translateAgents;


@Service
public class AgentQueryService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private AgentRepository agentRepository;

    /**
     * Method to get all agents or many agents filtered by agentType, parent, name
     * valued
     *
     * @param query to request
     * @return GetAgentsQuery with the DTOList of requested agents
     */
    public GetAgentsQuery getAgents(final GetAgentsQuery query) {

        if (StringTools.isNotEmpty(query.getName())) {
            translateAgents(agentRepository.findAllByNameInLanguageContaining(query.getLanguage().getShortName(),
                    query.getName()), query.getLanguage()).ifPresent(query.getRead()::setData);
            return query;
        }
        if (query.getAgentType() != null) {
            translateAgents(agentRepository.findByType(query.getAgentType()), query.getLanguage())
                    .ifPresent(query.getRead()::setData);
            return query;
        }

        if (query.getParent() != null) {
            agentRepository.findById(query.getParent())
                    .flatMap(parent -> translateAgents(agentRepository.findByParent(parent), query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
            return query;
        }

        translateAgents(agentRepository.findAll(), query.getLanguage()).ifPresent(query.getRead()::setData);

        return query;
    }

    /**
     * Method to get a single agent by id, accountId and by email (localId)
     *
     * @param query to request
     * @return GetAgentQuery including requested agent dto in the read
     */
    public GetAgentQuery getAgent(final GetAgentQuery query) {
        if (query.getId() != null) {
            agentRepository.findById(query.getId()).flatMap(sp -> translate(sp, query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
        }
        if (query.getAccountId() != null) {
            agentRepository.findByAccount(query.getAccountId()).flatMap(sp -> translate(sp, query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
        }
        if (StringTools.isNotEmpty(query.getLocalId())) {
            agentRepository.findByLocalId(query.getLocalId()).flatMap(sp -> translate(sp, query.getLanguage()))
                    .ifPresent(query.getRead()::setData);
        }
        return query;
    }
}
