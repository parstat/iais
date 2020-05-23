package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.BusinessServiceDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential/business/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiBusinessServiceClosed extends AbstractController {

    /**
     * Method to create a new Business Service
     * @param jwt The authentication token
     * @param localId The local id of the Business Service
     * @param name The name of business service
     * @param description The description of business service
     * @param version The version of business service
     * @param versionDate The date of released version
     * @param versionRationale The reason of this version
     * @param language The language used
     * @return BusinessServiceDTO
     */
    @JsonView(Views.Extended.class)
    @PutMapping("/{local_id}")
    public BusinessServiceDTO createBusinessService(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final CreateBusinessServiceCommand command = CreateBusinessServiceCommand.create(jwt, name, description,
                localId, version, versionRationale, versionDate, Language.getLanguage(language));

        return sendCommand(command, "business_service").getEvent().getData();

    }

    /**
     * Method to update a business service
     * @param jwt The authentication token
     * @param id The id of the business service to update
     * @param name The new name of business service (or name in different language)
     * @param description The new description of business service (or description on a different language)
     * @param version Version of business service
     * @param versionDate Date of the version
     * @param versionRationale Reason for the version
     * @param language The language to  use
     * @return BusinessServiceDTO
     */
    @JsonView(Views.Extended.class)
    @PatchMapping("/{id}")
    public BusinessServiceDTO updateBusinessService(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "version", required = false) final String version,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "language", required = false) final String language) {

        final UpdateBusinessServiceCommand command = UpdateBusinessServiceCommand.create(jwt, id, name, description, version, versionRationale, versionDate, Language.getLanguage(language));

        return sendCommand(command, "business_service").getEvent().getData();

    }

    /**
     * Method to delete a business service
     * @param jwt the authentication token
     * @param id The id of the business service
     * @return DTOBoolean (true if deleted)
     */
    @JsonView(Views.Basic.class)
    @DeleteMapping("/{id}")
    public DTOBoolean deleteBusinessService(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id) {

        final DeleteBusinessServiceCommand command = DeleteBusinessServiceCommand.crete(jwt, id);

        return sendCommand(command, "business_service").getEvent().getData();
    }

    /**
     * Method to add a service interface in business service
     * @param jwt The authentication token
     * @param id The id of business service
     * @param serviceInterface The service interface string
     * @param language The language to use
     * @return BusinessServiceDTO
     */
    @JsonView(Views.Extended.class)
    @PutMapping("/{id}/interface")
    public BusinessServiceDTO addInterface(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "service_interface") final String serviceInterface,
            @RequestParam(name = "language", required = false) final String language) {

        final AddBusinessServiceInterfaceCommand command = AddBusinessServiceInterfaceCommand.create(jwt, id,
                serviceInterface, Language.getLanguage(language));

        return sendCommand(command, "business_service").getEvent().getData();
    }

    /**
     * Method to remove a service interface in business service
     * @param jwt The authentication token
     * @param id The id of business service
     * @param serviceInterface The service interface string
     * @param language The language to use
     * @return BusinessServiceDTO
     */
    @JsonView(Views.Extended.class)
    @DeleteMapping("/{id}/interface")
    public BusinessServiceDTO removeInterface(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "service_interface") final String serviceInterface,
            @RequestParam(name = "language", required = false) final String language) {

        final RemoveBusinessServiceInterfaceCommand command = RemoveBusinessServiceInterfaceCommand.create(jwt, id,
                serviceInterface, Language.getLanguage(language));

        return sendCommand(command, "business_service").getEvent().getData();
    }
}
