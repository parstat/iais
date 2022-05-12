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
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.AddProcessDocumentationQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.RemoveProcessDocumentationQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.UpdateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessQualityEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessDocumentationRepository;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessQualityRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;

@Service
public class ProcessQualityCommandService {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessQualityCommandService.class);

	@Autowired
	private ProcessQualityRepository processQualityRepository;

	@Autowired
	private ProcessDocumentationRepository processDocumentationRepository;

	/**
	 * Method to create an Process Quality
	 *
	 * @param command to execute
	 * @return CreateProcessQualityCommand including the dto of ProcessQuality in
	 *         the event
	 * 
	 */
	public AddProcessDocumentationQualityCommand createProcessQuality(final AddProcessDocumentationQualityCommand command)
			throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(documentation -> {

			ProcessQualityEntity processQuality = CommandTranslator.translate(command);

			documentation.addProcessQuality(processQuality);
			 
			Translator.translate(processDocumentationRepository.save(documentation), command.getLanguage())
					.ifPresent(command.getEvent()::setData);

		}, () -> {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
		});

		return command;
	}

	/**
	 * Method to update a ProcessQuality
	 *
	 * @param command to execute
	 * @return UpdateProcessQualityCommand including the dto of updated entity in
	 *         the event
	 * @throws EntityException PROCESS_QUALITY_NOT_FOUND when the Process Quality
	 *                         can not be found
	 */
	public UpdateProcessQualityCommand updateProcessQuality(final UpdateProcessQualityCommand command)
			throws AuthorizationException {

		if (command.getId() != null) {
			processQualityRepository.findById(command.getId()).ifPresentOrElse(Quality -> {
				CommandTranslator.translate(command, Quality);

				Translator.translate(processQualityRepository.save(Quality).getProcessDocumentation(), command.getLanguage())
						.ifPresent(command.getEvent()::setData);
			}, () -> {
				throw new EntityException(ExceptionCodes.PROCESS_QUALITY_NOT_FOUND);
			});

		}

		return command;
	}

	/**
	 * Method to delete a Process Quality
	 *
	 * @param command to execute
	 * @return DTOBoolean
	 * @throws EntityException PROCESS_QUALITY_NOT_FOUND when the Process Quality
	 *                         can not be found
	 */

	public RemoveProcessDocumentationQualityCommand deleteProcessQuality(final RemoveProcessDocumentationQualityCommand command)
			throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getDocumentation()).ifPresentOrElse(processDocumentation -> {
			final ProcessQualityEntity processQuality = processQualityRepository.findById(command.getQuality())
					.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_QUALITY_NOT_FOUND));

			processDocumentation.removeProcessQuality(processQuality);
			Translator.translate(processDocumentationRepository.save(processDocumentation), command.getLanguage())
					.ifPresent(command.getEvent()::setData);
		}, () -> {
			throw new EntityException(ExceptionCodes.PROCESS_DOCUMENTATION_NOT_FOUND);
		});

		return command;
	}
}
