package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegislativeReferenceCommandService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private LegislativeReferenceRepository legislativeReferenceRepository;


    /**
     * Method to create an legislative reference
     *
     * @param command to execute
     * @return CreateLegislativeReferenceCommand including the dto of  LegislativeReference in the event

     */
    public CreateLegislativeReferenceCommand createLegislativeReference(final CreateLegislativeReferenceCommand command)
            throws AuthorizationException, EntityException {

        final LegislativeReferenceEntity referenceEntity = legislativeReferenceRepository
                .save(CommandTranslator.translate(command));

        Translator.translate(referenceEntity, command.getLanguage()).ifPresent(command.getEvent()::setData);
        return command;
    }

    /**
     * Method to update a LegislativeReference
     *
     * @param command to execute
     * @return UpdateLegislativeReferenceCommand including the dto of updated entity
     *         in the event
     * @throws EntityException LEGISLATIVE_REFERENCE_NOT_FOUND when the legislative reference can not be found
     */
    public UpdateLegislativeReferenceCommand updateLegislativeReference(final UpdateLegislativeReferenceCommand command)
            throws AuthorizationException, EntityException {

        if (command.getId() != null) {
            legislativeReferenceRepository.findById(command.getId()).ifPresentOrElse(reference -> {
                CommandTranslator.translate(command, reference);

                Translator.translate(legislativeReferenceRepository.save(reference), command.getLanguage())
                        .ifPresent(command.getEvent()::setData);
            }, () -> {
                throw new EntityException(ExceptionCodes.LEGISLATIVE_REFERENCE_NOT_FOUND);
            });

        }

        return command;
    }

    /**
     * Method to delete an legislative reference
     *
     * @param command to execute
     * @return DTOBoolean
     * @throws EntityException LEGISLATIVE_REFERENCE_NOT_FOUND when the legislative reference can not be found
     */

    public DeleteLegislativeReferenceCommand deleteLegislativeReference(final DeleteLegislativeReferenceCommand command)
            throws AuthorizationException, EntityException {

        try {
            legislativeReferenceRepository.deleteById(command.getId());
        } catch (Exception e) {
            LOG.debug("Error deleting agent: " + e.getMessage());
            command.getEvent().setData(DTOBoolean.FAIL);
            return command;
        }

        command.getEvent().setData(DTOBoolean.TRUE);

        return command;
    }

}


