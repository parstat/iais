package com.nbs.iais.ms.meta.referential.api.controllers;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationOutputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationVersionCommand;
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
	 * @param jwt                authorization token
	 * @param name               of the process documentation in selected language
	 * @param description        of the process documentation in the selected
	 *                           language
	 * @param localId            of the process documentation
	 * @param version            first version of process documentation (default
	 *                           1.0)
	 * @param versionDate        of the process documentation (default now())
	 * @param versionRationale   reason of the first version of process
	 *                           documentation (default 'First Version')
	 * @param businessFunction   of the process documentation
	 * @param statisticalProgram of the process documentation
	 * @param frequency          of the process documentation
	 * @param maintainer         of the process documentation
	 * @param nextSubPhase       of the process documentation
	 * @param language           selected
	 * @return ProcessDocumentationDTO
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/program/{statistical_program}/function/{business_function}")
	public ProcessDocumentationDTO createProcessDocumentation(@RequestHeader(name = "jwt-auth") final String jwt,
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

		final CreateProcessDocumentationCommand command = CreateProcessDocumentationCommand.create(jwt, name,
				description, localId, version, versionDate, versionRationale, businessFunction, statisticalProgram,
				frequency, maintainer, nextSubPhase, Language.getLanguage(language));
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
	 * @param versionRationale reason of the first version of process documentation
	 *                         (default 'First Version')
	 * @param owner            of the process documentation
	 * @param frequency        of the process documentation
	 * @param maintainer       of the process documentation
	 * @param nextSubPhase     of the process documentation
	 * @param language         selected
	 * @return ProcessDocumentationDTO
	 */
	@JsonView(Views.Extended.class)
	@PatchMapping("/{id}")
	public ProcessDocumentationDTO updateProcessDocumentation(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @RequestParam(name = "name", required = false) final String name,
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
				description, localId, version, versionDate, versionRationale, owner, frequency, maintainer,
				nextSubPhase, Language.getLanguage(language));
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
	public DTOBoolean deleteProcessDocumentation(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id) {

		final DeleteProcessDocumentationCommand command = DeleteProcessDocumentationCommand.create(jwt, id);

		return sendCommand(command, "process_documentation").getEvent().getData();
	}

	/**
	 * API method to add an existent statistical standard to a process documentation
	 *
	 * @param id       id of the process documentation
	 * @param standard id of the statistical standard to add
	 * @param jwt      token in the header of the request
	 * @param language to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/standards/{standard}")
	public ProcessDocumentationDTO addProcessDocumentationStandard(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "standard") final Long standard,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationStandardCommand command = AddProcessDocumentationStandardCommand.create(jwt, id,
				standard, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * API method to add an existent process document to a process documentation
	 *
	 * @param id       id of the process documentation
	 * @param document id of the process document to add
	 * @param jwt      token in the header of the request
	 * @param language to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/document/{document}")
	public ProcessDocumentationDTO addProcessDocumentationDocument(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "document") final Long document,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationDocumentCommand command = AddProcessDocumentationDocumentCommand.create(jwt, id,
				document, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * API method to add an existent input specification to a process documentation
	 *
	 * @param id                  id of the process documentation
	 * @param input_specification id of the input specification to add
	 * @param jwt                 token in the header of the request
	 * @param language            to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/input/{input_specification}")
	public ProcessDocumentationDTO addProcessDocumentationInput(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id,
			@PathVariable(name = "input_specification") final Long inputSpecification,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationInputCommand command = AddProcessDocumentationInputCommand.create(jwt, id,
				inputSpecification, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * API method to add an existent process method to a process documentation
	 *
	 * @param id       id of the process documentation
	 * @param method   id of the process method to add
	 * @param jwt      token in the header of the request
	 * @param language to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/method/{method}")
	public ProcessDocumentationDTO addProcessDocumentationMethod(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "method") final Long method,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationMethodCommand command = AddProcessDocumentationMethodCommand.create(jwt, id,
				method, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}


	/**
	 * API method to add an existent output specification to a process documentation
	 *
	 * @param id                   id of the process documentation
	 * @param output_specification id of the output specification to add
	 * @param jwt                  token in the header of the request
	 * @param language             to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/output/{output_specification}")
	public ProcessDocumentationDTO addProcessDocumentationOutput(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id,
			@PathVariable(name = "output_specification") final Long outputSpecification,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationOutputCommand command = AddProcessDocumentationOutputCommand.create(jwt, id,
				outputSpecification, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * API quality to add an existent process quality to a process documentation
	 *
	 * @param id       id of the process documentation
	 * @param quality  id of the process quality to add
	 * @param jwt      token in the header of the request
	 * @param language to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/quality/{quality}")
	public ProcessDocumentationDTO addProcessDocumentationQuality(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "quality") final Long quality,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationQualityCommand command = AddProcessDocumentationQualityCommand.create(jwt, id,
				quality, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}

	/**
	 * API version to add an existent process version to a process documentation
	 *
	 * @param id       id of the process documentation
	 * @param version  id of the process version to add
	 * @param jwt      token in the header of the request
	 * @param language to present the returned DTO
	 * @return ProcessDocumentationDTO (the update process documentation)
	 */
	@JsonView(Views.Extended.class)
	@PutMapping("/{id}/version/{version}")
	public ProcessDocumentationDTO addProcessDocumentationVersion(@RequestHeader(name = "jwt-auth") final String jwt,
			@PathVariable(name = "id") final Long id, @PathVariable(name = "version") final Long version,
			@RequestParam(name = "language", required = false) final String language) {

		final AddProcessDocumentationVersionCommand command = AddProcessDocumentationVersionCommand.create(jwt, id,
				version, Language.getLanguage(language));

		return sendCommand(command, "process_documentation").getEvent().getData();

	}

}
