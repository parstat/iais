package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetBusinessFunctionQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetStatisticalProgramQuery;

@RestController
@RequestMapping(value = "/api/v1/referential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialOpen extends AbstractController {

	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs")
	public DTOList<StatisticalProgramDTO> getStatisticalProcessesQuery(
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery.create();
		getStatisticalProgramsQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getStatisticalProgramsQuery, "referential").getRead().getData();

	}

	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs/{id}")
	public StatisticalProgramDTO getStatisticalProgram(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(id);
		getStatisticalProcessQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getStatisticalProcessQuery, "referential").getRead().getData();
	}

	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/{id}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(id,
				Language.getLanguage(language));

		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/sub-phase/{localId}/version/{version}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "localId") final String localId,
			@PathVariable(name = "version") final String version,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
					version, Language.getLanguage(language));
		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/sub-phase/{localId}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "localId") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
				Language.getLanguage(language));
		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}
}
