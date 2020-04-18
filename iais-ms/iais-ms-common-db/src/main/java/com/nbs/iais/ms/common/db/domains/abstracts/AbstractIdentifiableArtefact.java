package com.nbs.iais.ms.common.db.domains.abstracts;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractIdentifiableArtefact extends AbstractDomainObject implements IdentifiableArtefact {

    @Column(name = "local_id")
    private String localId;

    @Column(name = "version")
    private String version;

    @Column(name = "version_date")
    private LocalDateTime versionDate;

    @Column(name = "version_rationale")
    private String versionRationale;

    public AbstractIdentifiableArtefact() {
        super();
    }

    @Override
    public String getLocalId() {
        return localId;
    }

    @Override
    public void setLocalId(final String localId) {
        this.localId = localId;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(final String version) {
        this.version = version;
    }

    @Override
    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    @Override
    public void setVersionDate(final LocalDateTime versionDate) {
        this.versionDate = versionDate;
    }

    @Override
    public String getVersionRationale() {
        return versionRationale;
    }

    @Override
    public void setVersionRationale(final String versionRationale) {
        this.versionRationale = versionRationale;
    }
}
