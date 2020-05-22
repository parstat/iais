package com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.service.DeleteBusinessServiceEvent;

public class DeleteBusinessServiceCommand extends AbstractCommand<DeleteBusinessServiceEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * Id of Business Service to be deleted
     */
    private Long id;

    private DeleteBusinessServiceCommand() {
        super(new DeleteBusinessServiceEvent());
    }

    private DeleteBusinessServiceCommand(final String jwt, final Long id) {
        this.id = id;
        setJwt(jwt);
    }

    public static DeleteBusinessServiceCommand crete(final String jwt, final Long id) {
        return new DeleteBusinessServiceCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
