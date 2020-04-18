package com.nbs.iais.ms.common.dto.impl.mini;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableEntityDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DivisionMiniDTO extends LinkableEntityDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String name;

    public DivisionMiniDTO() {
        super();
    }

    public DivisionMiniDTO(final Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
