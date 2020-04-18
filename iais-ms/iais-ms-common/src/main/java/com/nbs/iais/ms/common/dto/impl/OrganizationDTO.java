package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.DivisionMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DTOList<DivisionMiniDTO> divisions;

    public OrganizationDTO() {
        super();
    }

    public OrganizationDTO(final Long id) {
        super(id);
    }

    public DTOList<DivisionMiniDTO> getDivisions() {
        return divisions;
    }

    public void setDivisions(final DTOList<DivisionMiniDTO> divisions) {
        this.divisions = divisions;
    }
}
