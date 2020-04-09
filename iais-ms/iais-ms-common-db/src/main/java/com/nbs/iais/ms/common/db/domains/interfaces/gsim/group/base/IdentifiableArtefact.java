package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;

import java.time.LocalDateTime;
import java.util.List;

public interface IdentifiableArtefact extends DomainObject {

    MultilingualText getName();

    void setName(MultilingualText name);

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

    String getLocalId();

    void setLocalId(String localId);

    String getVersion();

    void setVersion(String version);

    LocalDateTime getVersionDate();

    void setVersionDate(LocalDateTime versionDate);

    String getVersionRationale();

    void setVersionRationale(String versionRationale);

    ChangeEvent getChangeEvent();

    void setChangeEvent(ChangeEvent changeEvent);

    List<AgentInRole> getAdministrators();

    void setAdministrators(List<AgentInRole> administrators);

    AdministrativeDetails getAdministrativeDetails();

    void setAdministrativeDetails(AdministrativeDetails administrativeDetails);

}
