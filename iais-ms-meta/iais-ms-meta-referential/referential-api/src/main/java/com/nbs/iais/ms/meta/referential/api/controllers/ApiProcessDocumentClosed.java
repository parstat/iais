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
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentDTO;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.AddProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.RemoveProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;

@RestController
@RequestMapping(value = "/api/v1/close/referential/process/documentations/{documentation}/documents", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessDocumentClosed extends AbstractController {

	/**
	 * Method to create a process document
	 *
	 * @param jwt                  authorization token
	 * @param documentation        id of the process documentation
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
	@PostMapping("/")
	public ProcessDocumentationDTO addProcessDocumentationDocument(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@RequestParam(name = "name") final String name,
			@RequestParam(name = "type", required = false) final MediaType type,
			@RequestParam(name = "description", required = false) final String description,
			@RequestParam(name = "localId", required = false) final String localId,
			@RequestParam(name = "link", required = false) final String link,
			@RequestParam(name = "version", required = false) final String version,
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
			@RequestParam(name = "versionRationale", required = false) final String versionRationale,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationDocumentCommand command = AddProcessDocumentationDocumentCommand.create(jwt, documentation,
				name, description, localId, link, type, version, versionDate, versionRationale,
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
			@RequestParam(name = "versionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime versionDate,
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
	 * @param documentation  the id of the process documentation
	 * @param document the id of the document to remove
	 * @return DTOBoolean true if the agent has been deleted
	 */
	@JsonView(Views.Extended.class)
	@DeleteMapping("/{document}")
	public ProcessDocumentationDTO deleteProcessDocument(
			@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "documentation") final Long documentation,
			@PathVariable(name = "document") final Long document,
			@RequestParam(name = "language", required = false) final String language) {

		final RemoveProcessDocumentationDocumentCommand command = RemoveProcessDocumentationDocumentCommand.create(jwt,
				documentation, document, Language.getLanguage(language));

		return sendCommand(command, "process_documents").getEvent().getData();
	}
}
