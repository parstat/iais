package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class ReferentialServiceException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public ReferentialServiceException() {
        super();
    }

    public ReferentialServiceException(final ExceptionCodes type) {
        super(type);
    }

    public ReferentialServiceException(final String message, final ExceptionCodes type) {
        super(message, type);
    }
}
