package com.nbs.iais.ms.common.db.domains.abstracts;

import com.nbs.iais.ms.common.db.domains.interfaces.Linkable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLinkable extends AbstractDomainObject implements Linkable {

    @Column(name = "link", nullable = false)
    private String link;


    protected AbstractLinkable() {
        super();
    }


    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(final String link) {
        this.link = link;
    }
}
