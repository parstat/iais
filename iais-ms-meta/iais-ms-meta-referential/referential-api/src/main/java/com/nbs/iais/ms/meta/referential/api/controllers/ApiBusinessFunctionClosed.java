package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/referential/business/functions", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiBusinessFunctionClosed extends AbstractController {

    /**
     * Method to create a business function currently this method supports only
     * adding the current version of GSBPM sub-phases 5.1
     *
     * @param jwt         authentication token
     * @param localId     of the business function (GSBPM sub-phase id)
     * @param name        of the business function (GSBPM sub-phase) in the
     *                    selected @param language
     * @param description of the business function (GSBPM sub-phase) in the
     *                    selected @param language
     * @param language    selected language
     * @return BusinessFunctionDTO the DTO object of the newly created business
     *         function
     */
    @JsonView(Views.Secure.class)
    @PutMapping("/{local_id}")
    public BusinessFunctionDTO createBusinessFunction(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "language") final String language) {

        final CreateBusinessFunctionCommand command = CreateBusinessFunctionCommand.create(jwt, name, description,
                localId, Language.getLanguage(language));

        return sendCommand(command, "business_function").getEvent().getData();

    }

    /**
     * Method to update a business function (GSBPM sub-phase) normally to add
     * another supported language
     *
     * @param jwt         authorization token
     * @param id          of the business function (GSBPM sub-phase)
     * @param name        updated name in selected language
     * @param description updated description in the selected language
     * @param language    selected language
     * @return BusinessFunctionDTO the updated business function (GSBPM sub-phase)
     *         in the selected language
     */
    @JsonView(Views.Secure.class)
    @PatchMapping("/{id}")
    public BusinessFunctionDTO updateBusinessFunction(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
            @RequestParam(name = "versionRationale", required = false) final String versionRationale,
            @RequestParam(name = "description", required = false) final String description,
            @RequestParam(name = "language") final String language) {

        final UpdateBusinessFunctionCommand command = UpdateBusinessFunctionCommand.create(jwt, id, name, description,
                Language.getLanguage(language));

        return sendCommand(command, "business_function").getEvent().getData();
    }
}
