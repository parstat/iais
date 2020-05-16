package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ProcessQualityDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.QualityType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.CreateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.DeleteProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.UpdateProcessQualityCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/qualities", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessQualityClosed extends AbstractController {

	/**
	 * Method to create a process quality
	 *
	 * @param jwt              authorization token
	 * @param processDocumentation id of the process documentation
	 * @param name             of the process quality in selected language
	 * @param type             of process quality: INDICATOR, CONTROL
	 * @param description      of the process quality in the selected language
	 * @param localId          of the process quality
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of process quality
	 *                         (default 'First Version')
	 * @param language         selected
	 * @return ProcessQualityDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/{processDocumentation}")
	public ProcessQualityDTO createProcessQuality(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "processDocumentation") final Long processDocumentation,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final QualityType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateProcessQualityCommand command = CreateProcessQualityCommand.create(jwt, processDocumentation, name,
				description, localId, type, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "process_qualities").getEvent().getData();

	}

	/**
	 * Method to update a process quality
	 *
	 * @param jwt              authorization token
	 * @param id               of the process quality
	 * @param name             of the process quality in selected language
	 * @param type             of process quality: INDICATOR, CONTROL
	 * @param description      of the process quality in the selected language
	 * @param localId          of the process quality
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of process quality
	 *                         (default 'First Version')
	 * @param language         selected
	 * @return ProcessQualityDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{id}")
	public ProcessQualityDTO updateProcessQuality(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final QualityType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateProcessQualityCommand command = UpdateProcessQualityCommand.create(jwt, id, name, description, type,
				localId, version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "process_qualities").getEvent().getData();

	}

	/**
	 * Method to delete an process quality
	 *
	 * @param jwt authorization token
	 * @param id  of process quality to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}")
	public DTOBoolean deleteProcessQuality(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteProcessQualityCommand command = DeleteProcessQualityCommand.create(jwt, id);

		return sendCommand(command, "process_qualities").getEvent().getData();
	}
}
