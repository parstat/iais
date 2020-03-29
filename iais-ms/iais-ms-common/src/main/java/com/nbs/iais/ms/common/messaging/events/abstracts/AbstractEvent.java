package com.nbs.iais.ms.common.messaging.events.abstracts;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.messaging.events.Event;

public abstract class AbstractEvent<D extends DTO> implements Event<D> {

    private D data;
    private Throwable exception;

    protected AbstractEvent() {
        super();
    }

    @Override
    public D getData() {
        return this.data;
    }

    @Override
    public void setData(final D data) {
        this.data = data;
    }

    @Override
    public Throwable getException() {
        return this.exception;
    }

    @Override
    public void setException(final Throwable exception) {
        this.exception = exception;
    }
}
