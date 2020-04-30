package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.ProcessDocumentationMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegislativeReferenceDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private int number;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private LocalDateTime approval;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private LegislativeType type;


    public LegislativeReferenceDTO() {
        super();
    }

    public LegislativeReferenceDTO(final Long id) {
        super(id);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
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
}
