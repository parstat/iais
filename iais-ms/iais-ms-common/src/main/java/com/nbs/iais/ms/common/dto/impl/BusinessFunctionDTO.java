package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessFunctionDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private String phase;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private int phaseId;

    public BusinessFunctionDTO() {
        super();
    }

    public BusinessFunctionDTO(final Long id) {
        super(id);
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(final String phase) {
        this.phase = phase;
    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(final int phaseId) {
        this.phaseId = phaseId;
    }
}
