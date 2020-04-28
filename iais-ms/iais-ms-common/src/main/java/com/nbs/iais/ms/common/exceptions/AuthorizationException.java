package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class AuthorizationException extends AbstractIaisException{

    private static final long serialVersionUID = 200L;

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(final ExceptionCodes type) {
        super(type);
    }


    public AuthorizationException(final String message, final ExceptionCodes type) {
        super(message, type);
    }

}
