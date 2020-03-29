package com.nbs.iais.ms.common.messaging.commands;

import com.nbs.iais.ms.common.RequestMessage;
import com.nbs.iais.ms.common.messaging.events.Event;

public interface Command<E extends Event<?>> extends RequestMessage<E> {

    E getEvent();

}
