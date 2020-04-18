package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.IndividualMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.OrganizationMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DivisionDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private OrganizationMiniDTO organization;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DTOList<IndividualMiniDTO> employees;

    public DivisionDTO() {
        super();
    }

    public DivisionDTO(final Long id) {
        super(id);
    }

    public OrganizationMiniDTO getOrganization() {
        return organization;
    }

    public void setOrganization(final OrganizationMiniDTO organization) {
        this.organization = organization;
    }

    public DTOList<IndividualMiniDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(final DTOList<IndividualMiniDTO> employees) {
        this.employees = employees;
    }
}
