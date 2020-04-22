package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;
import com.nbs.iais.ms.common.enums.ProcessOutputType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessOutputSpecificationDTO extends IdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    List<ProcessOutputType> processOutputTypes;

    public ProcessOutputSpecificationDTO() {
        super();
    }

    public ProcessOutputSpecificationDTO(final Long id) {
        super(id);
    }

    public List<ProcessOutputType> getProcessOutputTypes() {
        return processOutputTypes;
    }

    public void setProcessOutputTypes(final List<ProcessOutputType> processOutputTypes) {
        this.processOutputTypes = processOutputTypes;
    }
}
