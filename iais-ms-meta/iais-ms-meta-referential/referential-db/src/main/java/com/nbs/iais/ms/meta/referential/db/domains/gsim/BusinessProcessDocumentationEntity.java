package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessProcess;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgramCycle;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.*;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;
import java.util.List;

@Entity(name = "BusinessProcessDocumentation")
@Table(name = "business_process_documentation")
public class BusinessProcessDocumentationEntity extends AbstractDomainObject implements BusinessProcessDocumentation {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_note")
    private MultilingualText notes;

    @ManyToOne(targetEntity = StatisticalProgramCycleEntity.class)
    @JoinColumn(name = "statistical_program_cycle_id", referencedColumnName = "id")
    private StatisticalProgramCycle statisticalProgramCycle;

    @ManyToOne(targetEntity = BusinessProcessEntity.class)
    @JoinColumn(name = "business_process_id", referencedColumnName = "id")
    private BusinessProcess businessProcess;

    @ManyToOne(targetEntity = AgentEntity.class)
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private Agent division;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private Frequency frequency;

    @ManyToOne(targetEntity = BusinessProcessEntity.class)
    @JoinColumn(name = "next_business_process_id", referencedColumnName = "id")
    private BusinessProcess nextProcess;

    @ManyToMany(targetEntity = SoftwareEntity.class)
    @JoinTable(name = "software_used",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "software_id", referencedColumnName = "id"))
    private List<Software> softwareUsed;

    @ManyToMany(targetEntity = StatisticalStandardReferenceEntity.class)
    @JoinTable(name = "standards_used",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "standard_id", referencedColumnName = "id"))
    private List<StatisticalStandardReference> standardUsed;

    @ManyToMany(targetEntity = StatisticalMethodEntity.class)
    @JoinTable(name = "methods_used",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "method_id", referencedColumnName = "id"))
    private List<StatisticalMethod> methodsUsed;

    @ManyToMany(targetEntity = QualityIndicatorEntity.class)
    @JoinTable(name = "quality_indicators_used",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quality_indicator_id", referencedColumnName = "id"))
    private List<QualityIndicator> qualityIndicatorsUsed;

    @ManyToMany(targetEntity = DocumentEntity.class)
    @JoinTable(name = "additional_documents",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"))
    private List<Document> additionalDocuments;

    @ManyToMany(targetEntity = InputDocumentationEntity.class)
    @JoinTable(name = "inputs_used",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "input_id", referencedColumnName = "id"))
    private List<InputDocumentation> inputs;

    @ManyToMany(targetEntity = OutputDocumentationEntity.class)
    @JoinTable(name = "output_produced",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "output_id", referencedColumnName = "id"))
    private List<OutputDocumentation> outputs;

    public void setName(final String name, final Language language) {
        name().addText(language.getShortName(), name);
    }

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

    private MultilingualText notes() {
        if(getNotes() == null) {
            setNotes(new MultiLanguageTextEntity());
        }
        return getNotes();
    }

    public String getDescription(final Language language) {
        return description().getText(language.getShortName());
    }

    public void setDescription(final String description, final Language language) {
        description().addText(language.getShortName(), description);
    }

    public String getNotes(final Language language) {
        return notes().getText(language.getShortName());
    }

    public void setNotes(final String notes, final Language language) {
        notes().addText(language.getShortName(), notes);
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
    public MultilingualText getNotes() {
        return notes;
    }

    @Override
    public void setNotes(final MultilingualText notes) {
        this.notes = notes;
    }

    @Override
    public StatisticalProgramCycle getStatisticalProgramCycle() {
        return statisticalProgramCycle;
    }

    @Override
    public void setStatisticalProgramCycle(final StatisticalProgramCycle statisticalProgramCycle) {
        this.statisticalProgramCycle = statisticalProgramCycle;
    }

    @Override
    public BusinessProcess getBusinessProcess() {
        return businessProcess;
    }

    @Override
    public void setBusinessProcesses(final BusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
    }

    @Override
    public Agent getDivision() {
        return division;
    }

    @Override
    public void setDivision(final Agent division) {
        this.division = division;
    }

    @Override
    public Frequency getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(final Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public BusinessProcess getNextProcess() {
        return nextProcess;
    }

    @Override
    public void setNextProcess(final BusinessProcess nextProcess) {
        this.nextProcess = nextProcess;
    }

    @Override
    public List<StatisticalStandardReference> getStandards() {
        return standardUsed;
    }

    @Override
    public void setStandards(final List<StatisticalStandardReference> standardUsed) {
        this.standardUsed = standardUsed;
    }

    @Override
    public List<Software> getSoftwareUsed() {
        return softwareUsed;
    }

    @Override
    public void setSoftwareUsed(final List<Software> softwareUsed) {
        this.softwareUsed = softwareUsed;
    }

    @Override
    public List<StatisticalMethod> getMethodsUsed() {
        return methodsUsed;
    }

    @Override
    public void setMethodsUsed(final List<StatisticalMethod> methodsUsed) {
        this.methodsUsed = methodsUsed;
    }

    @Override
    public List<QualityIndicator> getQualityIndicatorsUsed() {
        return qualityIndicatorsUsed;
    }

    @Override
    public void setQualityIndicatorsUsed(final List<QualityIndicator> qualityIndicatorsUsed) {
        this.qualityIndicatorsUsed = qualityIndicatorsUsed;
    }

    @Override
    public List<Document> getAdditionalDocuments() {
        return additionalDocuments;
    }

    @Override
    public void setAdditionalDocuments(final List<Document> additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }

    @Override
    public List<InputDocumentation> getInputs() {
        return inputs;
    }

    @Override
    public void setInputs(final List<InputDocumentation> inputs) {
        this.inputs = inputs;
    }

    @Override
    public List<OutputDocumentation> getOutputs() {
        return outputs;
    }

    @Override
    public void setOutputs(final List<OutputDocumentation> outputs) {
        this.outputs = outputs;
    }

}
