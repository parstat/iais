package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.enums.Language;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;

/**
 * All API method in this controller does not require being registered
 * <p>
 * Open methods
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/referential/statistical/programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiStatisticalProgramOpen extends AbstractController {

	/**
	 * Method to get statistical programs (survey) by different filters
	 * 
	 * @param name       the name to search the statistical surveys if this
	 *                   parameter has value the other filter parameters will be
	 *                   ignored
	 * @param maintainer the division id, to get all statistical surveys by division
	 *                   id
	 * @param language   the language to present the DTOs (en, ro, ru) also the
	 *                   query will search only in the selected language name of the
	 *                   statistical survey
	 * @return List of all Surveys in selected language if not parameter provided or
	 *         will return only filtered surveys
	 */
	@JsonView(Views.Extended.class)
	@GetMapping
	public DTOList<StatisticalProgramDTO> getStatisticalPrograms(
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "maintainer", required = false) final Long maintainer,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery.create(name,
				maintainer, Language.getLanguage(language));
		return sendQuery(getStatisticalProgramsQuery, "statistical_program").getRead().getData();

	}

	/**
	 * Method to get a statistical program (survey) by id
	 * 
	 * @param id       the id of the requested statistical program (survey)
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return StatisticalProgramDTO (the requested survey presented in the selected
	 *         language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/{id}")
	public StatisticalProgramDTO getStatisticalProgram(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(id,
				Language.getLanguage(language));
		return sendQuery(getStatisticalProcessQuery, "statistical_program").getRead().getData();
	}

	/**
	 * Method to get a statistical program (survey) by local id and version
	 * 
	 * @param localId  local id of the requested statistical program (survey)
	 * @param version  the version of the statistical program (survey)
	 * @param language to present the returned DTO (en, ro, ru)
	 * @return StatisticalProgramDTO (the requested survey presented in the selected
	 *         language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/{local_id}/versions/{version}")
	public StatisticalProgramDTO getStatisticalProgramByVersion(
			@PathVariable(name = "local_id") final String localId,
			@PathVariable(name = "version") final String version,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(localId,
				version, Language.getLanguage(language));

		return sendQuery(getStatisticalProcessQuery, "statistical_program").getRead().getData();
	}

	/**
	 * Method to get latest versions of a statistical program (survey)
	 * 
	 * @param localId  the local id of the statistical program (survey)
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return StatisticalProgramDTO (the latest versions of the survey presented in
	 *         the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/{local_id}/latest")
	public StatisticalProgramDTO getLatestVersionStatisticalProgram(
			@PathVariable(name = "local_id") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProgramQuery = GetStatisticalProgramQuery.create(localId,
				Language.getLanguage(language));

		return sendQuery(getStatisticalProgramQuery, "statistical_program").getRead().getData();
	}

	/**
	 * Method to get all versions of a statistical program (survey)
	 * 
	 * @param localId  of the statistical program (survey)
	 * @param language to present the returned DTO (en, ro, ru)
	 * @return (the requested versions of the survey presented in the selected
	 *         language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/{local_id}/versions")
	public DTOList<StatisticalProgramDTO> getAllVersionsOfStatisticalProgram(
			@PathVariable(name = "local_id") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery
				.create(Language.getLanguage(language));
		getStatisticalProgramsQuery.setLocalId(localId);
		return sendQuery(getStatisticalProgramsQuery, "statistical_program").getRead().getData();
	}


}
