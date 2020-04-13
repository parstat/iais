package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessProcess;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgramCycle;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "StatisticalProgramCycle")
@Table(name = "statistical_program_cycle")
public class StatisticalProgramCycleEntity extends AbstractIdentifiableArtefact implements StatisticalProgramCycle {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @Column(name = "reference_period_start")
    private LocalDateTime referencePeriodStart;

    @Column(name = "reference_period_end")
    private LocalDateTime referencePeriodEnd;

    @OneToOne(targetEntity = AdministrativeDetailsEntity.class)
    @JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
    private AdministrativeDetails administrativeDetails;

    @ManyToOne(targetEntity = StatisticalProgramEntity.class)
    @JoinColumn(name = "statistical_program_id", referencedColumnName = "id")
    private StatisticalProgram statisticalProgram;

    public StatisticalProgramCycleEntity() {
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
    public LocalDateTime getReferencePeriodStart() {
        return referencePeriodStart;
    }

    @Override
    public void setReferencePeriodStart(final LocalDateTime referencePeriodStart) {
        this.referencePeriodStart = referencePeriodStart;
    }

    @Override
    public LocalDateTime getReferencePeriodEnd() {
        return referencePeriodEnd;
    }

    @Override
    public void setReferencePeriodEnd(final LocalDateTime referencePeriodEnd) {
        this.referencePeriodEnd = referencePeriodEnd;
    }

    @Override
    public List<BusinessProcess> getBusinessProcesses() {
        return null;
    }

    @Override
    public void setBusinessProcesses(List<BusinessProcess> businessProcesses) {

    }

    @Override
    public StatisticalProgram getStatisticalProgram() {
        return statisticalProgram;
    }

    @Override
    public void setStatisticalProgram(final StatisticalProgram statisticalProgram) {
        this.statisticalProgram = statisticalProgram;
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
}
