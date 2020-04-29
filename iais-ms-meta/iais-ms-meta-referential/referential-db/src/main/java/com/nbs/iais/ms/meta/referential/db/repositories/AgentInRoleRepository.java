package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentInRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentInRoleRepository extends CrudRepository<AgentInRoleEntity, Long> {

    Optional<AgentInRoleEntity> findByAgentAndRole(Agent agent, RoleType role);
}
