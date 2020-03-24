package com.nbs.iais.ms.common.exceptions;

import java.util.UUID;

public interface IaisMessagingException extends IaisException {

    UUID getCorrelationId();

    void setCorrelationId(UUID correlationId);

    String getMessage();

}
