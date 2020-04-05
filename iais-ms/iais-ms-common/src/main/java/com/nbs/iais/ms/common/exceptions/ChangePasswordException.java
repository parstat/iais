package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class ChangePasswordException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public ChangePasswordException() {
        super();
    }

    public ChangePasswordException(final ExceptionCodes type) {
        super(type);
    }

    public ChangePasswordException(final String message, final ExceptionCodes type) {
        super(message, type);
    }
}
