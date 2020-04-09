package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;


public interface AgentInRole extends DomainObject {

    MultilingualText getName();

    void setName(MultilingualText name);

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

    Agent getAgent();

    void setAgent(Agent agent);

    Role getRole();

    void setRole(Role role);

}
