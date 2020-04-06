package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class AccountNotFoundException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(final ExceptionCodes type) {
        super(type);
    }

    public AccountNotFoundException(final String message, final ExceptionCodes type) {
        super(message, type);
    }
}
