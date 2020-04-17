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
    @JoinColumn(name = "key_description", referencedColumnName = "id")
    private MultilingualText description;

    @ManyToOne(targetEntity = AgentEntity.class)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Agent parent;

    @OneToMany(targetEntity = AgentEntity.class, mappedBy = "parent")
    private List<Agent> children;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AgentType type;

    @OneToMany(targetEntity = AgentInRoleEntity.class, mappedBy = "agent")
    private List<AgentInRole> agentInRoles;

    @OneToOne(targetEntity = AdministrativeDetailsEntity.class, orphanRemoval = true)
    @JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
    private AdministrativeDetails administrativeDetails;

    @Column(name = "account_id")
    private Long account;

    public AgentEntity() {
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
    public List<Agent> getChildren() {
        return children;
    }

    @Override
    public void setChildren(final List<Agent> children) {
        this.children = children;
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
    public Long getAccount() {
        return account;
    }

    @Override
    public void setAccount(final Long account) {
        this.account = account;
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
    public AdministrativeDetails getAdministrativeDetails() {
        return administrativeDetails;
    }

    @Override
    public void setAdministrativeDetails(final AdministrativeDetails administrativeDetails) {
        this.administrativeDetails = administrativeDetails;
    }
}
