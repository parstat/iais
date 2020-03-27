package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ChangeEvent extends DomainObject {

    LocalDateTime getChangeDate();

    void setChangeDate(LocalDateTime changeDate);

    String getChangeType();

    void setChangeType(String changeType);

    UUID getIdentifier();

    void setIdentifier(UUID identifier);

    List<IdentifiableArtefact> getSources();

    void setSources(List<IdentifiableArtefact> sources);

    List<IdentifiableArtefact> getTargets();

    void setTargets(List<IdentifiableArtefact> targets);

    List<ChangeEventTuple> getChangeEventTuples();

    void setChangeEventTuples(List<ChangeEventTuple> changeEventTuples);

    List<AgentInRole> getAttributed();

    void setAttributed(List<AgentInRole> attributed);
}
