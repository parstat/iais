package com.nbs.iais.ms.common.db.domains.abstracts;

import com.nbs.iais.ms.common.db.domains.interfaces.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractIdentifiableArtefact extends AbstractDomainObject implements IdentifiableArtefact {

    private MultilingualText description;

    private MultilingualText name;

    @Column(name = "local_id")
    private String localId;

    @Column(name = "version")
    private String version;

    @Column(name = "version_date")
    private LocalDateTime versionDate;

    @Column(name = "version_rationale")
    private String versionRationale;

    @OneToOne(optional = true, orphanRemoval = true)
    private AdministrativeDetails administrativeDetails;

    public AbstractIdentifiableArtefact() {
        super();
    }

    public AbstractIdentifiableArtefact(final UUID id) {
        super(id);
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
    public MultilingualText getName() {
        return name;
    }

    @Override
    public void setName(final MultilingualText name) {
        this.name = name;
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

    @Override
    public AdministrativeDetails getAdministrativeDetails() {
        return administrativeDetails;
    }

    @Override
    public void setAdministrativeDetails(final AdministrativeDetails administrativeDetails) {
        this.administrativeDetails = administrativeDetails;
    }
}
