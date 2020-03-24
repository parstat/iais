package com.nbs.iais.ms.common.dto.abstracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.dto.Views;

import java.util.UUID;


public abstract class BaseEntityDTO implements DTO {

    @JsonProperty
    @JsonView(Views.Basic.class)
    private UUID id;

    public BaseEntityDTO() {
        super();
    }

    public BaseEntityDTO(final UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
}
