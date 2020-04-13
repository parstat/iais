package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "StatisticalProgramDesign")
@Table(name = "statistical_program_design")
public class StatisticalProgramDesignEntity extends AbstractIdentifiableArtefact implements StatisticalProgramDesign {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @OneToOne(targetEntity = AdministrativeDetailsEntity.class, orphanRemoval = true)
    @JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
    private AdministrativeDetails administrativeDetails;

    @ManyToMany(targetEntity = StatisticalStandardReferenceEntity.class)
    @JoinTable(name = "conceptual_framework_used",
            joinColumns = @JoinColumn(name = "documentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "conceptual_framework_id", referencedColumnName = "id"))
    private List<StatisticalStandardReference> conceptualFrameworkUsed;

    @Column(name = "date_initiated")
    private LocalDateTime dateInitiated;

    @Column(name = "date_ended")
    private LocalDateTime dateEnded;

    @OneToMany(targetEntity = StatisticalProgramEntity.class, mappedBy = "statisticalProgramDesign")
    private List<StatisticalProgram> statisticalPrograms;

    @OneToMany(targetEntity = ProcessDesignEntity.class, mappedBy = "statisticalProgramDesign")
    private List<ProcessDesign> processDesigns;

    @Enumerated(EnumType.STRING)
    @Column(name = "design_status")
    private ProgramStatus programStatus;

    public StatisticalProgramDesignEntity() {
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
    public List<StatisticalStandardReference> getConceptualFrameworks() {
        return conceptualFrameworkUsed;
    }

    @Override
    public void setConceptualFrameworks(final List<StatisticalStandardReference> conceptualFrameworks) {
        this.conceptualFrameworkUsed = conceptualFrameworks;
    }

    @Override
    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    @Override
    public void setDateEnded(final LocalDateTime dateEnded) {
        this.dateEnded = dateEnded;
    }

    @Override
    public LocalDateTime getDateInitiated() {
        return dateInitiated;
    }

    @Override
    public void setDateInitiated(final LocalDateTime dateStarted) {
        this.dateInitiated = dateStarted;
    }

    @Override
    public ProgramStatus getProgramDesignStatus() {
        return programStatus;
    }

    @Override
    public void setProgramDesignStatus(final ProgramStatus programDesignStatus) {
        this.programStatus = programDesignStatus;
    }

    @Override
    public List<StatisticalProgram> getStatisticalPrograms() {
        return statisticalPrograms;
    }

    @Override
    public void setStatisticalPrograms(final List<StatisticalProgram> statisticalPrograms) {
        this.statisticalPrograms = statisticalPrograms;
    }

    @Override
    public List<ProcessDesign> getProcessDesigns() {
        return processDesigns;
    }

    @Override
    public void setProcessDesigns(final List<ProcessDesign> processDesigns) {
        this.processDesigns = processDesigns;
    }
}
