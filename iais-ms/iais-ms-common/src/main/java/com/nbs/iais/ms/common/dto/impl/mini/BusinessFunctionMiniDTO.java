package com.nbs.iais.ms.common.dto.impl.mini;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableEntityDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessFunctionMiniDTO extends LinkableEntityDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    //GSBPM sub-phase name
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String name;

    //GSBPM sub-phase id
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String localId;

    public BusinessFunctionMiniDTO() {
        super();
    }

    public BusinessFunctionMiniDTO(final Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }
}
