package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.*;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;
import java.util.List;

public class ChangeEventTupleEntity extends AbstractIdentifiableArtefact implements ChangeEventTuple {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @ManyToOne(targetEntity = ChangeEventEntity.class)
    @JoinColumn(name = "change_event_id" )
    private ChangeEvent changeEvent;

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    @JoinTable(name = "change_tuple_agent_in_role",
            joinColumns = @JoinColumn(name = "change_tuple_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
    private List<AgentInRole> administrators;

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
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
