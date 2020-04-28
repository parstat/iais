package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class EntityException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public EntityException() {
        super();
    }

    public EntityException(final ExceptionCodes type) {
        super(type);
    }

    public EntityException(final String message, final ExceptionCodes type) {

    }
}
