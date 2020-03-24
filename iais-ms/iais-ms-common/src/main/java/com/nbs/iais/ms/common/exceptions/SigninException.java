package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class SigninException extends AbstractIaisException {

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
