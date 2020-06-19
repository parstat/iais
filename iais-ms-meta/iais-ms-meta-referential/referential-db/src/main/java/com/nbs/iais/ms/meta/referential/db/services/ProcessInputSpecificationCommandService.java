package com.nbs.iais.ms.meta.referential.db.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.AddProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveProcessDocumentationInputCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.RemoveProcessDocumentationInputTypeCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification.UpdateProcessDocumentationInputCommand;
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
	public AddProcessDocumentationInputCommand addProcessInputSpecification(
			final AddProcessDocumentationInputCommand command) throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(documentation -> {

			final ProcessInputSpecificationEntity inputSpecification = CommandTranslator.translate(command);

			LOG.debug("Here: " + inputSpecification.getName(command.getLanguage()));

			documentation.addProcessInput(inputSpecification);

			LOG.debug("inputs: " + documentation.getProcessInputs().get(0).getName());
			Translator.translate(processDocumentationRepository.save(documentation), command.getLanguage())
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
	public UpdateProcessDocumentationInputCommand updateProcessInputSpecification(
			final UpdateProcessDocumentationInputCommand command) throws AuthorizationException {

			processInputSpecificationRepository.findByIdAndProcessDocumentation(command.getInput(), command.getDocumentation()).ifPresentOrElse(inputSpecification -> {
					CommandTranslator.translate(command, inputSpecification);

					Translator.translate(processInputSpecificationRepository.save(inputSpecification)
							.getProcessDocumentation(), command.getLanguage())
							.ifPresent(command.getEvent()::setData);

			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION_NOT_FOUND);
			});


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
	public AddProcessDocumentationInputTypeCommand addInputSpecificationTypeCommand(
			final AddProcessDocumentationInputTypeCommand command) throws AuthorizationException {

		processInputSpecificationRepository.findByIdAndProcessDocumentation(command.getInput(),
				command.getDocumentation()).ifPresentOrElse(processInputSpecification -> {
					if(!processInputSpecification.getProcessInputTypes().contains(command.getType())) {
						processInputSpecification.getProcessInputTypes().add(command.getType());
						Translator.translate(processInputSpecificationRepository.save(processInputSpecification)
								.getProcessDocumentation(), command.getLanguage())
								.ifPresent(command.getEvent()::setData);
					}
				},
				() -> {
					throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION_NOT_FOUND);
				});
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
	public RemoveProcessDocumentationInputTypeCommand removeInputSpecificationTypeCommand(
			final RemoveProcessDocumentationInputTypeCommand command) throws AuthorizationException {

		processInputSpecificationRepository.findByIdAndProcessDocumentation(command.getInput(),
				command.getDocumentation()).ifPresentOrElse(processInputSpecification -> {
					if(processInputSpecification.getProcessInputTypes().contains(command.getType())) {
						processInputSpecification.getProcessInputTypes().remove(command.getType());
						Translator.translate(processInputSpecificationRepository.save(processInputSpecification)
								.getProcessDocumentation(), command.getLanguage())
								.ifPresent(command.getEvent()::setData);
					}
				},
				() -> {
					throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION_NOT_FOUND);
				});
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

	public RemoveProcessDocumentationInputCommand deleteProcessInputSpecification(
			final RemoveProcessDocumentationInputCommand command) throws AuthorizationException, EntityException {
		processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(processDocumentation ->
			processInputSpecificationRepository.findById(command.getInput()).ifPresentOrElse(processInputSpecification -> {
					processDocumentation.removeProcessInput(processInputSpecification);

					Translator.translate(processDocumentationRepository.save(processDocumentation),
							command.getLanguage()).ifPresent(command.getEvent()::setData);
					},
					() -> {
						throw new EntityException(ExceptionCodes.PROCESS_INPUT_SPECIFICATION_NOT_FOUND);
					}), () -> {
						throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
			});

		return command;
	}
}
