package com.nbs.iais.ms.meta.referential.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nbs.iais.ms.common.api.controllers.AbstractController;

import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessesQuery;


@RestController
@RequestMapping(value = "/api/v1/open/refential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialOpen extends AbstractController {

	@GetMapping("/statprocess")
	public DTOList<StatisticalProcessDTO> getStatisticalProcessesQuery() {

		final GetStatisticalProcessesQuery getStatisticalProcessesQuery = GetStatisticalProcessesQuery.create();

		return sendQuery(getStatisticalProcessesQuery, "referential").getRead().getData();

	}


}
