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
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.CreateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.DeleteProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/documents", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessDocumentClosed extends AbstractController {

	/**
	 * Method to create a process document
	 *
	 * @param jwt                  authorization token
	 * @param processDocumentation id of the process documentation
	 * @param name                 of the process document in selected language
	 * @param link                 of the process document
	 * @param type                 of process document: APPLICATION_PDF,
	 *                             APPLICATION_ZIP, APPLICATION_JSON,
	 *                             APPLICATION_DOC, APPLICATION_DOCX,
	 *                             APPLICATION_PPT, APPLICATION_PPTX,
	 *                             APPLICATION_ODT, APPLICATION_ODP,
	 *                             APPLICATION_ODS, IMAGE_PNG, IMAGE_JPEG,
	 *                             IMAGE_SVG, TEXT_PLAIN, TEXT_HTML, TEXT_CSV
	 * @param description          of the process document in the selected language
	 * @param localId              of the process document
	 * @param version              first version of agent (default 1.0)
	 * @param versionDate          of the agent (default now())
	 * @param versionRationale     reason of the first version of process document
	 *                             (default 'First Version')
	 * @param language             selected
	 * @return ProcessDocumentDTO
	 */
	@JsonView(Views.Extended.class)
	@PostMapping("/{processDocumentation}")
	public ProcessDocumentDTO createProcessDocument(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "processDocumentation") final Long processDocumentation,
			@RequestParam(name = "name") final String name,
			@RequestParam(name = "type") final MediaType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id") final String localId,
			@RequestParam(name = "link", required = false) final String link,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final CreateProcessDocumentCommand command = CreateProcessDocumentCommand.create(jwt, processDocumentation,
				name, description, localId, link,type, version, versionDate, versionRationale,
				Language.getLanguage(language));
		return sendCommand(command, "process_documents").getEvent().getData();

	}

	/**
	 * Method to update a process document
	 *
	 * @param jwt              authorization token
	 * @param id               of the process document
	 * @param name             of the process document in selected language
	 * @param link             of the process document in selected language
	 * 
	 * @param type             of process document: APPLICATION_PDF,
	 *                         APPLICATION_ZIP, APPLICATION_JSON, APPLICATION_DOC,
	 *                         APPLICATION_DOCX, APPLICATION_PPT, APPLICATION_PPTX,
	 *                         APPLICATION_ODT, APPLICATION_ODP, APPLICATION_ODS,
	 *                         IMAGE_PNG, IMAGE_JPEG, IMAGE_SVG, TEXT_PLAIN,
	 *                         TEXT_HTML, TEXT_CSV
	 * @param description      of the process document in the selected language
	 * @param localId          of the process document
	 * @param version          first version of agent (default 1.0)
	 * @param versionDate      of the agent (default now())
	 * @param versionRationale reason of the first version of process document
	 *                         (default 'First Version')
	 * @param language         selected
	 * @return ProcessDocumentDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{id}")
	public ProcessDocumentDTO updateProcessDocument(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "type", required = false) final MediaType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "local_id", required = false) final String localId,
			@RequestParam(name = "link", required = false) final String link,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final UpdateProcessDocumentCommand command = UpdateProcessDocumentCommand.create(jwt, id, name, description,
				type, localId, link,version, versionDate, versionRationale, Language.getLanguage(language));
		return sendCommand(command, "process_documents").getEvent().getData();

	}

	/**
	 * Method to delete an process document
	 *
	 * @param jwt authorization token
	 * @param id  of process document to delete
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{id}")
	public DTOBoolean deleteProcessDocument(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteProcessDocumentCommand command = DeleteProcessDocumentCommand.create(jwt, id);

		return sendCommand(command, "process_documents").getEvent().getData();
	}
}
