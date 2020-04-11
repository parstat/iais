package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessFunction;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;

import javax.persistence.*;
import java.util.List;

public class BusinessFunctionEntity extends AbstractIdentifiableArtefact implements BusinessFunction {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
    private AdministrativeDetails administrativeDetails;

    @ManyToOne(targetEntity = ChangeEventEntity.class)
    @JoinColumn(name = "change_event_id", referencedColumnName = "id")
    private ChangeEvent changeEvent;

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    @JoinTable(name = "bf_administrators",
            joinColumns = @JoinColumn(name = "bf_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
    private List<AgentInRole> administrators;

    @OneToMany(targetEntity = ProcessDesignEntity.class, mappedBy = "businessFunction")
    private List<ProcessDesign> processDesigns;

    @Override
    public List<ProcessDesign> getProcessDesigns() {
        return processDesigns;
    }

    @Override
    public void setProcessDesigns(final List<ProcessDesign> processDesigns) {
        this.processDesigns = processDesigns;
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
