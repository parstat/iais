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
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.CreateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.DeleteProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

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
	public CreateProcessDocumentCommand createProcessDocument(final CreateProcessDocumentCommand command)
			throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getProcessDocumentation()).ifPresentOrElse(documentation -> {

			ProcessDocumentEntity documentEntity = CommandTranslator.translate(command);
			documentEntity.setProcessDocumentation(documentation);
			documentEntity = processDocumentRepository.save(documentEntity);
			documentation.getProcessDocuments().add(documentEntity);
			processDocumentationRepository.save(documentation);

			Translator.translate(documentEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);

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

				Translator.translate(processDocumentRepository.save(document), command.getLanguage())
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

	public DeleteProcessDocumentCommand deleteProcessDocument(final DeleteProcessDocumentCommand command)
			throws AuthorizationException, EntityException {

		try {
			final ProcessDocumentEntity documentEntity = processDocumentRepository.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_DOCUMENT_NOT_FOUND));

			processDocumentRepository.delete(documentEntity);
		} catch (Exception e) {
			LOG.debug("Error deleting Process Document: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}
}
