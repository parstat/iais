package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.BusinessFunctionMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.DivisionMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.IndividualMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.StatisticalProgramMiniDTO;
import com.nbs.iais.ms.common.enums.Frequency;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessDocumentationDTO extends IdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private BusinessFunctionMiniDTO businessFunction;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private StatisticalProgramMiniDTO statisticalProgram;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DivisionMiniDTO owner;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private Frequency frequency;

    @JsonProperty
    @JsonView(Views.Secure.class)
    private IndividualMiniDTO creator;

    public ProcessDocumentationDTO() {
        super();
    }

    public ProcessDocumentationDTO(final Long id) {
        super(id);
    }

    public BusinessFunctionMiniDTO getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(final BusinessFunctionMiniDTO businessFunction) {
        this.businessFunction = businessFunction;
    }

    public StatisticalProgramMiniDTO getStatisticalProgram() {
        return statisticalProgram;
    }

    public void setStatisticalProgram(final StatisticalProgramMiniDTO statisticalProgram) {
        this.statisticalProgram = statisticalProgram;
    }

    public DivisionMiniDTO getOwner() {
        return owner;
    }

    public void setOwner(final DivisionMiniDTO owner) {
        this.owner = owner;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(final Frequency frequency) {
        this.frequency = frequency;
    }

    public IndividualMiniDTO getCreator() {
        return creator;
    }

    public void setCreator(IndividualMiniDTO creator) {
        this.creator = creator;
    }
}
