package com.nbs.iais.ms.meta.referential.api.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public StatisticalProgramDTO creteStatisticalProgram(
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
     * FIXME not sure we can use localId to make this a PUT method
     * This method can remain POST and lets use localId for the email of the individuals
     * Method to create an agent
     * @param jwt authorization token
     * @param name of the agent in selected language
     * @param type of agent: DIVISION, ORGANIZATION, INDIVIDUAL
     * @param description of the agent in the selected language
     * @param localId of the agent i.e the email of the INDIVIDUAL
     * @param parent of the agent, ORGANIZATIONS Can not have parents
     * @param account INDIVIDUAL only are related to an account
     * @param language selected
     * @return AgentDTO
     */
    @JsonView(Views.Extended.class)
    @PutMapping("/agents/{local_id}")
    public AgentDTO createAgent(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final AgentType type,
            @RequestParam(name = "description", required = false) final String description,
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "parent", required = false) final Long parent,
            @RequestParam(name = "account", required = false) final Long account,
            @RequestParam(name = "language", required = false) final String language) {


    	final CreateAgentCommand command = CreateAgentCommand.create(jwt, name,
                description, type, localId, parent, account,  Language.getLanguage(language) );
        return sendCommand(command, "referential").getEvent().getData();

    }

    /**
     * Method to create a business function
     * currently this method supports only adding the current version of GSBPM sub-phases 5.1
     * @param jwt authentication token
     * @param localId of the business function (GSBPM sub-phase id)
     * @param name of the business function (GSBPM sub-phase) in the selected @param language
     * @param description of the business function (GSBPM sub-phase) in the selected @param language
     * @param language selected language
     * @return BusinessFunctionDTO the DTO object of the newly created business function
     */
    @JsonView(Views.Secure.class)
    @PutMapping("/business/functions/{local_id}")
    public BusinessFunctionDTO createBusinessFunction(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "language") final String language) {

        final CreateBusinessFunctionCommand command = CreateBusinessFunctionCommand.create(jwt, name, description,
                localId, Language.getLanguage(language));

        return sendCommand(command, "referential").getEvent().getData();

    }

    /**
     * Method to update a business function (GSBPM sub-phase)
     * normally to add another supported language
     * @param jwt authorization token
     * @param id of the business function (GSBPM sub-phase)
     * @param name updated name in selected language
     * @param description updated description in the selected language
     * @param language selected language
     * @return BusinessFunctionDTO the updated business function (GSBPM sub-phase) in the selected language
     */
    @JsonView(Views.Secure.class)
    @PatchMapping("/business/functions/{id}")
    public BusinessFunctionDTO updateBusinessFunction(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "language") final String language) {

        final UpdateBusinessFunctionCommand command = UpdateBusinessFunctionCommand.create(jwt, id, name, description,
                Language.getLanguage(language));

        return sendCommand(command, "referential").getEvent().getData();
    }

}
