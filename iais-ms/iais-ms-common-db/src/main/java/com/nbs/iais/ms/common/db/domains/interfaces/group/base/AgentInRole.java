package com.nbs.iais.ms.common.db.domains.interfaces.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;

import java.util.List;

public interface AgentInRole extends DomainObject {

    MultilingualText getName();

    void setName(MultilingualText name);

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

    List<Agent> getAgents();

}
