package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.enums.Language;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface IdentifiableArtefact extends DomainObject {

    MultilingualText getName();

    String getName(Language language);

    void setName(MultilingualText name);

    void setName(String name, Language language);

    MultilingualText getDescription();

    String getDescription(Language language);

    void setDescription(MultilingualText description);

    void setDescription(String description, Language language);

    String getLocalId();

    void setLocalId(String localId);

    String getVersion();

    void setVersion(String version);

    LocalDateTime getVersionDate();

    void setVersionDate(LocalDateTime versionDate);

    String getVersionRationale();

    void setVersionRationale(String versionRationale);

    //not all artefact has a change event
    //ChangeEvent getChangeEvent();

    //void setChangeEvent(ChangeEvent changeEvent);

    //ChangeEventTuple getSourceChangeEventTuple();

    //void setSourceChangeEventTuple(ChangeEventTuple sourceChangeEventTuple);

    //ChangeEventTuple getTargetChangeEventTuple();

    //void setTargetChangeEventTuple(ChangeEventTuple targetChangeEventTuple);

    //not all artefact has administrators
    //List<AgentInRole> getAdministrators();

    //void setAdministrators(List<AgentInRole> administrators);

    AdministrativeDetails getAdministrativeDetails();

    void setAdministrativeDetails(AdministrativeDetails administrativeDetails);


}
