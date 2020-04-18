package com.nbs.iais.ms.meta.referential.api.controllers;

import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.enums.Language;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramQuery;

@RestController
@RequestMapping(value = "/api/v1/referential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialOpen extends AbstractController {

	@GetMapping("/statistical/programs")
	public DTOList<StatisticalProgramDTO> getStatisticalProcessesQuery(
			@RequestParam(name = "language") final Language language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery.create();
		getStatisticalProgramsQuery.setLanguage(language);
		return sendQuery(getStatisticalProgramsQuery, "referential").getRead().getData();

	}

	@GetMapping("/statistical/programs/{id}")
	public StatisticalProgramDTO getStatisticalProgram(
			@PathVariable("id") final Long id,
			@RequestParam(name = "language") final Language language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(id);
		getStatisticalProcessQuery.setLanguage(language);
		return sendQuery(getStatisticalProcessQuery, "referential").getRead().getData();
	}

}
