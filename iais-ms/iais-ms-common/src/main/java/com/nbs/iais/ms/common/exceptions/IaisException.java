package com.nbs.iais.ms.common.exceptions;

import com.nbs.iais.ms.common.enums.ExceptionCodes;

import java.io.Serializable;

public interface IaisException extends Serializable {

    ExceptionCodes getType();

    void setType(ExceptionCodes type);

}
