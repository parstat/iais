package com.nbs.iais.ms.common.dto.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.dto.Views;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOBoolean implements DTO {

    public static final DTOBoolean FALSE = new DTOBoolean(Boolean.FALSE);
    public static final DTOBoolean TRUE = new DTOBoolean(Boolean.TRUE);
    public static final DTOBoolean FAIL = new DTOBoolean(Boolean.FALSE);

    private static final long serialVersionUID = 200L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private boolean result;

    public DTOBoolean() {
    }

    public DTOBoolean(final Boolean result) {
        this.result = result;
    }

    public static DTOBoolean created(final boolean value) {
        return new DTOBoolean(value);
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}