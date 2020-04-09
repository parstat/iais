package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.Software;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Software")
@Table(name = "software")
public class SoftwareEntity extends AbstractDomainObject implements Software {

    @Column(name = "name")
    private String name;

    @Column(name = "developer")
    private String developer;

    @Column(name = "website")
    private String website;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getDeveloper() {
        return developer;
    }

    @Override
    public void setDeveloper(final String developer) {
        this.developer = developer;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public void setWebsite(final String website) {
        this.website = website;
    }
}
