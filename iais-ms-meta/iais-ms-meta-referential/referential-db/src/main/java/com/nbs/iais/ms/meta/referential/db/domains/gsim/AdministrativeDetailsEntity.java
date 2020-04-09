package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.enums.AdministrativeStatus;
import com.nbs.iais.ms.common.enums.LifeCycleStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "AdministrativeDetails")
@Table(name = "administrative_details")
public class AdministrativeDetailsEntity extends AbstractDomainObject implements AdministrativeDetails  {

    @Column(name = "alias")
    private String alias;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AdministrativeStatus administrativeStatus;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(name = "life_cycle_status")
    private LifeCycleStatus lifeCycleStatus;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

    @ElementCollection(targetClass = String.class)
    private List<String> documentations;

    @ElementCollection(targetClass = String.class)
    private List<String> urls;

    @Override
    public AdministrativeStatus getAdministrativeStatus() {
        return administrativeStatus;
    }

    @Override
    public void setAdministrativeStatus(final AdministrativeStatus administrativeStatus) {
        this.administrativeStatus = administrativeStatus;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(final String alias) {
        this.alias = alias;
    }

    @Override
    public List<String> getDocumentations() {
        return documentations;
    }

    @Override
    public void setDocumentations(final List<String> documentations) {
        this.documentations = documentations;
    }

    @Override
    public LocalDateTime getLastUpdatedDate() {
        return lastUpdated;
    }

    @Override
    public void setLastUpdatedDate(final LocalDateTime lastUpdatedDate) {
        this.lastUpdated = lastUpdatedDate;
    }

    @Override
    public LifeCycleStatus getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    @Override
    public void setLifeCycleStatus(final LifeCycleStatus lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(final LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public List<String> getUrls() {
        return urls;
    }

    @Override
    public void setUrls(final List<String> urls) {
        this.urls = urls;
    }

    @Override
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public LocalDateTime getValidTo() {
        return validTo;
    }

    @Override
    public void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }
}
