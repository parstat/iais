package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.*;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.enums.ExceptionCodes;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDTO implements Serializable {

    private static final long serialVersionUID = 200L;


    @JsonProperty
    @JsonView(Views.Minimal.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ExceptionCodes code;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;

    public ExceptionDTO() {
    }

    @JsonIgnore
    public static ExceptionDTO populate(final ExceptionCodes code) {

        final ExceptionDTO e = new ExceptionDTO();

        e.setCode(code);

        return e;
    }

    @JsonIgnore
    public static ExceptionDTO populate(final ExceptionCodes code, final String message) {

        final ExceptionDTO e = new ExceptionDTO();

        e.setCode(code);
        e.setMessage(message);

        return e;
    }

    public ExceptionCodes getCode() {
        return code;
    }

    public void setCode(ExceptionCodes code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
