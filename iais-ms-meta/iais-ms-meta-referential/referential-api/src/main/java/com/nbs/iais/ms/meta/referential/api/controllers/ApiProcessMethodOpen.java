package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ProcessMethodDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodsQuery;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/referential/process/methods", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessMethodOpen extends AbstractController {

    /**
     * API method to get a processMethod by id
     * @param id Id of the requested process method
     * @param language The language to get the result in
     * @return ProcessMethodDTO
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public ProcessMethodDTO getProcessMethod(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language", required = false) final String language) {

        final GetProcessMethodQuery query = GetProcessMethodQuery.create(id, Language.getLanguage(language));

        return sendQuery(query, "process_method").getRead().getData();

    }

    /**
     * Method to get a list of processMethods
     * @param name The text to search by name
     * @param language The language to get the result
     * @return DTOList<ProcessMethodDTO>
     */
    @JsonView(Views.Extended.class)
    @GetMapping
    public DTOList<ProcessMethodDTO> getProcessMethods(
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "language", required = false) final String language) {

        final GetProcessMethodsQuery query = GetProcessMethodsQuery.crate(name, Language.getLanguage(language));

        return sendQuery(query, "process_method").getRead().getData();
    }



}
