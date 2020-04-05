package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class ConfirmationException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public ConfirmationException() {
        super();
    }

    public ConfirmationException(final ExceptionCodes type) {
        super(type);
    }

    public ConfirmationException(final String message, final ExceptionCodes type) {
        super(message, type);
    }

}
