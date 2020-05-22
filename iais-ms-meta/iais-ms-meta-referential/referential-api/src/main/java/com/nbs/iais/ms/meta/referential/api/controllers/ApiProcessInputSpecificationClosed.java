package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

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
import com.nbs.iais.ms.common.dto.impl.ProcessInputSpecificationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessInputType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddInputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.CreateInputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.DeleteInputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveInputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.UpdateInputSpecificationCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/inputs", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessInputSpecificationClosed extends AbstractController {

	/**
	 * Method to create a process input specification
	 *
	 * @param jwt                  authorization token
	 * @param processDocumentation id of the process documentation
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
	@PostMapping("/{processDocumentation}")
	public ProcessInputSpecificationDTO createInputSpecification(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "processDocumentation") final Long processDocumentation,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateInputSpecificationCommand command = CreateInputSpecificationCommand.create(jwt,
				processDocumentation, name, description, localId, version, versionDate, versionRationale,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to update a process input specification
	 *
	 * @param jwt              authorization token
	 * @param id               of the process input specification
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
	@PatchMapping("/{id}")
	public ProcessInputSpecificationDTO updateInputSpecification(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateInputSpecificationCommand command = UpdateInputSpecificationCommand.create(jwt, id, name,
				description, localId, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to add a type to a process input specification
	 *
	 * @param jwt      authorization token
	 * @param id       of the process input specification
	 * @param type     of the process input specification : PARAMETER_INPUT,
	 *                 PROCESS_SUPPORT_INPUT, TRANSFORMABLE_INPUT;
	 * 
	 * @param language selected
	 * @return InputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/type/{type}")
	public ProcessInputSpecificationDTO addInputSpecificationType(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "type") final ProcessInputType type,
			@RequestParam(name = "language", required = false) final String language) {

		final AddInputSpecificationTypeCommand command = AddInputSpecificationTypeCommand.create(jwt, id, type,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to remove a type to a process input specification
	 *
	 * @param jwt      authorization token
	 * @param id       of the process input specification
	 * @param type     of the process input specification : PARAMETER_INPUT,
	 *                 PROCESS_SUPPORT_INPUT, TRANSFORMABLE_INPUT;
	 * 
	 * @param language selected
	 * @return InputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}/type/{type}")
	public ProcessInputSpecificationDTO removeInputSpecificationType(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "type") final ProcessInputType type,
			@RequestParam(name = "language", required = false) final String language) {

		final RemoveInputSpecificationTypeCommand command = RemoveInputSpecificationTypeCommand.create(jwt, id, type,
				Language.getLanguage(language));
		return sendCommand(command, "process_inputs").getEvent().getData();

	}

	/**
	 * Method to delete an process input specification
	 *
	 * @param jwt authorization token
	 * @param id  of process input specification to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}")
	public DTOBoolean deleteInputSpecification(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteInputSpecificationCommand command = DeleteInputSpecificationCommand.create(jwt, id);

		return sendCommand(command, "process_inputs").getEvent().getData();
	}
}
