package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalStandardDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.DeleteStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/close/referential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialClosed extends AbstractController {

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
	@PutMapping("/statistical/programs/{local_id}")
	public StatisticalProgramDTO creteStatisticalProgram(@RequestHeader(name = "jwt-auth") final String jwt,
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
		return sendCommand(command, "referential").getEvent().getData();

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
	@PutMapping("/statistical/programs/{local_id}/versions/{version}")
	public StatisticalProgramDTO addStatisticalProgramVersion(@RequestHeader(name = "jwt-auth") final String jwt,
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
		return sendCommand(command, "referential").getEvent().getData();

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
	@PatchMapping("/statistical/programs/{id}")
	public StatisticalProgramDTO updateStatisticalProgram(@RequestHeader(name = "jwt-auth") final String jwt,
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

		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * Method to delete a statistical program
	 * 
	 * @param jwt authorization token, only ADMIN and ROOT can delete currently
	 * @param id  of statistical program to delete
	 * @return DTOBoolean true if the statistical program has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/statistical/programs/{id}")
	public DTOBoolean deleteStatisticalProgram(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteStatisticalProgramCommand command = DeleteStatisticalProgramCommand.create(jwt, id);

		return sendCommand(command, "referential").getEvent().getData();
	}

	/**
	 * Method to create an agent
	 * 
	 * @param jwt         authorization token
	 * @param name        of the agent in selected language
	 * @param type        of agent: DIVISION, ORGANIZATION, INDIVIDUAL
	 * @param description of the agent in the selected language
	 * @param localId     of the agent i.e the email of the INDIVIDUAL
	 * @param parent      of the agent, ORGANIZATIONS Can not have parents
	 * @param account     INDIVIDUAL only are related to an account
	 * @param language    selected
	 * @return AgentDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/agents")
	public AgentDTO createAgent(@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final AgentType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "parent", required = false) final Long parent,
			@RequestParam(name = "account", required = false) final Long account,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateAgentCommand command = CreateAgentCommand.create(jwt, name, description, type, localId, parent,
				account, Language.getLanguage(language));
		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * Method to update an agent
	 * 
	 * @param jwt              authorization token
	 * @param id               of the agent
	 * @param name             of the agent in selected language
	 * @param type             of agent: DIVISION, ORGANIZATION, INDIVIDUAL
	 * @param description      of the agent in the selected language
	 * @param localId          of the agent i.e the email of the INDIVIDUAL
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of agent (default 'First
	 *                         Version')
	 * @param parent           of the agent, ORGANIZATIONS Can not have parents
	 * @param account          INDIVIDUAL only are related to an account
	 * @param language         selected
	 * @return AgentDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/agents/{id}")
	public AgentDTO updateAgent(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final AgentType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "parent", required = false) final Long parent,
			@RequestParam(name = "account", required = false) final Long account,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateAgentCommand command = UpdateAgentCommand.create(jwt, id, name, description, type, localId, parent,
				account, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * Method to delete an agent
	 * 
	 * @param jwt authorization token, only ADMIN and ROOT can delete currently
	 * @param id  of agent to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/agents/{id}")
	public DTOBoolean deleteAgent(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteAgentCommand command = DeleteAgentCommand.create(jwt, id);

		return sendCommand(command, "referential").getEvent().getData();
	}

	/**
	 * 
	 * @param jwt              authorization token
	 * @param name             of the Statistical Standard in selected language
	 * @param type             of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
	 *                         DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 *                         RECOMMENDATIONS, FRAMEWORK
	 * @param description      of the Statistical Standard in the selected language
	 * @param localId          of the Statistical Standard 
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of agent (default 'First
	 *                         Version')
	 * @param language         selected
	 * @return StatisticalStandardDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/statistical/standards")
	public StatisticalStandardDTO createStatisticalStandard(@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final StatisticalStandardType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateStatisticalStandardCommand command = CreateStatisticalStandardCommand.create(jwt, name, description,
				localId, type, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * @param jwt         authorization token
	 * @param id          of the Statistical Standard
	 * @param name        of the Statistical Standard in selected language
	 * @param type             of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
	 *                         DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 *                         RECOMMENDATIONS, FRAMEWORK
	 * @param description      of the Statistical Standard in the selected language
	 * @param localId          of the Statistical Standard 
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of agent (default 'First
	 *                         Version')
	 * @param language         selected
	 * @return StatisticalStandardDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/statistical/standards/{id}")
	public StatisticalStandardDTO updateStatisticalStandard(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final StatisticalStandardType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateStatisticalStandardCommand command = UpdateStatisticalStandardCommand.create(jwt, id, name,
				description, type, localId, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * FIXME Francesco Method to delete an Statistical Standard
	 * 
	 * @param jwt authorization token
	 * @param id  of Statistical Standard to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/statistical/standards/{id}")
	public DTOBoolean deleteStatisticalStandard(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteStatisticalStandardCommand command = DeleteStatisticalStandardCommand.create(jwt, id);

		return sendCommand(command, "referential").getEvent().getData();
	}

	/**
	 *  FIXME FRancesco
	 * @param jwt              authorization token
	 * @param name             of the legislative reference in selected language
	 * @param type             of legislative reference: CLASSIFICATIONS, CONCEPTS,
	 *                         DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 *                         RECOMMENDATIONS, FRAMEWORK
	 * @param description      of the legislative reference in the selected language
	 * @param localId          of the legislative reference 
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of agent (default 'First
	 *                         Version')
	 * @param language         selected
	 * @return LegislativeReferenceDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/legislative/references")
	public LegislativeReferenceDTO createLegislativeReference(@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final LegislativeType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "number", required = false) final Integer number,
			@RequestParam(name = "approvalDate", required = false) final LocalDateTime approvalDate,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateLegislativeReferenceCommand command = CreateLegislativeReferenceCommand.create(jwt, name, description,
				localId, number,approvalDate,type, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "referential").getEvent().getData();

	}
	 
	/**  FIXME FRancesco
	 * @param jwt         authorization token
	 * @param id          of the legislative reference
	 * @param name        of the legislative reference in selected language
	 * @param type             of legislative reference: CLASSIFICATIONS, CONCEPTS,
	 *                         DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 *                         RECOMMENDATIONS, FRAMEWORK
	 * @param description      of the legislative reference in the selected language
	 * @param localId          of the legislative reference 
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of agent (default 'First
	 *                         Version')
	 * @param language         selected
	 * @return LegislativeReferenceDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/legislative/references/{id}")
	public LegislativeReferenceDTO updateLegislativeReference(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final LegislativeType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "number", required = false) final Integer number,
			@RequestParam(name = "approvalDate", required = false) final LocalDateTime approvalDate,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateLegislativeReferenceCommand command = UpdateLegislativeReferenceCommand.create(jwt, id, name,
				description, number,approvalDate,type, localId, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * FIXME Francesco Method to delete an legislative reference
	 * 
	 * @param jwt authorization token
	 * @param id  of legislative reference to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/legislative/references/{id}")
	public DTOBoolean deleteLegislativeReference(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteLegislativeReferenceCommand command = DeleteLegislativeReferenceCommand.create(jwt, id);

		return sendCommand(command, "referential").getEvent().getData();
	}

	
	
	
	/**
	 * Method to create a business function currently this method supports only
	 * adding the current version of GSBPM sub-phases 5.1
	 * 
	 * @param jwt         authentication token
	 * @param localId     of the business function (GSBPM sub-phase id)
	 * @param name        of the business function (GSBPM sub-phase) in the
	 *                    selected @param language
	 * @param description of the business function (GSBPM sub-phase) in the
	 *                    selected @param language
	 * @param language    selected language
	 * @return BusinessFunctionDTO the DTO object of the newly created business
	 *         function
	 */
	@JsonView(Views.Secure.class)
	@PutMapping("/business/functions/{local_id}")
	public BusinessFunctionDTO createBusinessFunction(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "local_id") final String localId, @RequestParam(name = "name") final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "language") final String language) {

		final CreateBusinessFunctionCommand command = CreateBusinessFunctionCommand.create(jwt, name, description,
				localId, Language.getLanguage(language));

		return sendCommand(command, "referential").getEvent().getData();

	}

	/**
	 * Method to update a business function (GSBPM sub-phase) normally to add
	 * another supported language
	 * 
	 * @param jwt         authorization token
	 * @param id          of the business function (GSBPM sub-phase)
	 * @param name        updated name in selected language
	 * @param description updated description in the selected language
	 * @param language    selected language
	 * @return BusinessFunctionDTO the updated business function (GSBPM sub-phase)
	 *         in the selected language
	 */
	@JsonView(Views.Secure.class)
	@PatchMapping("/business/functions/{id}")
	public BusinessFunctionDTO updateBusinessFunction(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name") final String name,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "language") final String language) {

		final UpdateBusinessFunctionCommand command = UpdateBusinessFunctionCommand.create(jwt, id, name, description,
				Language.getLanguage(language));

		return sendCommand(command, "referential").getEvent().getData();
	}

}
