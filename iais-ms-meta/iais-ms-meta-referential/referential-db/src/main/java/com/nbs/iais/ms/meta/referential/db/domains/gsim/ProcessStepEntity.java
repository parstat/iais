package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ProcessStep")
@Table(name = "process_step")
public class ProcessStepEntity extends AbstractIdentifiableArtefact implements ProcessStep {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @Column(name = "comprehensive")
    private boolean comprehensive;

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
    private AdministrativeDetails administrativeDetails;

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    @JoinTable(name = "ps_agent_in_role",
            joinColumns = @JoinColumn(name = "ps_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
    private List<AgentInRole> administrators;

    @ManyToMany(targetEntity = ProcessStepEntity.class)
    @JoinTable(name = "sub_process_steps",
        joinColumns = @JoinColumn(name = "process_step_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "sub_process_step_id", referencedColumnName = "id"))
    private List<ProcessStep> subProcessSteps;

    @ManyToOne(targetEntity = BusinessProcessEntity.class, optional = false)
    @Column(name = "business_process_id")
    private BusinessProcess businessProcess;

    @ManyToOne(targetEntity = ChangeEventEntity.class)
    @JoinColumn(name = "change_event_id", referencedColumnName = "id")
    private ChangeEvent changeEvent;

    @Override
    public boolean isComprehensive() {
        return comprehensive;
    }

    @Override
    public void setComprehensive(final boolean comprehensive) {
        this.comprehensive = comprehensive;
    }

    @Override
    public List<ProcessStep> getSubProcessSteps() {
        return subProcessSteps;
    }

    @Override
    public void setSubProcessSteps(final List<ProcessStep> subProcessSteps) {
        this.subProcessSteps = subProcessSteps;
    }

    @Override
    public List<BusinessService> getUses() {
        return null;
    }

    @Override
    public void setUses(List<BusinessService> uses) {

    }

    @Override
    public List<BusinessService> getPerforms() {
        return null;
    }

    @Override
    public void setPerforms(List<BusinessService> performs) {

    }

    @Override
    public BusinessProcess getBusinessProcess() {
        return businessProcess;
    }

    @Override
    public void setBusinessProcess(final BusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
    }

    @Override
    public ProcessControl getExecute() {
        return null;
    }

    @Override
    public void setExecute(ProcessControl execute) {

    }

    @Override
    public List<ProcessControl> getInvokes() {
        return null;
    }

    @Override
    public void setInvokes(List<ProcessControl> invokes) {

    }

    @Override
    public List<ProcessStepInstance> getProcessStepInstances() {
        return null;
    }

    @Override
    public void setProcessStepInstances(List<ProcessStepInstance> processStepInstances) {

    }

    @Override
    public ProcessDesign getImplementedBy() {
        return null;
    }

    @Override
    public void setImplementedBy(ProcessDesign processDesign) {

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

    public ChangeEvent getChangeEvent() {
        return changeEvent;
    }

    public void setChangeEvent(final ChangeEvent changeEvent) {
        this.changeEvent = changeEvent;
    }

    public List<AgentInRole> getAdministrators() {
        return administrators;
    }

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
