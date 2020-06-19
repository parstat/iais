package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessInputType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveProcessDocumentationInputTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.UpdateProcessDocumentationInputCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/documentations/{documentation}/inputs", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessInputSpecificationClosed extends AbstractController {

	/**
	 * Method to create a process input specification
	 *
	 * @param jwt                  authorization token
	 * @param documentation        id of the process documentation
	 * @param name                 of the process input specification in selected
	 *                             language
	 * @param description          of the process input specification in the
	 *                             selected language
	 * @param localId              of the process input specification
	 * @param version              first version of input specification (default
	 *                             1.0)
	 * @param versionDate          of the agent (default now())
	 * @param versionRationale     reason of the first version of process input
	 *                             specification (default 'First Version')
	 * @param language             selected
	 * @return ProcessInputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/")
	public ProcessDocumentationDTO createInputSpecification(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "localId", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationInputCommand command = AddProcessDocumentationInputCommand.create(jwt,
				documentation, name, description, localId, version, versionDate, versionRationale,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to update a process input specification
	 *
	 * @param jwt              authorization token
	 * @param input             of the process input specification
	 * @param name             of the process input specification in selected
	 *                         language
	 * @param description      of the process input specification in the selected
	 *                         language
	 * @param localId          of the process input specification
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of process input
	 *                         specification (default 'First Version')
	 * @param language         selected
	 * @return InputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{input}")
	public ProcessDocumentationDTO updateInputSpecification(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@PathVariable(name = "input") final Long input,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateProcessDocumentationInputCommand command = UpdateProcessDocumentationInputCommand.create(jwt,
				documentation, input, name, description, localId, version, versionDate, versionRationale,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to add a type to a process input specification
	 *
	 * @param jwt      authorization token
	 * @param input       of the process input specification
	 * @param type     of the process input specification : PARAMETER_INPUT,
	 *                 PROCESS_SUPPORT_INPUT, TRANSFORMABLE_INPUT;
	 * 
	 * @param language selected
	 * @return InputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{input}/types/{type}")
	public ProcessDocumentationDTO addInputSpecificationType(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@PathVariable(name = "input") final Long input,
			@PathVariable(name = "type") final ProcessInputType type,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationInputTypeCommand command = AddProcessDocumentationInputTypeCommand.create(jwt, documentation, input, type,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to remove a type to a process input specification
	 *
	 * @param jwt      authorization token
	 * @param documentation       of the process input specification
	 * @param input    id of input specification to remove type
	 * @param type     of the process input specification : PARAMETER_INPUT,
	 *                 PROCESS_SUPPORT_INPUT, TRANSFORMABLE_INPUT;
	 * 
	 * @param language selected
	 * @return InputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{input}/type/{type}")
	public ProcessDocumentationDTO removeInputSpecificationType(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@PathVariable(name = "input") final Long input,
			@PathVariable(name = "type") final ProcessInputType type,
			@RequestParam(name = "language", required = false) final String language) {

		final RemoveProcessDocumentationInputTypeCommand command = RemoveProcessDocumentationInputTypeCommand.create(jwt, documentation, input, type,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to delete an process input specification
	 *
	 * @param jwt authorization token
	 * @param input  of process input specification to delete
	 * @param documentation the id of documentation to delete the input (for checking)
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{input}")
	public ProcessDocumentationDTO deleteInputSpecification(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@PathVariable(name = "input") final Long input,
			@RequestParam(name = "language") final String language) {
		final RemoveProcessDocumentationInputCommand command = RemoveProcessDocumentationInputCommand.create(jwt,
				documentation, input, Language.getLanguage(language));

		return sendCommand(command, "process_inputs").getEvent().getData();
	}
}
