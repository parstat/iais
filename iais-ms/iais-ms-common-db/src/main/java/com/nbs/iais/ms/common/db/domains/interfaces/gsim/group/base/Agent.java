package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.enums.AgentType;

import java.util.List;

public interface Agent extends IdentifiableArtefact {

    Agent getParent();

    void setParent(Agent agent);

    AgentType getType();

    void setType(AgentType type);

    List<Agent> getChildren();

    void setChildren(List<Agent> children);

    List<AgentInRole> getAgentInRoles();

    void setAgentInRoles(List<AgentInRole> agentInRoles);

    Long getAccount();

    void setAccount(Long account);

}
