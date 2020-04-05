package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class SigninException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public SigninException() {
        super();
    }

    public SigninException(final ExceptionCodes type) {
        super(type);
    }

    public SigninException(final String message, final ExceptionCodes type) {
        super(message, type);
    }
}
