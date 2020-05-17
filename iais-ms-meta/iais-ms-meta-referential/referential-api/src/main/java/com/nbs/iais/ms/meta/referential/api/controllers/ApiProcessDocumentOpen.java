package com.nbs.iais.ms.meta.referential.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document.GetProcessDocumentsQuery;

@RestController
@RequestMapping(value = "/api/v1/referential/process/documents", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessDocumentOpen extends AbstractController {

    /**
     * Method to get many process documents in the selected language
     *
     * @param processDocumentation     the processDocumentation to search the process quality
    
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return a list of filtered process quality in the selected language all
     *         process quality if no filter parameter has been provided
     */
    @JsonView(Views.Extended.class)
    @GetMapping
    public DTOList<ProcessDocumentDTO> getProcessDocumentsQuery(
            @RequestParam(name = "processDocumentation", required = false) final Long processDocumentation,
            @RequestParam(name = "language") final String language) {

        final GetProcessDocumentsQuery query = GetProcessDocumentsQuery.create(processDocumentation,
                Language.getLanguage(language));
        return sendQuery(query, "process_document").getRead().getData();

    }

    /**
     * Method to get the process document by id
     *
     * @param id       the id of the process document
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return ProcessDocumentDTO in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public ProcessDocumentDTO getProcessDocumentQuery(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language") final String language) {

        final GetProcessDocumentQuery getProcessDocumentQuery = GetProcessDocumentQuery.create(id);
        getProcessDocumentQuery.setLanguage(Language.getLanguage(language));
        return sendQuery(getProcessDocumentQuery, "process_document").getRead().getData();
    }
}
