package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

import java.util.UUID;

public abstract class AbstractMessagingIaisException extends RuntimeException implements IaisMessagingException {

    private static final long serialVersionUID = 200L;

    private ExceptionCodes type = ExceptionCodes.SYSTEM_ERROR;

    private UUID correlationId;

    protected AbstractMessagingIaisException(final UUID correlationId) {
        super();
        this.correlationId = correlationId;
    }

    protected AbstractMessagingIaisException(final String subject, final UUID correlationId) {
        super(subject);
        this.correlationId = correlationId;
    }

    protected AbstractMessagingIaisException(final ExceptionCodes type, final UUID correlationId) {
        this.type = type;
        this.correlationId = correlationId;
    }

    protected AbstractMessagingIaisException(final String subject, final ExceptionCodes type, final UUID correlationId) {
        super(subject);
        this.type = type;
        this.correlationId = correlationId;
    }

    protected AbstractMessagingIaisException(final String text, final Exception ex, final UUID correlationId) {
        super(text, ex);
        this.correlationId = correlationId;
    }

    protected AbstractMessagingIaisException(final String text, final Exception ex, final ExceptionCodes type, final UUID correlationId) {
        super(text, ex);
        this.type = type;
        this.correlationId = correlationId;
    }

    private AbstractMessagingIaisException(final Throwable throwable, final UUID correlationId) {
        super(throwable);
        this.correlationId = correlationId;
    }

    @Override
    public ExceptionCodes getType() {
        return type;
    }

    @Override
    public void setType(final ExceptionCodes type) {
        this.type = type;
    }

    @Override
    public UUID getCorrelationId() {
        return correlationId;
    }

    @Override
    public void setCorrelationId(final UUID correlationId) {
        this.correlationId = correlationId;
    }
}
