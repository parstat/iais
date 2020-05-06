package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.DeleteStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential/statistical/programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiStatisticalProgramClosed extends AbstractController {

	private static Logger LOG = LoggerFactory.getLogger(ApiStatisticalProgramClosed.class);
	/**
	 * API method to create a statistical program
	 *
	 * @param jwt              token in the header of the request (header name:
	 *                         jwt-auth)
	 * @param name             of survey
	 * @param acronym          of survey
	 * @param description      of survey
	 * @param localId          of survey
	 * @param version          first version of statistical program (default 1.0)
	 * @param versionDate      of the statistical program (default now())
	 * @param versionRationale reason of the first version of statistical program
	 *                         (default 'First Version')
	 * @param status           of survey
	 * @param budget           of survey
	 * @param funding          source of funding for survey
	 * @param dateInitiated    started date of the survey
	 * @param dateEnded        ended date of the survey if it is not cycled
	 * @param owner            of the survey, normally the statistical office
	 * @param maintainer       of the survey, normally the responsible division
	 * @param contact          of the survey, normally an individual of the
	 *                         responsible division
	 * @param language         to present the returned DTO
	 * @return StatisticalProgramDTO (the created survey)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{local_id}")
	public StatisticalProgramDTO creteStatisticalProgram(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "acronym", required = false) final String acronym,
			@RequestParam(name = "description", required = false) final String description,
			@PathVariable(name = "local_id") final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "status", required = false) final ProgramStatus status,
			@RequestParam(name = "budget", required = false) final double budget,
			@RequestParam(name = "funding", required = false) final String funding,
			@RequestParam(name = "initiated", required = false) final LocalDateTime dateInitiated,
			@RequestParam(name = "ended", required = false) final LocalDateTime dateEnded,
			@RequestParam(name = "owner", required = false) final Long owner,
			@RequestParam(name = "maintainer", required = false) final Long maintainer,
			@RequestParam(name = "contact", required = false) final Long contact,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateStatisticalProgramCommand command = CreateStatisticalProgramCommand.create(jwt, name, description,
				acronym, localId, dateInitiated, dateEnded, budget, funding, status, owner, maintainer, contact,
				Language.getLanguage(language));

		// To create the first version
		// not required default 1.0
		if (StringTools.isNotEmpty(version)) {
			command.setVersion(version);
		}
		// not required default now()
		if (versionDate != null) {
			command.setVersionDate(versionDate);
		}
		// not required default 'First Version'
		if (StringTools.isNotEmpty(versionRationale)) {
			command.setVersionRationale(versionRationale);
		}

		LOG.debug("Sending CreateStatisticalProgramCommand: {}", command.toString());
		return sendCommand(command, "statistical_program").getEvent().getData();

	}

	/**
	 * API method to create a new version of statistical program
	 *
	 * @param jwt              token in the header of the request
	 * @param name             of survey
	 * @param acronym          of survey
	 * @param description      of survey
	 * @param localId          of survey
	 * @param version          new version id
	 * @param versionDate      date of new version
	 * @param versionRationale reason for new version
	 * @param previousVersion  where the new version is based
	 * @param status           of survey
	 * @param budget           of survey
	 * @param funding          source of funding for survey
	 * @param dateInitiated    started date of the survey
	 * @param dateEnded        ended date of the survey if it is not cycled
	 * @param owner            of the survey, normally the statistical office
	 * @param maintainer       of the survey, normally the responsible division
	 * @param contact          of the survey, normally an individual of the
	 *                         responsible division
	 * @param language         to present the returned DTO
	 * @return StatisticalProgramDTO (the created survey)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{local_id}/versions/{version}")
	public StatisticalProgramDTO addStatisticalProgramVersion(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "acronym", required = false) final String acronym,
			@RequestParam(name = "description", required = false) final String description,
			@PathVariable(name = "local_id") final String localId, @PathVariable(name = "version") final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "previousVersion") final String previousVersion,
			@RequestParam(name = "status", required = false) final ProgramStatus status,
			@RequestParam(name = "budget", required = false) final double budget,
			@RequestParam(name = "funding", required = false) final String funding,
			@RequestParam(name = "initiated", required = false) final LocalDateTime dateInitiated,
			@RequestParam(name = "ended", required = false) final LocalDateTime dateEnded,
			@RequestParam(name = "owner", required = false) final Long owner,
			@RequestParam(name = "maintainer", required = false) final Long maintainer,
			@RequestParam(name = "contact", required = false) final Long contact,
			@RequestParam(name = "language", required = false) final String language) {

		final AddStatisticalProgramVersionCommand command = AddStatisticalProgramVersionCommand.create(jwt, name,
				description, acronym, localId, previousVersion, version, versionDate, versionRationale, dateInitiated,
				dateEnded, budget, funding, status, owner, maintainer, contact, Language.getLanguage(language));
		return sendCommand(command, "statistical_program").getEvent().getData();

	}

	/**
	 * API method to update a statistical program by id
	 *
	 * @param id               of the statistical program to update
	 * @param jwt              token in the header of the request
	 * @param name             to update or add in another language the name of
	 *                         survey
	 * @param acronym          to update or add in another language the acronym of
	 *                         survey
	 * @param description      to update or add in another language the description
	 *                         of survey
	 * @param versionRationale to update the reason for the version
	 * @param versionDate      to update the version date for the servey
	 * @param status           to update the status of survey
	 * @param budget           to update the budget of survey
	 * @param funding          to update the source of funding for survey
	 * @param dateInitiated    to update the started date of the survey
	 * @param dateEnded        to update the ended date of the survey if it is not
	 *                         cycled
	 * @param language         to present the returned DTO
	 * @return StatisticalProgramDTO (the created survey)
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{id}")
	public StatisticalProgramDTO updateStatisticalProgram(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "acronym", required = false) final String acronym,
			@RequestParam(name = "description", required = false) final String description,
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "status", required = false) final ProgramStatus status,
			@RequestParam(name = "budget", required = false) final double budget,
			@RequestParam(name = "funding", required = false) final String funding,
			@RequestParam(name = "initiated", required = false) final LocalDateTime dateInitiated,
			@RequestParam(name = "ended", required = false) final LocalDateTime dateEnded,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateStatisticalProgramCommand command = UpdateStatisticalProgramCommand.create(jwt, id, name,
				description, acronym, versionRationale, versionDate, dateInitiated, dateEnded, budget, funding, status,
				Language.getLanguage(language));

		return sendCommand(command, "statistical_program").getEvent().getData();

	}

	
	/**
	 * API method to add an existent statistical standard to a statistical program 
	 *
	 * @param id       id of the statistical program
	 * @param standard id of the statistical standard to add
	 * @param jwt      token in the header of the request
	 * @param language to present the returned DTO
	 * @return StatisticalProgramDTO (the update survey)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/standards/{standard}")
	public StatisticalProgramDTO addStatisticalProgramStandard(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id,
			@PathVariable(name = "standard") final Long standard,
			@RequestParam(name = "language", required = false) final String language) {

		final AddStatisticalProgramStandardCommand command = AddStatisticalProgramStandardCommand.create(jwt, id, standard,
				Language.getLanguage(language));

		return sendCommand(command, "statistical_program").getEvent().getData();

	}

	/**
	 * Method to delete a statistical program
	 * 
	 * @param jwt authorization token, only ADMIN and ROOT can delete currently
	 * @param id  of statistical program to delete
	 * @return DTOBoolean true if the statistical program has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}")
	public DTOBoolean deleteStatisticalProgram(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteStatisticalProgramCommand command = DeleteStatisticalProgramCommand.create(jwt, id);

		return sendCommand(command, "statistical_program").getEvent().getData();
	}


	/**
	 * API method to add an existent legislative reference to a statistical program 
	 *
	 * @param id              id of the statistical program 
	 * @param legislative     id of the legislative reference to add
	 * @param jwt             token in the header of the request
	 * @param language        to present the returned DTr
	 * @return StatisticalProgramDTO (the update survey)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/legislative/{legislative}")
	public StatisticalProgramDTO addStatisticalProgramLegislativeReference(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id,
			@PathVariable(name = "legislative") final Long legislative,
			@RequestParam(name = "language", required = false) final String language) {

		final AddStatisticalProgramLegislativeReferenceCommand command = AddStatisticalProgramLegislativeReferenceCommand.create(jwt, id, legislative,
				Language.getLanguage(language));

		return sendCommand(command, "statistical_program").getEvent().getData();

	}


}
