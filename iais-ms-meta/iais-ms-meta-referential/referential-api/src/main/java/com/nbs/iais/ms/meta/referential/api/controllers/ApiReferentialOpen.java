package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.GetAgentsQuery;
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


	/**
	 * Method to get all statistical programs (survey)
	 * @param language to present the DTOs
	 * @return DTOList<StatisticalProgramDTO> (List of all Surveys in selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs")
	public DTOList<StatisticalProgramDTO> getStatisticalPrograms(
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery.create();
		getStatisticalProgramsQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getStatisticalProgramsQuery, "referential").getRead().getData();

	}

	/**
	 * Method to get a statistical program (survey) by id
	 * @param id of the statistical program (survey)
	 * @param language to present the returned DTO
	 * @return StatisticalProgramDTO (the requested survey presented in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs/{id}")
	public StatisticalProgramDTO getStatisticalProgram(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(id);
		getStatisticalProcessQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getStatisticalProcessQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get a business function (gsbpm sub-phase) by id
	 * @param id sub-phase id
	 * @param language to present the returned DTO
	 * @return BusinessFunctionDTO (the requested gsbpm sub-phase in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/{id}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(id,
				Language.getLanguage(language));

		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get a business function (gsbpm sub-phase) by local id and version
	 * sub-phase of gsbpm can have different version, current is 5.1
	 * if the user is interested in another version can use this method
	 * @param localId the id of sub-phase
	 * @param version version of sub-phase
	 * @param language to present the returned DTO
	 * @return BusinessFunctionDTO (gsbpm sub-phase in the requested language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/sub-phase/{localId}/version/{version}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "sub_phase") final String localId,
			@PathVariable(name = "version") final String version,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
					version, Language.getLanguage(language));
		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get the current version of gsbpm sub-phse (business function)
	 * current version is hardcoded to 5.1 method should be changed to get always the latest version
	 * @param localId id of gsbpm sub-phase
	 * @param language to present the returned DTO
	 * @return BusinessFunctionDTO the requested business function (gsbpm sub-phase) in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/sub-phase/{localId}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "localId") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
				Language.getLanguage(language));
		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}
	
	
	
	@JsonView(Views.Extended.class)
	@GetMapping("/agents")
	public DTOList<AgentDTO> getAgentsQuery(@RequestParam(name = "language") final String language) {

		final GetAgentsQuery getAgentsQuery = GetAgentsQuery.create();
		getAgentsQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getAgentsQuery, "referential").getRead().getData();

	}

	@JsonView(Views.Extended.class)
	@GetMapping("/agents/{id}")
	public AgentDTO getAgentQuery(@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetAgentQuery getAgentQuery = GetAgentQuery.create(id);
		getAgentQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getAgentQuery, "referential").getRead().getData();
	}
}
