package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.BusinessServiceDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service.GetBusinessServiceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service.GetBusinessServicesQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/close/referential/business/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiBusinessServiceOpen extends AbstractController {


    @JsonView(Views.Extended.class)
    @GetMapping("/{id}")
    public BusinessServiceDTO getBusinessService(
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language", required = false) final String language) {

        final GetBusinessServiceQuery query = GetBusinessServiceQuery.create(id, Language.getLanguage(language));

        return sendQuery(query, "business_service").getRead().getData();
    }

    @JsonView(Views.Extended.class)
    @GetMapping("/local/{local_id}/versions/{version}")
    public BusinessServiceDTO getBusinessServiceVersion(
            @PathVariable(name = "local_id") final String localId,
            @PathVariable(name = "version") final String version,
            @RequestParam(name = "language") final String language) {

        final GetBusinessServiceQuery query = GetBusinessServiceQuery.create(localId, version, Language.getLanguage(language));

        return sendQuery(query, "business_service").getRead().getData();
    }

    @JsonView(Views.Extended.class)
    @GetMapping()
    public DTOList<BusinessServiceDTO> getBusinessServices(
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "local_id", required = false) final String localId,
            @RequestParam(name = "language", required = false) final String language) {

        final GetBusinessServicesQuery query = GetBusinessServicesQuery.create(localId, name, Language.getLanguage(language));

        return sendQuery(query, "business_service").getRead().getData();
    }


}
