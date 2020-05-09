package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AuthorizationException;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessFunctionCommandService {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessDocumentationCommandService.class);

    @Autowired
    private BusinessFunctionRepository businessFunctionRepository;

    /**
     * Method to create a business function only ADMIN and ROOT users allowed
     *
     * @param command to execute
     * @return command including the created business function in the event
     * @throws AuthorizationException when the request is from a user that is not
     *                                ADMIN or ROOT
     * @throws EntityException        when the business function already exists
     */
    public CreateBusinessFunctionCommand createBusinessFunction(final CreateBusinessFunctionCommand command)
            throws AuthorizationException, EntityException {

        // check if user has permission (has role ADMIN or ROOT)
        if (AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
            throw new AuthorizationException(ExceptionCodes.NO_PERMISSION);
        }

        // check if business function already exists
        if (businessFunctionRepository.findByLocalIdAndVersion(command.getLocalId(), command.getVersion())
                .isPresent()) {
            throw new EntityException(ExceptionCodes.BUSINESS_FUNCTION_EXISTS);
        }

        final BusinessFunctionEntity businessFunctionEntity = CommandTranslator.translate(command);

        Translator.translate(businessFunctionRepository.save(businessFunctionEntity), command.getLanguage())
                .ifPresent(command.getEvent()::setData);

        return command;
    }

    /**
     * Method to update a business function usually to add name and description in
     * other languages
     *
     * @param command to execute
     * @return UpdateBusinessFunctionCommand including the dto of updated business
     *         function in the event
     * @throws AuthorizationException when user is not an ADMIN or ROOT
     */
    public UpdateBusinessFunctionCommand updateBusinessFunction(final UpdateBusinessFunctionCommand command)
            throws AuthorizationException {

        if (AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString()) == AccountRole.USER) {
            throw new AuthorizationException("You have no permission to perform this operation",
                    ExceptionCodes.NO_PERMISSION);
        }
        if (command.getId() != null) {
            businessFunctionRepository.findById(command.getId()).ifPresent(bf -> {
                CommandTranslator.translate(command, bf);
                Translator.translate(businessFunctionRepository.save(bf), command.getLanguage())
                        .ifPresent(command.getEvent()::setData);
            });

        }
        return command;
    }
}
