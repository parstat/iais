package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

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
import com.nbs.iais.ms.common.dto.impl.ProcessOutputSpecificationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessOutputType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.AddOutputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.CreateOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.DeleteOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.RemoveOutputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.UpdateOutputSpecificationCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/outputs", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessOutputSpecificationClosed extends AbstractController {

	/**
	 * Method to create a process output specification
	 *
	 * @param jwt                  authorization token
	 * @param processDocumentation id of the process documentation
	 * @param name                 of the process output specification in selected
	 *                             language
	 * @param description          of the process output specification in the
	 *                             selected language
	 * @param localId              of the process output specification
	 * @param version              first version of output specification (default
	 *                             1.0)
	 * @param versionDate          of the agent (default now())
	 * @param versionRationale     reason of the first version of process output
	 *                             specification (default 'First Version')
	 * @param language             selected
	 * @return ProcessOutputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/{processDocumentation}")
	public ProcessOutputSpecificationDTO createOutputSpecification(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "processDocumentation") final Long processDocumentation,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateOutputSpecificationCommand command = CreateOutputSpecificationCommand.create(jwt,
				processDocumentation, name, description, localId, version, versionDate, versionRationale,
				Language.getLanguage(language));
		return sendCommand(command, "process_outputs").getEvent().getData();

	}

	/**
	 * Method to update a process output specification
	 *
	 * @param jwt              authorization token
	 * @param id               of the process output specification
	 * @param name             of the process output specification in selected
	 *                         language
	 * @param description      of the process output specification in the selected
	 *                         language
	 * @param localId          of the process output specification
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of process output
	 *                         specification (default 'First Version')
	 * @param language         selected
	 * @return OutputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{id}")
	public ProcessOutputSpecificationDTO updateOutputSpecification(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateOutputSpecificationCommand command = UpdateOutputSpecificationCommand.create(jwt, id, name,
				description, localId, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "process_outputs").getEvent().getData();

	}

	/**
	 * Method to add a type to a process output specification
	 *
	 * @param jwt      authorization token
	 * @param id       of the process output specification
	 * @param type     of the process output specification : TRANSFORMED_OUTPUT,
	 *                 PROCESS_METRIC, PROCESS_EXECUTION_LOG
	 * 
	 * @param language selected
	 * @return OutputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/type/{type}")
	public ProcessOutputSpecificationDTO addOutputSpecificationType(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "type") final ProcessOutputType type,
			@RequestParam(name = "language", required = false) final String language) {

		final AddOutputSpecificationTypeCommand command = AddOutputSpecificationTypeCommand.create(jwt, id, type,
				Language.getLanguage(language));
		return sendCommand(command, "process_outputs").getEvent().getData();

	}

	/**
	 * Method to remove a type to a process output specification
	 *
	 * @param jwt      authorization token
	 * @param id       of the process output specification
	 * @param type     of the process output specification : TRANSFORMED_OUTPUT,
	 *                 PROCESS_METRIC, PROCESS_EXECUTION_LOG
	 * 
	 * @param language selected
	 * @return OutputSpecificationDTO
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}/type/{type}")
	public ProcessOutputSpecificationDTO removeOutputSpecificationType(
			@RequestHeader(name = "jwt-auth") final String jwt, @PathVariable(name = "id") final Long id,
			@PathVariable(name = "type") final ProcessOutputType type,
			@RequestParam(name = "language", required = false) final String language) {

		final RemoveOutputSpecificationTypeCommand command = RemoveOutputSpecificationTypeCommand.create(jwt, id, type,
				Language.getLanguage(language));
		return sendCommand(command, "process_outputs").getEvent().getData();

	}

	/**
	 * Method to delete an process output specification
	 *
	 * @param jwt authorization token
	 * @param id  of process output specification to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}")
	public DTOBoolean deleteOutputSpecification(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteOutputSpecificationCommand command = DeleteOutputSpecificationCommand.create(jwt, id);

		return sendCommand(command, "process_outputs").getEvent().getData();
	}
}
