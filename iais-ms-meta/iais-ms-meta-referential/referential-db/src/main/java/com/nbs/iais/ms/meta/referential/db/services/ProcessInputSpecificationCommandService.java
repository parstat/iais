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
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddInputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.CreateInputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.DeleteInputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveInputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.UpdateInputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessInputSpecificationRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

@Service
public class ProcessInputSpecificationCommandService {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessInputSpecificationCommandService.class);

	@Autowired
	private ProcessInputSpecificationRepository processInputSpecificationRepository;

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;

	/**
	 * Method to create an Process InputSpecification
	 *
	 * @param command to execute
	 * @return CreateProcessInputSpecificationCommand including the dto of
	 *         ProcessInputSpecification in the event
	 * 
	 */
	public CreateInputSpecificationCommand createProcessInputSpecification(
			final CreateInputSpecificationCommand command) throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getProcessDocumentation()).ifPresentOrElse(documentation -> {

			ProcessInputSpecificationEntity InputSpecificationEntity = CommandTranslator.translate(command);
			InputSpecificationEntity.setProcessDocumentation(documentation);
			InputSpecificationEntity = processInputSpecificationRepository.save(InputSpecificationEntity);

			Translator.translate(InputSpecificationEntity, command.getLanguage())
					.ifPresent(command.getEvent()::setData);

		}, () -> {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
		});

		return command;
	}

	/**
	 * Method to update a ProcessInputSpecification
	 *
	 * @param command to execute
	 * @return UpdateProcessInputSpecificationCommand including the dto of updated
	 *         entity in the event
	 * @throws EntityException PROCESS_INPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process InputSpecification can not be found
	 */
	public UpdateInputSpecificationCommand updateProcessInputSpecification(
			final UpdateInputSpecificationCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			processInputSpecificationRepository.findById(command.getId()).ifPresentOrElse(InputSpecification -> {
				CommandTranslator.translate(command, InputSpecification);

				Translator
						.translate(processInputSpecificationRepository.save(InputSpecification), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION__NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to add a type to a ProcessInputSpecification
	 *
	 * @param command to execute
	 * @return AddInputSpecificationTypeCommand including the dto of updated entity
	 *         in the event
	 * @throws EntityException PROCESS_INPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process InputSpecification can not be found
	 */
	public AddInputSpecificationTypeCommand addInputSpecificationTypeCommand(
			final AddInputSpecificationTypeCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			processInputSpecificationRepository.findById(command.getId()).ifPresentOrElse(inputSpecification -> {

				if (!inputSpecification.getProcessInputTypes().contains(command.getType())) {
					inputSpecification.getProcessInputTypes().add(command.getType());
					Translator.translate(processInputSpecificationRepository.save(inputSpecification),
							command.getLanguage()).ifPresent(command.getEvent()::setData);
				} else {
					Translator.translate(inputSpecification, command.getLanguage())
							.ifPresent(command.getEvent()::setData);
				}

			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION__NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to remove a type to a ProcessInputSpecification
	 *
	 * @param command to execute
	 * @return RemoveInputSpecificationTypeCommand including the dto of updated
	 *         entity in the event
	 * @throws EntityException PROCESS_INPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process InputSpecification can not be found
	 */
	public RemoveInputSpecificationTypeCommand removeInputSpecificationTypeCommand(
			final RemoveInputSpecificationTypeCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			processInputSpecificationRepository.findById(command.getId()).ifPresentOrElse(inputSpecification -> {

				if (inputSpecification.getProcessInputTypes().contains(command.getType())) {
					inputSpecification.getProcessInputTypes().remove(command.getType());
					Translator.translate(processInputSpecificationRepository.save(inputSpecification),
							command.getLanguage()).ifPresent(command.getEvent()::setData);
				} else {
					Translator.translate(inputSpecification, command.getLanguage())
							.ifPresent(command.getEvent()::setData);
				}

			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION__NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to delete a Process InputSpecification
	 *
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws EntityException PROCESS_INPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process InputSpecification can not be found
	 */

	public DeleteInputSpecificationCommand deleteProcessInputSpecification(
			final DeleteInputSpecificationCommand command) throws AuthorizationException, EntityException {

		try {
			final ProcessInputSpecificationEntity InputSpecificationEntity = processInputSpecificationRepository
					.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION__NOT_FOUND));

			processInputSpecificationRepository.delete(InputSpecificationEntity);
		} catch (Exception e) {
			LOG.debug("Error deleting Process Input Specification: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}
}
