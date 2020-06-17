package com.nbs.iais.ms.common.dto.impl.mini;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableEntityDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Frequency;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessDocumentationMiniDTO extends LinkableEntityDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private BusinessFunctionMiniDTO businessFunction;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private StatisticalProgramMiniDTO statisticalProgram;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String localId;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String name;
    
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String description;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private Frequency frequency;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private DTOList<AgentMiniDTO> maintainers;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String nextSubPhase;

    public ProcessDocumentationMiniDTO() {
        super();
    }

    public ProcessDocumentationMiniDTO(final Long id) {
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

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(final Frequency frequency) {
        this.frequency = frequency;
    }

    public DTOList<AgentMiniDTO> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(final DTOList<AgentMiniDTO> maintainers) {
        this.maintainers = maintainers;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getNextSubPhase() {
        return nextSubPhase;
    }

    public void setNextSubPhase(final String nextSubPhase) {
        this.nextSubPhase = nextSubPhase;
    }
}
