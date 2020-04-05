package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class RefentialServiceException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public RefentialServiceException() {
        super();
    }

    public RefentialServiceException(final ExceptionCodes type) {
        super(type);
    }

    public RefentialServiceException(final String message, final ExceptionCodes type) {
        super(message, type);
    }
}
