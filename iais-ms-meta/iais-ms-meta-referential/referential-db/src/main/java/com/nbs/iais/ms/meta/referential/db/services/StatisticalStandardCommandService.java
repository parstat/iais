package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticalStandardCommandService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;

    /**
     * Method to create an Statistical Standard
     *
     * @param command to execute
     * @return CreateStatisticalStandardCommand including the dto of
     *         StatisticalStandard in the event
     * @throws EntityException when the command includes a parent that can not be
     *                         found
     */
    public CreateStatisticalStandardCommand createStatisticalStandard(final CreateStatisticalStandardCommand command)
            throws AuthorizationException, EntityException {

        final StatisticalStandardReferenceEntity standardEntity = statisticalStandardReferenceRepository
                .save(CommandTranslator.translate(command));

        Translator.translate(standardEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
        return command;
    }

    /**
     * Method to update a StatisticalStandard
     *
     * @param command to execute
     * @return UpdateStatisticalStandardCommand including the dto of updated entity
     *         in the event
     */
    public UpdateStatisticalStandardCommand updateStatisticalStandard(final UpdateStatisticalStandardCommand command)
            throws AuthorizationException {

        if (command.getId() != null) {
            statisticalStandardReferenceRepository.findById(command.getId()).ifPresentOrElse(standard -> {
                CommandTranslator.translate(command, standard);

                Translator.translate(statisticalStandardReferenceRepository.save(standard), command.getLanguage())
                        .ifPresent(command.getEvent()::setData);
            }, () -> {
                throw new EntityException(ExceptionCodes.STANDARD_REFERENCE_NOT_FOUND);
            });

        }

        return command;
    }

    /**
     * Method to delete a Statistical Standard
     *
     * @param command to execute
     * @return DTOBoolean
     * @throws EntityException STANDARD_REFERENCE_NOT_FOUND when the Statistical Standard can not be
     *                                      found
     */

    public DeleteStatisticalStandardCommand deleteStatisticalStandard(final DeleteStatisticalStandardCommand command)
            throws AuthorizationException, EntityException {
        try {
            statisticalStandardReferenceRepository.deleteById(command.getId());
        } catch (Exception e) {
            LOG.debug("Error deleting agent: " + e.getMessage());
            command.getEvent().setData(DTOBoolean.FAIL);
            return command;
        }

        command.getEvent().setData(DTOBoolean.TRUE);

        return command;
    }
}
