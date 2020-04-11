package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEventTuple;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BusinessProcess")
@Table(name = "business_process")
public class BusinessProcessEntity extends AbstractIdentifiableArtefact implements BusinessProcess {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @Column(name = "date_initiated")
    private LocalDateTime dateInitiated;

    @Column(name = "date_ended")
    private LocalDateTime dateEnded;

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
    private AdministrativeDetails administrativeDetails;

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    @JoinTable(name = "bp_agent_in_role",
            joinColumns = @JoinColumn(name = "bp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
    private List<AgentInRole> administrators = new ArrayList<>();

    @OneToMany(targetEntity = ProcessStepEntity.class, orphanRemoval = true, mappedBy = "businessProcess")
    private List<ProcessStep> processSteps = new ArrayList<>();

    @ManyToOne(targetEntity = ChangeEventEntity.class)
    @JoinColumn(name = "change_event_id", referencedColumnName = "id")
    private ChangeEvent changeEvent;

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

    public String getDescription(final Language language) {
        return description().getText(language.getShortName());
    }

    public void setDescription(final String description, final Language language) {
        description().addText(language.getShortName(), description);
    }

    @Override
    public LocalDateTime getDateInitiated() {
        return dateInitiated;
    }

    @Override
    public void setDateInitiated(final LocalDateTime dateInitiated) {
        this.dateInitiated = dateInitiated;
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
    public List<ProcessStep> getProcessSteps() {
        return processSteps;
    }

    @Override
    public void setProcessSteps(final List<ProcessStep> processSteps) {
        this.processSteps = processSteps;
    }

    @Override
    public List<StatisticalProgramCycle> getStatisticalProgramCycles() {
        return null;
    }

    @Override
    public void setStatisticalProgramCycles(List<StatisticalProgramCycle> statisticalProgramCycles) {

    }

    @Override
    public List<BusinessFunction> getPerforms() {
        return null;
    }

    @Override
    public void setPerforms(List<BusinessFunction> performs) {

    }

    @Override
    public List<BusinessService> getBusinessServices() {
        return null;
    }

    @Override
    public void setBusinessServices(List<BusinessService> businessServices) {

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
    public ChangeEvent getChangeEvent() {
        return changeEvent;
    }

    @Override
    public void setChangeEvent(final ChangeEvent changeEvent) {
        this.changeEvent = changeEvent;
    }

    @Override
    public List<AgentInRole> getAdministrators() {
        return administrators;
    }

    @Override
    public void setAdministrators(final List<AgentInRole> administrators) {
        this.administrators = administrators;
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
