package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessServiceDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private List<String> interfaces;

    public BusinessServiceDTO() {
        super();
    }

    public BusinessServiceDTO(Long id) {
        super(id);
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(final List<String> interfaces) {
        this.interfaces = interfaces;
    }
}
