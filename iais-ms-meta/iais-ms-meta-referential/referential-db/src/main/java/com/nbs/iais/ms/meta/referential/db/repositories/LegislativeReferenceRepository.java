package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LegislativeReferenceRepository extends CrudRepository<LegislativeReferenceEntity, Long> {

	/**
	 * Method to get LegislativeReference by type
	 * 
	 * @param type The type of LegislativeReference (REGULATION, LAW, CODE,
	 *             GOVERNMENTAL_DECISION, AMENDMENT)
	 * 
	 * @return Iterable<LegislativeReferenceEntity>
	 */
	Iterable<LegislativeReferenceEntity> findByType(LegislativeType type);

	/**
	 * Method to search by name in the selected language
	 * 
	 * @param language The selected language
	 * @param name     The name to search with
	 * @return Iterable<LegislativeReferenceEntity>
	 */
	@Query("SELECT lr FROM LegislativeReference lr INNER JOIN lr.name n INNER JOIN n.map m WHERE KEY(m) = :language AND m LIKE %:name%")
	Iterable<LegislativeReferenceEntity> findAllByNameInLanguageContaining(
			@Param(value = "language") final String language, @Param(value = "name") final String name);

}
