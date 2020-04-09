package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.enums.AdministrativeStatus;
import com.nbs.iais.ms.common.enums.LifeCycleStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AdministrativeDetails extends DomainObject {

    AdministrativeStatus getAdministrativeStatus();

    void setAdministrativeStatus(AdministrativeStatus administrativeStatus);

    String getAlias();

    void setAlias(String alias);

    List<String> getDocumentations();

    void setDocumentations(List<String> documentations);

    LocalDateTime getLastUpdatedDate();

    void setLastUpdatedDate(LocalDateTime lastUpdatedDate);

    LifeCycleStatus getLifeCycleStatus();

    void setLifeCycleStatus(LifeCycleStatus lifeCycleStatus);

    LocalDateTime getReleaseDate();

    void setReleaseDate(LocalDateTime releaseDate);

    List<String> getUrls();

    void setUrls(List<String> urls);

    LocalDateTime getValidFrom();

    void setValidFrom(LocalDateTime validFrom);

    LocalDateTime getValidTo();

    void setValidTo(LocalDateTime validTo);

}
