package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.StatisticalStandardDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential/statistical/standards", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiStatisticalStandardClosed extends AbstractController {

    /**
     * Method to create a statistical standard
     *
     * @param jwt              authorization token
     * @param name             of the Statistical Standard in selected language
     * @param type             of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
     *                         DEFINITIONS, METHODOLOGIES, PROCEDURES,
     *                         RECOMMENDATIONS, FRAMEWORK
     * @param description      of the Statistical Standard in the selected language
     * @param localId          of the Statistical Standard
     * @param version          first version of agent (default 1.0)
     * @param versionDate      of the agent (default now())
     * @param versionRationale reason of the first version of agent (default 'First
     *                         Version')
     * @param language         selected
     * @return StatisticalStandardDTO
     */
    @JsonView(Views.Extended.class)
    @PostMapping
    public StatisticalStandardDTO createStatisticalStandard(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final StatisticalStandardType type,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "local_id") final String localId,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final CreateStatisticalStandardCommand command = CreateStatisticalStandardCommand.create(jwt, name, description,
                localId, type, version, versionDate, versionRationale, Language.getLanguage(language));
        return sendCommand(command, "statistical_standards").getEvent().getData();

    }

    /**
     * Method to update a statistical standard
     *
     * @param jwt              authorization token
     * @param id               of the Statistical Standard
     * @param name             of the Statistical Standard in selected language
     * @param type             of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
     *                         DEFINITIONS, METHODOLOGIES, PROCEDURES,
     *                         RECOMMENDATIONS, FRAMEWORK
     * @param description      of the Statistical Standard in the selected language
     * @param localId          of the Statistical Standard
     * @param version          first version of agent (default 1.0)
     * @param versionDate      of the agent (default now())
     * @param versionRationale reason of the first version of agent (default 'First
     *                         Version')
     * @param language         selected
     * @return StatisticalStandardDTO
     */
    @JsonView(Views.Extended.class)
    @PatchMapping("/{id}")
    public StatisticalStandardDTO updateStatisticalStandard(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final StatisticalStandardType type,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "local_id", required = false) final String localId,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final UpdateStatisticalStandardCommand command = UpdateStatisticalStandardCommand.create(jwt, id, name,
                description, type, localId, version, versionDate, versionRationale, Language.getLanguage(language));
        return sendCommand(command, "statistical_standards").getEvent().getData();

    }

    /**
     * Method to delete an Statistical Standard
     *
     * @param jwt authorization token
     * @param id  of Statistical Standard to delete
     * @return DTOBoolean true if the agent has been deleted
     */
    @JsonView(Views.Extended.class)
    @DeleteMapping("/{id}")
    public DTOBoolean deleteStatisticalStandard(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id) {

        final DeleteStatisticalStandardCommand command = DeleteStatisticalStandardCommand.create(jwt, id);

        return sendCommand(command, "statistical_standards").getEvent().getData();
    }
}
