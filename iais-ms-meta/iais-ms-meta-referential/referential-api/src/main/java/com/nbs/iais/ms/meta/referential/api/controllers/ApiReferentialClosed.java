package com.nbs.iais.ms.meta.referential.api.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/close/referential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialClosed extends AbstractController {

    /**
     * API method to create a statistical program
     *
     * @param jwt token in the header of the request
     * @param name of survey
     * @param acronym of survey
     * @param description of survey
     * @param localId of survey
     * @param status of survey
     * @param budget of survey
     * @param funding source of funding for survey
     * @param dateInitiated started date of the survey
     * @param dateEnded ended date of the survey if it is not cycled
     * @param owner of the survey, normally the statistical office
     * @param maintainer of the survey, normally the responsible division
     * @param contact of the survey, normally an individual of the responsible division
     * @param language to present the returned DTO
     * @return StatisticalProgramDTO (the created survey)
     */
    @JsonView(Views.Extended.class)
    @PutMapping("/statistical/programs/{local_id}")
    public StatisticalProgramDTO createStatisticalProgram(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "acronym", required = false) final String acronym,
            @RequestParam(name = "description", required = false) final String description,
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "status", required = false) final ProgramStatus status,
            @RequestParam(name = "budget", required = false) final double budget,
            @RequestParam(name = "funding", required = false) final String funding,
            @RequestParam(name = "initiated", required = false) final LocalDateTime dateInitiated,
            @RequestParam(name = "ended", required = false) final LocalDateTime dateEnded,
            @RequestParam(name = "owner", required = false) final Long owner,
            @RequestParam(name = "maintainer", required = false) final Long maintainer,
            @RequestParam(name = "contact", required = false) final Long contact,
            @RequestParam(name = "language", required = false) final String language) {

        final CreateStatisticalProgramCommand command = CreateStatisticalProgramCommand.create(jwt, name,
                description, acronym, localId, dateInitiated, dateEnded, budget, funding, status, owner,
                maintainer, contact, Language.getLanguage(language) );
        return sendCommand(command, "referential").getEvent().getData();

    }
    
    /**
     * API method to create an Agent
     *
     * @param jwt token in the header of the request
 
     * @return AgentDTO
     */
    @JsonView(Views.Extended.class)
    @PutMapping("/agents/{local_id}")
    public AgentDTO createAgent(
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final AgentType type,
            @RequestParam(name = "description", required = false) final String description,
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "parent", required = false) final Long parent,
            @RequestParam(name = "account", required = false) final Long account,
            @RequestParam(name = "children", required = false) final List<Long> children,
            @RequestParam(name = "administrativeDetails", required = false) final Long administrativeDetails,
            @RequestParam(name = "language", required = false) final String language) {
 
    	
    	final CreateAgentCommand command = CreateAgentCommand.create(name,
                description, type, localId, parent, account, children, administrativeDetails,  Language.getLanguage(language) );
        return sendCommand(command, "referential").getEvent().getData();

    }
}
