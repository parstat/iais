package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends CrudRepository<AgentEntity, Long> {

	Optional<AgentEntity> findByAccount(Long account);

	Optional<AgentEntity> findByLocalId(String localId);

	Iterable<AgentEntity> findByIdIn(List<Long> ids);

	Iterable<AgentEntity> findByParent(Agent parent);

	Iterable<AgentEntity> findByType(AgentType type);

	// the method retun a list of agent filtered by selected paramters in parent,
	// type and name by lanaguage
	@Query("SELECT DISTINCT(ag) FROM Agent ag INNER JOIN ag.name n INNER JOIN n.map m WHERE 1=1 AND ((:type IS NULL) OR (ag.type=:type)) AND ((:parent IS NULL) OR (ag.parent=:parent)) AND  ((:name IS NULL) OR ( KEY(m) = :language AND m LIKE %:name% ))")
	Iterable<AgentEntity> findAllOrBySelectedNameAndTypeAndParent(@Param("type") AgentType type,
			@Param("parent") Agent parent, @Param("name") String name, @Param("language") String language);
	 
}
