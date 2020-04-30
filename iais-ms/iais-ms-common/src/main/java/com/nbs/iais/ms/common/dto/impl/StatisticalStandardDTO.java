package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticalStandardDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private AdministativeDetailsDTO administativeDetails;
    
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private StatisticalStandardType type;
 

    public StatisticalStandardType getType() {
		return type;
	}

	public void setType(StatisticalStandardType type) {
		this.type = type;
	}

	public AdministativeDetailsDTO getAdministativeDetails() {
		return administativeDetails;
	}

	public void setAdministativeDetails(AdministativeDetailsDTO administativeDetails) {
		this.administativeDetails = administativeDetails;
	}

	public StatisticalStandardDTO() {
        super();
    }

    public StatisticalStandardDTO(final Long id) {
        super(id);
    }

  
}
