package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.StatisticalStandardDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/referential/statistical/standards", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiStatisticalStandardOpen extends AbstractController {

    /**
     * Method to get many statistical standards in the selected language
     *
     * @param name     the name to search the statistical standards
     * @param type     the type of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
     *                 DEFINITIONS, METHODOLOGIES, PROCEDURES, RECOMMENDATIONS,
     *                 FRAMEWORK
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return a list of filtered statistical standards in the selected language all
     *         statistical standards if no filter parameter has been provided
     */
    @JsonView(Views.Extended.class)
    @GetMapping
    public DTOList<StatisticalStandardDTO> getStatisticalStandardsQuery(
            @RequestParam(name = "type", required = false) final StatisticalStandardType type,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "language") final String language) {

        final GetStatisticalStandardsQuery getStatisticalStandardsQuery = GetStatisticalStandardsQuery.create(type,
                name, Language.getLanguage(language));
        return sendQuery(getStatisticalStandardsQuery, "statistical_standard").getRead().getData();

    }

    /**
     * Method to get the statistical standards by id
     *
     * @param id       the id of the statistical standards
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return StatisticalStandardDTO in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public StatisticalStandardDTO getStatisticalStandardQuery(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language") final String language) {

        final GetStatisticalStandardQuery getStatisticalStandardQuery = GetStatisticalStandardQuery.create(id, Language.ENG);
        getStatisticalStandardQuery.setLanguage(Language.getLanguage(language));
        return sendQuery(getStatisticalStandardQuery, "statistical_standard").getRead().getData();
    }
}
