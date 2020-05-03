package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.ProcessDocumentationMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticalProgramDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String acronym;

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

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private AgentMiniDTO owner;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private AgentMiniDTO contact;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private AgentMiniDTO maintainer;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DTOList<StatisticalStandardDTO> statisticalStandards;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DTOList<LegislativeReferenceDTO> legislativeReferences;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DTOList<ProcessDocumentationMiniDTO> processDocumentations;

    public StatisticalProgramDTO() {
        super();
    }

    public StatisticalProgramDTO(final Long id) {
        super(id);
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

    public AgentMiniDTO getOwner() {
        return owner;
    }

    public void setOwner(final AgentMiniDTO owner) {
        this.owner = owner;
    }

    public AgentMiniDTO getContact() {
        return contact;
    }

    public void setContact(final AgentMiniDTO contact) {
        this.contact = contact;
    }

    public AgentMiniDTO getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(final AgentMiniDTO maintainer) {
        this.maintainer = maintainer;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(final String acronym) {
        this.acronym = acronym;
    }

    public DTOList<StatisticalStandardDTO> getStatisticalStandards() {
        return statisticalStandards;
    }

    public void setStatisticalStandards(final DTOList<StatisticalStandardDTO> statisticalStandards) {
        this.statisticalStandards = statisticalStandards;
    }

    public DTOList<LegislativeReferenceDTO> getLegislativeReferences() {
        return legislativeReferences;
    }

    public void setLegislativeReferences(DTOList<LegislativeReferenceDTO> legislativeReferences) {
        this.legislativeReferences = legislativeReferences;
    }

    public DTOList<ProcessDocumentationMiniDTO> getProcessDocumentations() {
        return processDocumentations;
    }

    public void setProcessDocumentations(final DTOList<ProcessDocumentationMiniDTO> processDocumentations) {
        this.processDocumentations = processDocumentations;
    }
}
