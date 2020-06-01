package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.db.services.BusinessServiceCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/referential/business/functions", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiBusinessFunctionOpen extends AbstractController {

    private final static Logger LOG = LoggerFactory.getLogger(BusinessServiceCommandService.class);

    /**
     * Method to get a business function (gsbpm sub-phase) by id
     *
     * @param id       sub-phase id
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return BusinessFunctionDTO (the requested gsbpm sub-phase in the selected
     *         language)
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public BusinessFunctionDTO getBusinessFunction(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language") final String language) {

        final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(id,
                Language.getLanguage(language));

        return sendQuery(getBusinessFunctionQuery, "business_function").getRead().getData();
    }

    /**
     * Method to get a business function (gsbpm sub-phase) by local id and version
     * <p>
     * Sub-phase of gsbpm can have different version, current is 5.1 if the user is
     * interested in another version can use this method
     * </p>
     *
     * @param localId  the id of sub-phase
     * @param version  version of sub-phase (default 5.1)
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return BusinessFunctionDTO (gsbpm sub-phase in the requested language)
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/sub-phase/{local_id}/versions/{version}")
    public BusinessFunctionDTO getBusinessFunction(
            @PathVariable(name = "local_id") final String localId,
            @PathVariable(name = "version") final String version,
            @RequestParam(name = "language") final String language) {

        final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId, version,
                Language.getLanguage(language));
        return sendQuery(getBusinessFunctionQuery, "business_function").getRead().getData();
    }

    /**
     * Method to get the current version of GSBPM sub-phase
     * <p>
     * Current version is hardcoded to 5.1 method should be changed to get always
     * the latest version
     * </p>
     *
     * @param localId  the id of gsbpm sub-phase
     * @param language the language to present the returned DTO (en, ro, ru)
     * @return BusinessFunctionDTO the requested business function (gsbpm sub-phase)
     *         in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping("/sub-phase/{local_id}")
    public BusinessFunctionDTO getBusinessFunction(
            @PathVariable(name = "local_id") final String localId,
            @RequestParam(name = "language") final String language) {

        final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
                Language.getLanguage(language));
        return sendQuery(getBusinessFunctionQuery, "business_function").getRead().getData();
    }

    /**
     * Method to get business functions using different filters
     *
     * @param name     the string to search on the name of the selected language
     * @param phase    to get all business function of this phase
     * @param language the selected language to return the result
     * @return a list of BusinessFunctionDTO in the selected language
     */
    @JsonView(Views.Extended.class)
    @GetMapping
    public DTOList<BusinessFunctionDTO> getBusinessFunctions(
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "phase", required = false) final String phase,
            @RequestParam(name = "language") final String language) {

        int intPhase = 0;
        if(StringTools.isNotEmpty(phase)) {
            try {
                intPhase = Integer.parseInt(phase);
            } catch (NumberFormatException ex) {
                LOG.debug("Not a number used for phase");
            }
        }
        final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create(name, intPhase, null,
                Language.getLanguage(language));

        return sendQuery(query, "business_function").getRead().getData();
    }

}
