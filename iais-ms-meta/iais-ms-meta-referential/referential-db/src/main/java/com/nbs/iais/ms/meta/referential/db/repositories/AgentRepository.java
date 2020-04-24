package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends CrudRepository<AgentEntity, Long> {

    Optional<AgentEntity> findByAccount(Long account);

}
