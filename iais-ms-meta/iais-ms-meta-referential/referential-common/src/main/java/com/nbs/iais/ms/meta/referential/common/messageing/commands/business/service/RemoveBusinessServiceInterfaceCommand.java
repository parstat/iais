package com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.service.RemoveBusinessServiceInterfaceEvent;

public class RemoveBusinessServiceInterfaceCommand extends AbstractCommand<RemoveBusinessServiceInterfaceEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * Id of Business Service to add the interface
     */
    private Long id;

    /**
     * Interface of service (not always referred to it programs)
     */
    private String serviceInterface;

    private RemoveBusinessServiceInterfaceCommand() {
        super(new RemoveBusinessServiceInterfaceEvent());
    }

    private RemoveBusinessServiceInterfaceCommand(final String jwt, final Long id, final String serviceInterface,
                                               final Language language) {
        super(new RemoveBusinessServiceInterfaceEvent());
        this.id = id;
        this.serviceInterface = serviceInterface;
        setJwt(jwt);
        setLanguage(language);
    }

    public static RemoveBusinessServiceInterfaceCommand create(final String jwt, final Long id, final String serviceInterface,
                                                            final Language language) {

        return new RemoveBusinessServiceInterfaceCommand(jwt, id, serviceInterface, language);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(final String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }
}
