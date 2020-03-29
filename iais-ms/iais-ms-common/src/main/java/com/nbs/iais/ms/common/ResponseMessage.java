package com.nbs.iais.ms.common;

import com.nbs.iais.ms.common.dto.DTO;

public interface ResponseMessage<D extends DTO> extends Message {

    /**
     * @return the response DTO object
     */
    D getData();

    void setData(D data);

    /**
     * @return the exception
     */
    Throwable getException();

    void setException(Throwable exception);
}
