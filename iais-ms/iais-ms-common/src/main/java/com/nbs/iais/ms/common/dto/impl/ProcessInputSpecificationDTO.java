package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;
import com.nbs.iais.ms.common.enums.ProcessInputType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessInputSpecificationDTO extends IdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    List<ProcessInputType> processInputTypes;

    public ProcessInputSpecificationDTO() {
        super();
    }

    public ProcessInputSpecificationDTO(final Long id) {
        super(id);
    }

    public List<ProcessInputType> getProcessInputTypes() {
        return processInputTypes;
    }

    public void setProcessInputTypes(final List<ProcessInputType> processInputTypes) {
        this.processInputTypes = processInputTypes;
    }
}
