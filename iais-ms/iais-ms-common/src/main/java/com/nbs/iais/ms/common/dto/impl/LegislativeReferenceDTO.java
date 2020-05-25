package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.enums.LegislativeType;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegislativeReferenceDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private LocalDateTime approval;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private LegislativeType type;
    
    
    @JsonProperty
    @JsonView(Views.Extended.class)
    private String externalLink;


    public LegislativeReferenceDTO() {
        super();
    }

    public LegislativeReferenceDTO(final Long id) {
        super(id);
    }

    public LocalDateTime getApproval() {
        return approval;
    }

    public void setApproval(final LocalDateTime approval) {
        this.approval = approval;
    }

    public LegislativeType getType() {
        return type;
    }

    public void setType(final LegislativeType type) {
        this.type = type;
    }

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	 
}
