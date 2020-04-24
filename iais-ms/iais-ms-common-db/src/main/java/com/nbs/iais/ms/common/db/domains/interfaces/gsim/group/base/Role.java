package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;


import java.util.List;

public interface Role extends IdentifiableArtefact {

    List<AgentInRole> getAgentInRoles();

    void setAgentInRoles(List<AgentInRole> agentInRoles);

}
