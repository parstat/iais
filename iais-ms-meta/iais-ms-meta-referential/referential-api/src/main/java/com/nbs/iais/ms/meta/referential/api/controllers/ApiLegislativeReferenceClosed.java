package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential/legislative/references", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiLegislativeReferenceClosed extends AbstractController {

    /**
     * Method to create a legislative reference
     *
     * @param jwt              authorization token
     * @param name             of the legislative reference in selected language
     * @param type             of legislative reference: REGULATION, LAW, CODE,
     *                         GOVERNMENTAL_DECISION, AMENDMENT,
     * @param description      of the legislative reference in the selected language
     * @param localId          of the legislative reference
     * @param approvalDate     of the legislative reference
     * @param link             of the legislative reference
     * @param version          first version of agent (default 1.0)
     * @param versionDate      of the agent (default now())
     * @param versionRationale reason of the first version of agent (default 'First
     *                         Version')
     * @param language         selected
     * @return LegislativeReferenceDTO
     */
    @JsonView(Views.Extended.class)
    @PostMapping
    public LegislativeReferenceDTO createLegislativeReference(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final LegislativeType type,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "localId") final String localId,
            @RequestParam(name = "approvalDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime approvalDate,
            @RequestParam(name = "link", required = false) final String link,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final CreateLegislativeReferenceCommand command = CreateLegislativeReferenceCommand.create(jwt, name,
                description, localId, approvalDate,link, type, version, versionDate, versionRationale,
                Language.getLanguage(language));
        return sendCommand(command, "legislative_reference").getEvent().getData();

    }

    /**
     * Method to update an legislative reference
     *
     * @param jwt              authorization token
     * @param id               of the legislative reference
     * @param name             of the legislative reference in selected language
     * @param type             of legislative reference: REGULATION, LAW, CODE,
     *                         GOVERNMENTAL_DECISION, AMENDMENT,
     * @param description      of the legislative reference in the selected language
     * @param localId          of the legislative reference
     * @param approvalDate     of the legislative reference
     * @param link             of the legislative reference
     * @param version          first version of agent (default 1.0)
     * @param versionDate      of the agent (default now())
     * @param versionRationale reason of the first version of agent (default 'First
     *                         Version')
     * @param language         selected
     * @return LegislativeReferenceDTO
     */
    @JsonView(Views.Extended.class)
    @PatchMapping("/{id}")
    public LegislativeReferenceDTO updateLegislativeReference(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "type", required = false) final LegislativeType type,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "local_id", required = false) final String localId,
            @RequestParam(name = "approvalDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime approvalDate,
            @RequestParam(name = "link", required = false) final String link,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final UpdateLegislativeReferenceCommand command = UpdateLegislativeReferenceCommand.create(jwt, id, name,
                description, approvalDate,link, type, localId, version, versionDate, versionRationale,
                Language.getLanguage(language));
        return sendCommand(command, "legislative_reference").getEvent().getData();

    }

    /**
     * Method to delete an legislative reference
     *
     * @param jwt authorization token
     * @param id  of legislative reference to delete
     * @return DTOBoolean true if the agent has been deleted
     */
    @JsonView(Views.Extended.class)
    @DeleteMapping("/{id}")
    public DTOBoolean deleteLegislativeReference(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id) {

        final DeleteLegislativeReferenceCommand command = DeleteLegislativeReferenceCommand.create(jwt, id);

        return sendCommand(command, "legislative_reference").getEvent().getData();
    }
}
