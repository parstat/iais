package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableEntityDTO;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.DivisionMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.IndividualMiniDTO;
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
    @JsonView(Views.Extended.class)
    private DivisionMiniDTO owner;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private IndividualMiniDTO contact;

    @JsonProperty
    @JsonView(Views.Secure.class)
    private IndividualMiniDTO creator;

    @JsonProperty
    @JsonView(Views.Secure.class)
    private DTOList<ProcessDocumentationMiniDTO> processDocumentationMinis;

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

    public DivisionMiniDTO getOwner() {
        return owner;
    }

    public void setOwner(final DivisionMiniDTO owner) {
        this.owner = owner;
    }

    public IndividualMiniDTO getContact() {
        return contact;
    }

    public void setContact(final IndividualMiniDTO contact) {
        this.contact = contact;
    }

    public IndividualMiniDTO getCreator() {
        return creator;
    }

    public void setCreator(final IndividualMiniDTO creator) {
        this.creator = creator;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(final String acronym) {
        this.acronym = acronym;
    }

    public DTOList<ProcessDocumentationMiniDTO> getProcessDocumentationMinis() {
        return processDocumentationMinis;
    }

    public void setProcessDocumentationMinis(final DTOList<ProcessDocumentationMiniDTO> processDocumentationMinis) {
        this.processDocumentationMinis = processDocumentationMinis;
    }
}
