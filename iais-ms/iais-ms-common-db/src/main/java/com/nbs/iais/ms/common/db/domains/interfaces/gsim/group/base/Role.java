package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.enums.RoleType;

import java.util.List;

public interface Role extends IdentifiableArtefact {

    RoleType getType();

    void setType(RoleType type);

    List<AgentInRole> getAgentInRoles();

    void setAgentInRoles(List<AgentInRole> agentInRoles);

}
