package com.nbs.iais.ms.common.db.domains.abstracts;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class AbstractDomainObject implements DomainObject {

    @Id
    private UUID id;

    public AbstractDomainObject() {

    }

    public AbstractDomainObject(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
