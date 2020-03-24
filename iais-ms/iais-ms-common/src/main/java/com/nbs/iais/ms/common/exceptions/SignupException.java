package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

import java.util.UUID;

public class SignupException extends AbstractIaisException {


    public SignupException(final ExceptionCodes type) {
        super(type);
    }

    public SignupException(final String message, final ExceptionCodes type) {
        super(message, type);
    }
}
