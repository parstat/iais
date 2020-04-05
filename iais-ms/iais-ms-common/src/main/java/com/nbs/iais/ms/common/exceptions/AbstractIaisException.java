package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public abstract class AbstractIaisException extends RuntimeException implements IaisException {

    private static final long serialVersionUID = 200L;

    private ExceptionCodes type = ExceptionCodes.SYSTEM_ERROR;

    protected AbstractIaisException() {
        super();
    }

    protected AbstractIaisException(final String subject) {
        super(subject);
    }

    protected AbstractIaisException(final ExceptionCodes type) {
        this.type = type;
    }

    protected AbstractIaisException(final String subject, final ExceptionCodes type) {
        super(subject);
        this.type = type;
    }

    protected AbstractIaisException(final String text, final Exception ex) {
        super(text, ex);
    }

    protected AbstractIaisException(final String text, final Exception ex, final ExceptionCodes type) {
        super(text, ex);
        this.type = type;
    }

    private AbstractIaisException(final Throwable throwable) {
        super(throwable);
    }

    @Override
    public ExceptionCodes getType() {
        return type;
    }

    @Override
    public void setType(ExceptionCodes type) {
        this.type = type;
    }
}
