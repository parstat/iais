package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.CreateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.DeleteProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.UpdateProcessDocumentationCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/documentations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessDocumentationClosed extends AbstractController {

	private static Logger LOG = LoggerFactory.getLogger(ApiProcessDocumentationClosed.class);


	/**
	 * Method to create a process documentation
	 * 
	 * @param jwt              authorization token
	 * @param name             of the process documentation in selected language
	 * @param description      of the process documentation in the selected language
	 * @param localId          of the process documentation
	 * @param version          first version of process documentation (default 1.0)
	 * @param versionDate      of the process documentation (default now())
	 * @param versionRationale reason of the first version of process documentation (default 'First
	 *                         Version')
	 * @param businessFunction of the process documentation
	 * @param statisticalProgram  of the process documentation
	 * @param frequency      of the process documentation
	 * @param maintainer     of the process documentation
	 * @param nextSubPhase   of the process documentation
	 * @param language       selected
	 * @return ProcessDocumentationDTO
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/program/{statistical_program}/function/{business_function}")
	public ProcessDocumentationDTO createProcessDocumentation(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@PathVariable(name = "business_function") final Long businessFunction,
			@PathVariable(name = "statistical_program") final Long statisticalProgram,
			@RequestParam(name = "frequency", required = false) final Frequency frequency,
			@RequestParam(name = "maintainer", required = false) final Long maintainer,
			@RequestParam(name = "nextSubPhase", required = false) final String nextSubPhase,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateProcessDocumentationCommand command = CreateProcessDocumentationCommand.create(jwt, name, description,
				localId, version, versionDate, versionRationale, businessFunction, statisticalProgram, frequency, maintainer, nextSubPhase ,Language.getLanguage(language));
		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * Method to update a statistical standard
	 * 
	 * @param jwt              authorization token
	 * @param id               of the process documentation
	 * @param name             of the process documentation in selected language
	 * @param description      of the process documentation in the selected language
	 * @param localId          of the process documentation
	 * @param version          first version of process documentation (default 1.0)
	 * @param versionDate      of the process documentation (default now())
	 * @param versionRationale reason of the first version of process documentation (default 'First
	 *                         Version')
	 * @param owner            of the process documentation
	 * @param frequency        of the process documentation
	 * @param maintainer       of the process documentation
	 * @param nextSubPhase     of the process documentation
	 * @param language         selected
	 * @return ProcessDocumentationDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{id}")
	public ProcessDocumentationDTO updateProcessDocumentation(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "owner", required = false) final Long owner,
			@RequestParam(name = "frequency", required = false) final Frequency frequency,
			@RequestParam(name = "maintainer", required = false) final Long maintainer,
			@RequestParam(name = "nextSubPhase", required = false) final String nextSubPhase,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateProcessDocumentationCommand command = UpdateProcessDocumentationCommand.create(jwt, id, name,
				description, localId, version, versionDate, versionRationale, owner, frequency, maintainer, nextSubPhase ,Language.getLanguage(language));
		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * Method to delete an process documentation
	 * 
	 * @param jwt authorization token
	 * @param id  of Statistical Standard to delete
	 * @return DTOBoolean true if the process documentation has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}")
	public DTOBoolean deleteProcessDocumentation(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteProcessDocumentationCommand command = DeleteProcessDocumentationCommand.create(jwt, id);

		return sendCommand(command, "referential").getEvent().getData();
	}

}
