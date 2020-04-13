package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableEntityDTO;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticalProgramDTO extends LinkableEntityDTO {

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String name;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private String description;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private ProgramStatus programStatus;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private double budget;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private String sourceOfFunding;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private LocalDateTime dateInitiated;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private LocalDateTime dateEnded;

    public StatisticalProgramDTO() {
        super();
    }

    public StatisticalProgramDTO(final Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProgramStatus getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(ProgramStatus programStatus) {
        this.programStatus = programStatus;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getSourceOfFunding() {
        return sourceOfFunding;
    }

    public void setSourceOfFunding(String sourceOfFunding) {
        this.sourceOfFunding = sourceOfFunding;
    }

    public LocalDateTime getDateInitiated() {
        return dateInitiated;
    }

    public void setDateInitiated(LocalDateTime dateInitiated) {
        this.dateInitiated = dateInitiated;
    }

    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(LocalDateTime dateEnded) {
        this.dateEnded = dateEnded;
    }
}
