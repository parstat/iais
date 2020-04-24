package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Role;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.enums.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity(name = "AgentInRole")
@Table(name = "agent_in_role")
public class AgentInRoleEntity extends AbstractDomainObject implements AgentInRole {

    @ManyToOne(targetEntity = AgentEntity.class, optional = false)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    @ManyToOne(targetEntity = ChangeEventEntity.class)
    @JoinColumn(name = "change_event_id")
    private ChangeEvent changeEvent;

    @ManyToMany(targetEntity = StatisticalProgramEntity.class, mappedBy = "administrators")
    private List<StatisticalProgram> statisticalPrograms;

    @ManyToMany(targetEntity = ProcessDocumentationEntity.class, mappedBy = "administrators")
    private List<ProcessDocumentation> processDocumentations;

    public AgentInRoleEntity() {
        super();
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
    public Agent getAgent() {
        return agent;
    }

    @Override
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public RoleType getRole() {
        return role;
    }

    @Override
    public void setRole(final RoleType role) {
        this.role = role;
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
    public List<ProcessDocumentation> getProcessDocumentations() {
        return processDocumentations;
    }

    @Override
    public void setProcessDocumentations(final List<ProcessDocumentation> processDocumentations) {
        this.processDocumentations = processDocumentations;
    }
}
