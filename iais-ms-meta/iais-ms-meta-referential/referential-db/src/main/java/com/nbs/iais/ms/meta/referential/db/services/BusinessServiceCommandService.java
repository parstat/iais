package com.nbs.iais.ms.meta.referential.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.EntityException;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service.*;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessServiceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessServiceRepository;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceCommandService {

    private final static Logger LOG = LoggerFactory.getLogger(BusinessServiceCommandService.class);

    @Autowired
    private BusinessServiceRepository businessServiceRepository;

    /**
     * Method to create a new Business Service
     * @param command The command to execute
     * @return CreateBusinessServiceCommand including the newly created Business Service
     * @throws EntityException when business function already exists
     */
    public CreateBusinessServiceCommand createBusinessService(final CreateBusinessServiceCommand command) throws EntityException {

        if(!businessServiceRepository.existsByLocalId(command.getLocalId())) {
            final BusinessServiceEntity businessService = CommandTranslator.translate(command);
            Translator.translate(businessServiceRepository.save(businessService), command.getLanguage()).ifPresent(command.getEvent()::setData);
        } else {
            throw new EntityException(ExceptionCodes.BUSINESS_FUNCTION_EXISTS);
        }
        return command;
    }

    /**
     * Method to update business service
     * @param command The command to execute
     * @return UpdateBusinessServiceCommand
     * @throws EntityException when business service was not found
     */
    public UpdateBusinessServiceCommand updateBusinessService(final UpdateBusinessServiceCommand command)
            throws EntityException {

        businessServiceRepository.findById(command.getId()).ifPresentOrElse(businessService -> {
               CommandTranslator.translate(command, businessService);
               businessServiceRepository.save(businessService);
               Translator.translate(businessServiceRepository.save(businessService), command.getLanguage()).ifPresent(
                       command.getEvent()::setData);
        }, () -> {
            throw new EntityException(ExceptionCodes.BUSINESS_SERVICE_NOT_FOUND);
        });

        return command;
    }

    /**
     * Method to delete business service
     * @param command The command to execute
     * @return DeleteBusinessServiceCommand
     */
    public DeleteBusinessServiceCommand deleteBusinessService(final DeleteBusinessServiceCommand command) {

        final AccountRole accountRole = AccountRole.valueOf(JWT.decode(command.getJwt()).getClaim("role").asString());

        if(accountRole == AccountRole.ADMIN) {
            businessServiceRepository.findById(command.getId()).ifPresent(businessService ->
                    businessServiceRepository.delete(businessService));
            command.getEvent().setData(DTOBoolean.TRUE);
            return command;
        } else {
            throw new EntityException(ExceptionCodes.NO_PERMISSION);
        }
    }

    public AddBusinessServiceInterfaceCommand addBusinessServiceInterface(final AddBusinessServiceInterfaceCommand command) {

        businessServiceRepository.findById(command.getId()).ifPresentOrElse(businessServiceEntity ->  {
                businessServiceEntity.getServiceInterfaces().add(command.getServiceInterface());
                Translator.translate(businessServiceRepository.save(businessServiceEntity), command.getLanguage())
                        .ifPresent(command.getEvent()::setData);
        }, () -> {
            throw new EntityException(ExceptionCodes.BUSINESS_SERVICE_NOT_FOUND);
        });
        return command;
    }

    public RemoveBusinessServiceInterfaceCommand removeBusinessServiceInterface(final RemoveBusinessServiceInterfaceCommand command) {

        businessServiceRepository.findById(command.getId()).ifPresentOrElse(businessServiceEntity -> {
           businessServiceEntity.getServiceInterfaces().remove(command.getServiceInterface());
           Translator.translate(businessServiceRepository.save(businessServiceEntity), command.getLanguage())
                   .ifPresent(command.getEvent()::setData);
        }, () -> {
            throw new EntityException(ExceptionCodes.BUSINESS_SERVICE_NOT_FOUND);
        });
        return command;
    }


}
