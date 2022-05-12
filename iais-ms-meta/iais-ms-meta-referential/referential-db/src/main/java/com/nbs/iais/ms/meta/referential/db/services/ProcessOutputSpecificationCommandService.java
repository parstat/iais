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
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.AddOutputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.AddProcessDocumentationOutputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.RemoveProcessDocumentationOutputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.RemoveOutputSpecificationTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification.UpdateOutputSpecificationCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessOutputSpecificationEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessOutputSpecificationRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

@Service
public class ProcessOutputSpecificationCommandService {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessOutputSpecificationCommandService.class);

	@Autowired
	private ProcessOutputSpecificationRepository processOutputSpecificationRepository;

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;

	/**
	 * Method to create an Process OutputSpecification
	 *
	 * @param command to execute
	 * @return CreateProcessOutputSpecificationCommand including the dto of
	 *         ProcessOutputSpecification in the event
	 * 
	 */
	public AddProcessDocumentationOutputCommand addProcessDocumentationOutput(
			final AddProcessDocumentationOutputCommand command) throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(documentation -> {

			ProcessOutputSpecificationEntity outputSpecificationEntity = CommandTranslator.translate(command);

			documentation.addProcessOutput(outputSpecificationEntity);

			Translator.translate(processDocumentationRepository.save(documentation), command.getLanguage())
					.ifPresent(command.getEvent()::setData);

		}, () -> {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
		});

		return command;
	}

	/**
	 * Method to update a ProcessOutputSpecification
	 *
	 * @param command to execute
	 * @return UpdateProcessOutputSpecificationCommand including the dto of updated
	 *         entity in the event
	 * @throws EntityException PROCESS_OUTPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process OutputSpecification can not be found
	 */
	public UpdateOutputSpecificationCommand updateProcessOutputSpecification(
			final UpdateOutputSpecificationCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			processOutputSpecificationRepository.findById(command.getId()).ifPresentOrElse(OutputSpecification -> {
				CommandTranslator.translate(command, OutputSpecification);

				Translator.translate(processOutputSpecificationRepository.save(OutputSpecification).getProcessDocumentation(),
						command.getLanguage()).ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_OUTPUT_SPECIFICATION_NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to add a type to a ProcessOutputSpecification
	 *
	 * @param command to execute
	 * @return AddOutputSpecificationTypeCommand including the dto of updated entity
	 *         in the event
	 * @throws EntityException PROCESS_OUTPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process OutputSpecification can not be found
	 */
	public AddOutputSpecificationTypeCommand addOutputSpecificationTypeCommand(
			final AddOutputSpecificationTypeCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			processOutputSpecificationRepository.findById(command.getId()).ifPresentOrElse(outputSpecification -> {

				if (!outputSpecification.getProcessOutputTypes().contains(command.getType())) {
					outputSpecification.getProcessOutputTypes().add(command.getType());
					Translator.translate(processOutputSpecificationRepository.save(outputSpecification),
							command.getLanguage()).ifPresent(command.getEvent()::setData);
				} else {
					Translator.translate(outputSpecification, command.getLanguage())
							.ifPresent(command.getEvent()::setData);
				}

			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_OUTPUT_SPECIFICATION_NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to remove a type to a ProcessOutputSpecification
	 *
	 * @param command to execute
	 * @return RemoveOutputSpecificationTypeCommand including the dto of updated
	 *         entity in the event
	 * @throws EntityException PROCESS_OUTPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process OutputSpecification can not be found
	 */
	public RemoveOutputSpecificationTypeCommand removeOutputSpecificationTypeCommand(
			final RemoveOutputSpecificationTypeCommand command) throws AuthorizationException {

		if (command.getId() != null) {
			processOutputSpecificationRepository.findById(command.getId()).ifPresentOrElse(outputSpecification -> {

				if (outputSpecification.getProcessOutputTypes().contains(command.getType())) {
					outputSpecification.getProcessOutputTypes().remove(command.getType());
					Translator.translate(processOutputSpecificationRepository.save(outputSpecification),
							command.getLanguage()).ifPresent(command.getEvent()::setData);
				} else {
					Translator.translate(outputSpecification, command.getLanguage())
							.ifPresent(command.getEvent()::setData);
				}

			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_OUTPUT_SPECIFICATION_NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to delete a Process OutputSpecification
	 *
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws EntityException PROCESS_OUTPUT_SPECIFICATION__NOT_FOUND when the
	 *                         Process OutputSpecification can not be found
	 */

	public RemoveProcessDocumentationOutputCommand deleteProcessOutputSpecification(
			final RemoveProcessDocumentationOutputCommand command) throws AuthorizationException, EntityException {

			processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(processDocumentation -> {
				final ProcessOutputSpecificationEntity outputSpecification = processOutputSpecificationRepository
						.findById(command.getOutput())
						.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_OUTPUT_SPECIFICATION_NOT_FOUND));
				processDocumentation.removeProcessOutput(outputSpecification);
				Translator.translate(processDocumentationRepository.save(processDocumentation), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
			});

		return command;
	}
}
