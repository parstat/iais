package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ProcessMethodDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.CreateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.DeleteProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.UpdateProcessMethodCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/methods", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessMethodClosed extends AbstractController {

    /**
     * Method to create a processMethod
     * @param jwt The authentication token
     * @param name The name of the process method
     * @param description The description of the process method
     * @param localId The local id of the process method
     * @param version The version of the process method
     * @param versionDate The date of the version
     * @param versionRationale The text description of the version
     * @param language The language to use
     * @return ProcessMethodDTO
     */
    @JsonView(Views.Extended.class)
    @PostMapping
    public ProcessMethodDTO createProcessMethod(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "localId", required = false) final String localId,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final CreateProcessMethodCommand command = CreateProcessMethodCommand.create(jwt, localId, name, description, version,
                versionRationale, versionDate, Language.getLanguage(language));

        return sendCommand(command, "process_method").getEvent().getData();
    }


    /**
     * Method to update a process method
     * @param jwt The authentication token
     * @param id The id of process method to update
     * @param name The new name of process method
     * @param description The new description of process method
     * @param localId The new local id of the process method
     * @param language The language to use
     * @return ProcessMethodDTO
     */
    @JsonView(Views.Extended.class)
    @PatchMapping("/{id}")
    public ProcessMethodDTO updateProcessMethod(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "localId", required = false) final String localId,
            @RequestParam(name = "language", required = false) final String language) {

        final UpdateProcessMethodCommand command = UpdateProcessMethodCommand.create(jwt, id, localId, name, description,
                Language.getLanguage(language));

        return sendCommand(command, "process_method").getEvent().getData();

    }

    /**
     * Method to delete a process method
     * @param jwt The authentication token
     * @param id The id of process method to delete
     * @return DTOBoolean true if the process method has been deleted
     */
    @JsonView(Views.Basic.class)
    @DeleteMapping("/{id}")
    public DTOBoolean deleteProcessMethod(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id) {

        final DeleteProcessMethodCommand command = DeleteProcessMethodCommand.create(jwt, id);

        return sendCommand(command, "process_method").getEvent().getData();

    }

}
