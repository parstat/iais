package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
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

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    @JoinTable(name = "spc_agent_in_role",
            joinColumns = @JoinColumn(name = "spc_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
    private List<AgentInRole> administrators;

    @Column(name = "reference_period_start")
    private LocalDateTime referencePeriodStart;

    @Column(name = "reference_period_end")
    private LocalDateTime referencePeriodEnd;

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
        return null;
    }

    @Override
    public void setStatisticalProgram(StatisticalProgram statisticalProgram) {

    }

    @Override
    public MultilingualText getName() {
        return null;
    }

    @Override
    public void setName(MultilingualText name) {

    }

    @Override
    public MultilingualText getDescription() {
        return null;
    }

    @Override
    public void setDescription(MultilingualText description) {

    }

    public List<AgentInRole> getAdministrators() {
        return null;
    }

    public void setAdministrators(List<AgentInRole> administrators) {

    }

    @Override
    public AdministrativeDetails getAdministrativeDetails() {
        return null;
    }

    @Override
    public void setAdministrativeDetails(AdministrativeDetails administrativeDetails) {

    }
}
