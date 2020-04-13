package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocument;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessQualityIndicator;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ProcessDesign")
@Table(name = "process_design")
public class ProcessDesignEntity extends AbstractIdentifiableArtefact implements ProcessDesign {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @ManyToMany(targetEntity = ProcessMethodEntity.class)
    @JoinTable(name = "process_design_methods",
            joinColumns = @JoinColumn(name = "process_design_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "method_id", referencedColumnName = "id" ))
    private List<ProcessMethod> processMethods;

    @ManyToMany(targetEntity = ProcessQualityIndicatorEntity.class)
    @JoinTable(name = "process_design_quality_indicators",
            joinColumns = @JoinColumn(name = "process_design_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quality_indicator_id", referencedColumnName = "id" ))
    private List<ProcessQualityIndicator> processQualityIndicators;

    @OneToMany(targetEntity = ProcessInputSpecificationsEntity.class, mappedBy = "processDesign")
    private List<ProcessInputSpecifications> processInputSpecifications;

    @OneToMany(targetEntity = ProcessOutputSpecificationEntity.class, mappedBy = "processDesign")
    private List<ProcessOutputSpecification> processOutputSpecifications;

    @OneToMany(targetEntity = ProcessDocumentEntity.class, mappedBy = "processDesign")
    private List<ProcessDocument> processDocuments;

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
    @JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
    private AdministrativeDetails administrativeDetails;

    @ManyToOne(targetEntity = BusinessFunctionEntity.class)
    @JoinColumn(name = "business_function_id", referencedColumnName = "id")
    private BusinessFunction businessFunction;

    @ManyToOne(targetEntity = BusinessFunctionEntity.class)
    @JoinColumn(name = "next_business_function_id", referencedColumnName = "id")
    private BusinessFunction nextBusinessFunction;

    @ManyToOne(targetEntity = StatisticalProgramDesignEntity.class)
    @JoinColumn(name = "statistical_program_design_id", referencedColumnName = "id")
    private StatisticalProgramDesign statisticalProgramDesign;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private Frequency frequency;

    public ProcessDesignEntity() {
        super();
    }

    @Override
    public void setName(final String name, final Language language) {
        name().addText(language.getShortName(), name);
    }

    @Override
    public String getName(final Language language) {
        return name().getText(language.getShortName());
    }

    private MultilingualText name() {
        if(getName() == null) {
            setName(new MultiLanguageTextEntity());
        }
        return getName();
    }

    private MultilingualText description() {
        if(getDescription() == null) {
            setDescription(new MultiLanguageTextEntity());
        }
        return getDescription();
    }

    @Override
    public String getDescription(final Language language) {
        return description().getText(language.getShortName());
    }

    @Override
    public void setDescription(final String description, final Language language) {
        description().addText(language.getShortName(), description);
    }

    @Override
    public MultilingualText getName() {
        return name;
    }

    @Override
    public void setName(final MultilingualText name) {
        this.name = name;
    }

    @Override
    public MultilingualText getDescription() {
        return description;
    }

    @Override
    public void setDescription(final MultilingualText description) {
        this.description = description;
    }

   @Override
    public AdministrativeDetails getAdministrativeDetails() {
        return administrativeDetails;
    }

    @Override
    public void setAdministrativeDetails(final AdministrativeDetails administrativeDetails) {
        this.administrativeDetails = administrativeDetails;
    }

    @Override
    public List<BusinessService> getBusinessServices() {
        return null;
    }

    @Override
    public void setBusinessServices(final List<BusinessService> businessServices) {

    }

    @Override
    public List<ProcessInputSpecifications> getProcessInputs() {
        return processInputSpecifications;
    }

    @Override
    public void setProcessInputs(final List<ProcessInputSpecifications> processInputs) {
        this.processInputSpecifications = processInputs;
    }

    @Override
    public List<ProcessOutputSpecification> getProcessOutputs() {
        return processOutputSpecifications;
    }

    @Override
    public void setProcessOutputs(final List<ProcessOutputSpecification> processOutputs) {
        this.processOutputSpecifications = processOutputs;
    }

    @Override
    public List<ProcessMethod> getProcessMethods() {
        return processMethods;
    }

    @Override
    public void setProcessMethods(final List<ProcessMethod> processMethods) {
        this.processMethods = processMethods;
    }

    @Override
    public List<ProcessDocument> getProcessDocuments() {
        return processDocuments;
    }

    @Override
    public void setProcessDocuments(final List<ProcessDocument> processDocuments) {
        this.processDocuments = processDocuments;
    }

    @Override
    public List<ProcessQualityIndicator> getProcessQualityIndicators() {
        return processQualityIndicators;
    }

    @Override
    public void setProcessQualityIndicators(final List<ProcessQualityIndicator> processQualityIndicators) {
        this.processQualityIndicators = processQualityIndicators;
    }

    @Override
    public BusinessFunction getBusinessFunction() {
        return businessFunction;
    }

    @Override
    public void setBusinessFunction(final BusinessFunction businessFunctions) {
        this.businessFunction = businessFunctions;
    }

    @Override
    public BusinessFunction getNextBusinessFunction() {
        return nextBusinessFunction;
    }

    @Override
    public void setNextBusinessFunction(final BusinessFunction businessFunction) {
        this.nextBusinessFunction = businessFunction;
    }

    @Override
    public StatisticalProgramDesign getStatisticalProgramDesign() {
        return statisticalProgramDesign;
    }

    @Override
    public void setStatisticalProgramDesign(final StatisticalProgramDesign statisticalProgramDesign) {
        this.statisticalProgramDesign = statisticalProgramDesign;
    }

    @Override
    public Frequency getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(final Frequency frequency) {
        this.frequency = frequency;
    }

}
