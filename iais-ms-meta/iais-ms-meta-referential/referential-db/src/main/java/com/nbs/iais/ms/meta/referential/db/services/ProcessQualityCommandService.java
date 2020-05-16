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
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.CreateProcessQualityCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality.DeleteProcessQualityCommand;
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
	public CreateProcessQualityCommand createProcessQuality(final CreateProcessQualityCommand command)
			throws AuthorizationException, EntityException {

		processDocumentationRepository.findById(command.getProcessDocumentation()).ifPresentOrElse(documentation -> {

			ProcessQualityEntity QualityEntity = CommandTranslator.translate(command);
			QualityEntity.setProcessDocumentation(documentation);
			QualityEntity = processQualityRepository.save(CommandTranslator.translate(command));
			documentation.getProcessQualityIndicators().add(QualityEntity);
			processDocumentationRepository.save(documentation);

			Translator.translate(QualityEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);

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

				Translator.translate(processQualityRepository.save(Quality), command.getLanguage())
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

	public DeleteProcessQualityCommand deleteProcessQuality(final DeleteProcessQualityCommand command)
			throws AuthorizationException, EntityException {

		try {
			final ProcessQualityEntity QualityEntity = processQualityRepository.findById(command.getId())
					.orElseThrow(() -> new EntityException(ExceptionCodes.PROCESS_QUALITY_NOT_FOUND));

			processQualityRepository.delete(QualityEntity);
		} catch (Exception e) {
			LOG.debug("Error deleting Process Quality: " + e.getMessage());
			command.getEvent().setData(DTOBoolean.FAIL);
			return command;
		}

		command.getEvent().setData(DTOBoolean.TRUE);

		return command;
	}
}
