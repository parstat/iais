package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.QualityType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.AddProcessDocumentationQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.RemoveProcessDocumentationQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.UpdateProcessQualityCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/documentations/{documentation}/qualities", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessQualityClosed extends AbstractController {

	/**
	 * Method to create a process quality
	 *
	 * @param jwt              authorization token
	 * @param documentation id of the process documentation
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
	@PostMapping("/")
	public ProcessDocumentationDTO addProcessDocumentationQuality(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final QualityType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationQualityCommand command = AddProcessDocumentationQualityCommand.create(jwt, documentation, name,
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
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
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
	 * @param documentation  the id of the process documentation to remove quality
	 * @param quality the id of process quality to remove
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{quality}")
	public ProcessDocumentationDTO deleteProcessQuality(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@PathVariable(name = "quality") final Long quality,
			@RequestParam(name = "language", required = false) final String language) {

		final RemoveProcessDocumentationQualityCommand command = RemoveProcessDocumentationQualityCommand.create(jwt,
				documentation, quality, Language.getLanguage(language));

		return sendCommand(command, "process_qualities").getEvent().getData();
	}
}
