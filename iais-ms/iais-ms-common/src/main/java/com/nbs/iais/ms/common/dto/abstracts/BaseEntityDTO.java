package com.nbs.iais.ms.common.dto.abstracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.dto.Views;

public abstract class BaseEntityDTO implements DTO {

    @JsonProperty
    @JsonView(Views.Basic.class)
    private Long id;

    public BaseEntityDTO() {
        super();
    }

    public BaseEntityDTO(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
