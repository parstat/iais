package com.nbs.iais.ms.common.messaging.commands.abstracts;

import com.nbs.iais.ms.common.AbstractRequest;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.Command;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public abstract class AbstractCommand<E extends AbstractEvent<? extends DTO>> extends AbstractRequest<E> implements Command<E> {

    private E event;


    protected AbstractCommand() {
    }

    protected AbstractCommand(final E event) {
        this.event = event;
    }

    @Override
    public E getEvent() {
        return event;
    }


    @Override
    public E getReply() {
        return getEvent();
    }



}
