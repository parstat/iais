package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.enums.AdministrativeStatus;
import com.nbs.iais.ms.common.enums.LifeCycleStatus;

import java.time.LocalDate;
import java.util.List;

public interface AdministrativeDetails extends DomainObject {

    AdministrativeStatus getAdministrativeStatus();

    void setAdministrativeStatus(AdministrativeStatus administrativeStatus);

    String getAlias();

    void setAlias(String alias);

    List<String> getDocumentations();

    void setDocumentations(List<String> documentations);

    LocalDate getLastUpdatedDate();

    void setLastUpdatedDate(LocalDate lastUpdatedDate);

    LifeCycleStatus getLifeCycleStatus();

    void setLifeCycleStatus(LifeCycleStatus lifeCycleStatus);

    LocalDate getReleaseDate();

    void setReleaseDate(LocalDate releaseDate);

    List<String> getUrls();

    void setUrls(List<String> urls);

    LocalDate getValidFrom();

    void setValidFrom(LocalDate validFrom);

    LocalDate getValidTo();

    void setValidTo(LocalDate validTo);

}
