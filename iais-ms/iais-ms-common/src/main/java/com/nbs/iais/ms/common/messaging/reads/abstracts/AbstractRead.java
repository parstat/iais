package com.nbs.iais.ms.common.messaging.reads.abstracts;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.messaging.reads.Read;

public abstract class AbstractRead<D extends DTO> implements Read<D> {

    private D data;
    private Throwable exception;

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
