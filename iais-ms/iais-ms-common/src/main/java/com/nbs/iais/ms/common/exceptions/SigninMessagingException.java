package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

import java.util.UUID;

public class SigninMessagingException extends AbstractMessagingIaisException {

    private static final long serialVersionUID = 200L;

    public SigninMessagingException(final UUID correlationId) {
        super(correlationId);
    }

    public SigninMessagingException(final ExceptionCodes type, final UUID correlationId) {
        super(type, correlationId);
    }

    public SigninMessagingException(final String message, final ExceptionCodes type, final UUID correlationId) {
        super(message, type, correlationId);
    }
}
