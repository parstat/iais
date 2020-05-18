package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;
import com.nbs.iais.ms.common.enums.QualityType;

public class ProcessQualityDTO extends IdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private QualityType type;

    public ProcessQualityDTO() {
        super();
    }

    public ProcessQualityDTO(final Long id) {
        super(id);
    }

    public QualityType getType() {
        return type;
    }

    public void setType(final QualityType type) {
        this.type = type;
    }
}
