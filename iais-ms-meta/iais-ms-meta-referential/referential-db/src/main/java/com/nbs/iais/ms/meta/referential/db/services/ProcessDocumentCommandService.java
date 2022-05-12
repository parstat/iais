package com.nbs.iais.ms.meta.referential.db.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.AddProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.RemoveProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProcessDocumentCommandService {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

	@Autowired
	private ProcessDocumentRepository processDocumentRepository;

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;

	/**
	 * Method to create an Process Document
	 *
	 * @param command to execute
	 * @return CreateProcessDocumentCommand including the dto of ProcessDocument in
	 *         the event
	 * 
	 */
	public AddProcessDocumentationDocumentCommand createProcessDocument(final AddProcessDocumentationDocumentCommand command)
			throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(documentation -> {

			ProcessDocumentEntity document = CommandTranslator.translate(command);
			documentation.addProcessDocument(document);

			Translator.translate(processDocumentationRepository.save(documentation), command.getLanguage())
					.ifPresent(command.getEvent()::setData);

		}, () -> {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
		});

		return command;
	}

	/**
	 * Method to update a ProcessDocument
	 *
	 * @param command to execute
	 * @return UpdateProcessDocumentCommand including the dto of updated entity in
	 *         the event
	 * @throws EntityException PROCESS_DOCUMENT_NOT_FOUND when the Process Document
	 *                         can not be found
	 */
	public UpdateProcessDocumentCommand updateProcessDocument(final UpdateProcessDocumentCommand command)
			throws AuthorizationException {

		if (command.getId() != null) {
			processDocumentRepository.findById(command.getId()).ifPresentOrElse(document -> {
				CommandTranslator.translate(command, document);

				Translator.translate(processDocumentRepository.save(document).getProcessDocumentation(), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_DOCUMENT_NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to delete a Process Document
	 *
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws EntityException PROCESS_DOCUMENT_NOT_FOUND when the Process Document
	 *                         can not be found
	 */

	public RemoveProcessDocumentationDocumentCommand deleteProcessDocument(final RemoveProcessDocumentationDocumentCommand command)
			throws AuthorizationException, EntityException {

			processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(processDocumentation -> {
				final ProcessDocumentEntity document = processDocumentRepository.findById(command.getDocument())
						.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENT_NOT_FOUND));

				processDocumentation.removeProcessDocument(document);

				Translator.translate(processDocumentationRepository.save(processDocumentation), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
			});

		return command;
	}
}
