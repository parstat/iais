package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.AgentEntity;

import org.modelmapper.internal.asm.tree.LabelNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends CrudRepository<AgentEntity, Long> {

	/**
	 * Method to find the agent by the account mapped with it
	 * @param account the INDIVIDUAL account mapped with the agent
	 * @return Optional<AgentEntity>
	 */
	Optional<AgentEntity> findByAccount(Long account);

	/**
	 * Method to find agent by localId
	 * @param localId Local id of the agent (for INDIVIDUAL will be the email)
	 * @return Optional<AgentEntity>
	 */
	Optional<AgentEntity> findByLocalId(String localId);

	Iterable<AgentEntity> findByIdIn(List<Long> ids);

	/**
	 * Method to find all children of a agent
	 * @param parent The parent to find the children of
	 * @return Iterable<AgentEntity>
	 */
	Iterable<AgentEntity> findByParent(Agent parent);

	/**
	 * Method to get Agents by type
	 * @param type The type of Agent (INDIVIDUAL, DIVISION, ORGANIZATION)
	 * @return Iterable<AgentEntity>
	 */
	Iterable<AgentEntity> findByType(AgentType type);

	/**
	 * Method to search by name in the selected language
	 * @param language The selected language
	 * @param name The name to search with
	 * @return Iterable<AgentEntity>
	 */
	@Query("SELECT a FROM Agent a INNER JOIN a.name n INNER JOIN n.map m WHERE KEY(m) = :language AND m LIKE %:name%")
	Iterable<AgentEntity> findAllByNameInLanguageContaining(@Param(value = "language") final String language,
															@Param(value = "name") final String name);
}
