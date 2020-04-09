package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEventTuple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "ChangeEvent")
@Table(name = "change_event")
public class ChangeEventEntity extends AbstractDomainObject implements ChangeEvent {

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @Column(name = "change_type")
    private String changeType;

    @Column(name = "identifier")
    private UUID identifier;

    @OneToMany(targetEntity = AgentInRoleEntity.class, mappedBy = "changeEvent")
    private List<AgentInRole> attributedTo;

    @Override
    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    @Override
    public void setChangeDate(final LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    @Override
    public String getChangeType() {
        return changeType;
    }

    @Override
    public void setChangeType(final String changeType) {
        this.changeType = changeType;
    }

    @Override
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(final UUID identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<ChangeEventTuple> getChangeEventTuples() {
        return null;
    }

    @Override
    public void setChangeEventTuples(List<ChangeEventTuple> changeEventTuples) {

    }

    @Override
    public List<AgentInRole> getAttributed() {
        return attributedTo;
    }

    @Override
    public void setAttributed(final List<AgentInRole> attributed) {
        this.attributedTo = attributed;
    }
}
