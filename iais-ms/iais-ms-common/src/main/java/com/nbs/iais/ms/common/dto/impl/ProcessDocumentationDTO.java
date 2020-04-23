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
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
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

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DTOList<ProcessMethodDTO> processMethods;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DTOList<ProcessInputSpecificationDTO> processInputSpecifications;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DTOList<ProcessOutputSpecificationDTO> processOutputSpecifications;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DTOList<BusinessServiceDTO> businessServices;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DTOList<ProcessQualityDTO> processQualityList;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private String nextSubPhase;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private DTOList<ProcessDocumentDTO> documents;

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

    public DTOList<ProcessMethodDTO> getProcessMethods() {
        return processMethods;
    }

    public void setProcessMethods(final DTOList<ProcessMethodDTO> processMethods) {
        this.processMethods = processMethods;
    }

    public DTOList<ProcessInputSpecificationDTO> getProcessInputSpecifications() {
        return processInputSpecifications;
    }

    public void setProcessInputSpecifications(final DTOList<ProcessInputSpecificationDTO> processInputSpecifications) {
        this.processInputSpecifications = processInputSpecifications;
    }

    public DTOList<ProcessOutputSpecificationDTO> getProcessOutputSpecifications() {
        return processOutputSpecifications;
    }

    public void setProcessOutputSpecifications(final DTOList<ProcessOutputSpecificationDTO> processOutputSpecifications) {
        this.processOutputSpecifications = processOutputSpecifications;
    }

    public DTOList<BusinessServiceDTO> getBusinessServices() {
        return businessServices;
    }

    public void setBusinessServices(final DTOList<BusinessServiceDTO> businessServices) {
        this.businessServices = businessServices;
    }

    public DTOList<ProcessQualityDTO> getProcessQualityList() {
        return processQualityList;
    }

    public void setProcessQualityList(final DTOList<ProcessQualityDTO> processQualityList) {
        this.processQualityList = processQualityList;
    }

    public String getNextSubPhase() {
        return nextSubPhase;
    }

    public void setNextSubPhase(final String nextSubPhase) {
        this.nextSubPhase = nextSubPhase;
    }

    public DTOList<ProcessDocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(DTOList<ProcessDocumentDTO> documents) {
        this.documents = documents;
    }
}
