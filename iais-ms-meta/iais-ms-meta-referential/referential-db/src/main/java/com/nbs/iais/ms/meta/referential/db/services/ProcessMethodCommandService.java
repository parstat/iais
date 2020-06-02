package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.CreateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.DeleteProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.UpdateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessMethodRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessMethodCommandService {

    @Autowired
    private ProcessMethodRepository processMethodRepository;

    /**
     * Method to create a processMethod
     * @param command the create command to execute
     * @return CreateProcessMethodCommand including the created process method into the event property
     */
    public CreateProcessMethodCommand createProcessMethod(final CreateProcessMethodCommand command) {

        Translator.translate(processMethodRepository.save(CommandTranslator.translate(command)), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;

    }

    /**
     * Method to update a process method
     * @param command the update command to execute
     * @return UpdateProcessMethodCommand including the updated process method in the event property
     */
    public UpdateProcessMethodCommand updateProcessMethod(final UpdateProcessMethodCommand command) {

        processMethodRepository.findById(command.getId()).ifPresentOrElse(
                processMethod -> {
                    CommandTranslator.translate(command, processMethod);

                    Translator.translate(processMethodRepository.save(processMethod), command.getLanguage())
                            .ifPresent(command.getEvent()::setData);
                },
                () -> {
                    throw new EntityException(ExceptionCodes.PROCESS_METHOD_NOT_FOUND);
                }
        );

        return command;
    }

    /**
     * Method to delete a process method
     * @param command the delete command to execute
     * @return DeleteProcessMethodCommand including true if deleted
     */
    public DeleteProcessMethodCommand deleteProcessMethod(final DeleteProcessMethodCommand command) {

        final AccountRole role = AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString().toUpperCase());

        if(role == AccountRole.USER) {
            throw new AuthorizationException(ExceptionCodes.NO_PERMISSION);
        }

        processMethodRepository.deleteById(command.getId());

        command.getEvent().setData(DTOBoolean.TRUE);

        return command;
    }
}
