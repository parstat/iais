package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.*;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Agent")
@Table(name = "agent")
public class AgentEntity extends AbstractIdentifiableArtefact implements Agent {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @ManyToOne(targetEntity = AgentEntity.class)
    @Column(name = "parent_id")
    private Agent parent;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AgentType type;

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    private List<AgentInRole> administrators;

    @OneToMany(targetEntity = AgentInRoleEntity.class, mappedBy = "agent")
    private List<AgentInRole> agentInRoles;

    @OneToOne(targetEntity = AdministrativeDetailsEntity.class, orphanRemoval = true)
    @JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
    private AdministrativeDetails administrativeDetails;

    @ManyToOne(targetEntity = ChangeEventTupleEntity.class)
    @JoinColumn(name = "source_change_event_tuple_id", referencedColumnName = "id")
    private ChangeEventTuple sourceChangeEventTuple;

    @ManyToOne(targetEntity = ChangeEventTupleEntity.class)
    @JoinColumn(name = "target_change_event_tuple_id", referencedColumnName = "id")
    private ChangeEventTuple targetChangeEventTuple;

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
    public Agent getParent() {
        return parent;
    }

    @Override
    public void setParent(Agent parent) {
        this.parent = parent;
    }

    @Override
    public AgentType getType() {
        return type;
    }

    @Override
    public void setType(final AgentType type) {
        this.type = type;
    }

    @Override
    public List<AgentInRole> getAgentInRoles() {
        return agentInRoles;
    }

    @Override
    public void setAgentInRoles(final List<AgentInRole> agentInRoles) {
        this.agentInRoles = agentInRoles;
    }

    @Override
    public MultilingualText getName() {
        return this.name;
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
    public ChangeEventTuple getSourceChangeEventTuple() {
        return sourceChangeEventTuple;
    }

    @Override
    public void setSourceChangeEventTuple(final ChangeEventTuple sourceChangeEventTuple) {
        this.sourceChangeEventTuple = sourceChangeEventTuple;
    }

    @Override
    public ChangeEventTuple getTargetChangeEventTuple() {
        return targetChangeEventTuple;
    }

    @Override
    public void setTargetChangeEventTuple(final ChangeEventTuple targetChangeEventTuple) {
        this.targetChangeEventTuple = targetChangeEventTuple;
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
