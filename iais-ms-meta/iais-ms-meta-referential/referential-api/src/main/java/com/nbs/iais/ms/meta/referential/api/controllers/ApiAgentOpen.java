package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/referential/agents", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiAgentOpen extends AbstractController {


    /**
     * Method to get many agents in the selected language
     *
     * @param name     the name to search the agents
     * @param type     the type of agent: DIVISION, ORGANIZATION, DEPARTMENT,
     *                 INDIVIDUAL
     * @param parent   the agent id to return all children
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return a list of filtered agents in the selected language all agents if no
     *         filter parameter has been provided
     */
    @JsonView(Views.Extended.class)
    @GetMapping
    public DTOList<AgentDTO> getAgents(
            @RequestParam(name = "type", required = false) final AgentType type,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "parent", required = false) final Long parent,
            @RequestParam(name = "language") final String language) {

        final GetAgentsQuery getAgentsQuery = GetAgentsQuery.create(type, name, parent, Language.getLanguage(language));
        return sendQuery(getAgentsQuery, "agent").getRead().getData();

    }

    /**
     * Method to get the agent by id
     *
     * @param id       the id of the agent
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return AgentDTO in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public AgentDTO getAgentQuery(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language") final String language) {

        final GetAgentQuery getAgentQuery = GetAgentQuery.create(id, Language.getLanguage(language));
        return sendQuery(getAgentQuery, "agent").getRead().getData();
    }


    /**
     * FIXME not sure wee need this method Method to get the agent by localId
     *
     * @param localId  the local id of the agent
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return AgentDTO in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/local/{localId}")
    public AgentDTO getAgentQueryByLocalId(
            @PathVariable(name = "localId") final String localId,
            @RequestParam(name = "language") final String language) {

        final GetAgentQuery getAgentQuery = GetAgentQuery.create();
        getAgentQuery.setLocalId(localId);
        getAgentQuery.setLanguage(Language.getLanguage(language));
        return sendQuery(getAgentQuery, "agent").getRead().getData();
    }

}
