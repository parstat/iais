package com.nbs.iais.common.db.domains.interfaces.group.base;

import com.nbs.iais.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.common.db.domains.interfaces.group.base.AdministrativeDetails;
import com.nbs.iais.common.db.domains.interfaces.group.base.AgentInRole;
import com.nbs.iais.common.db.domains.interfaces.group.base.ChangeEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface IdentifiableArtefact extends DomainObject {

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

    MultilingualText getName();

    void setName(MultilingualText name);

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
