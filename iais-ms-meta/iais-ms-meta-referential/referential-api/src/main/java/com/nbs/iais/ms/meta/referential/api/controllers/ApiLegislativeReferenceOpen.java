package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/referential/legislative/references", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiLegislativeReferenceOpen extends AbstractController {

    /**
     * Method to get many legislative references in the selected
     * language
     *
     * @param name     the name to search the legislative references
     * @param type     the type of legislative references:REGULATION, LAW, CODE,
     *                 GOVERNMENTAL_DECISION, AMENDMENT
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return a list of filtered legislative references in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping
    public DTOList<LegislativeReferenceDTO> getLegislativeReferencesQuery(
            @RequestParam(name = "type", required = false) final LegislativeType type,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "language") final String language) {

        final GetLegislativeReferencesQuery getLegislativeReferencesQuery = GetLegislativeReferencesQuery.create(type,
                name, Language.getLanguage(language));
        return sendQuery(getLegislativeReferencesQuery, "referential").getRead().getData();

    }

    /**
     *  Method to get the legislative reference by id
     *
     * @param id       the id of the legislative reference
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return LegislativeReferenceDTO in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public LegislativeReferenceDTO getLegislativeReferenceQuery(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language") final String language) {

        final GetLegislativeReferenceQuery getLegislativeReferenceQuery = GetLegislativeReferenceQuery.create(id, Language.getLanguage(language));
        getLegislativeReferenceQuery.setLanguage(Language.getLanguage(language));
        return sendQuery(getLegislativeReferenceQuery, "referential").getRead().getData();
    }
}
