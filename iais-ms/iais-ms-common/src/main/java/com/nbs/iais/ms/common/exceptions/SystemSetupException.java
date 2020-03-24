package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

public class SystemSetupException extends AbstractIaisException {

    private static final long serialVersionUID = 200L;

    public SystemSetupException() {
        super();
    }

    public SystemSetupException(final String subject) {
        super(subject, ExceptionCodes.SYSTEM_ERROR);
    }

    public SystemSetupException(final String subject, final Exception e) {
        super(subject, e, ExceptionCodes.SYSTEM_ERROR);
    }

}

