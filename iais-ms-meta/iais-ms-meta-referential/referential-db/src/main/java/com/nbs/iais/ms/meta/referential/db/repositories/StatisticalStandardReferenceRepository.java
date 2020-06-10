package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalStandardReferenceRepository
		extends CrudRepository<StatisticalStandardReferenceEntity, Long> {

	/**
	 * Method to get StatisticalStandardReference by type
	 * @param type The type of StatisticalStandardReference (CLASSIFICATIONS,CONCEPTS,DEFINITIONS, METHODOLOGIES,
	 *  PROCEDURES,  RECOMMENDATIONS, FRAMEWORK)
	 *  
	 * @return Iterable<StatisticalStandardReferenceEntity>
	 */
	Iterable<StatisticalStandardReferenceEntity> findByType(StatisticalStandardType type);

	/**
	 * Method to search by name in the selected language
	 * @param language The selected language
	 * @param name The name to search with
	 * @return Iterable<StatisticalStandardReferenceEntity>
	 */
	@Query("SELECT ssr FROM StatisticalStandardReference ssr INNER JOIN ssr.name n INNER JOIN n.map m WHERE KEY(m) = :language AND LOWER(m) LIKE LOWER(CONCAT('%', :name, '%'))")
	Iterable<StatisticalStandardReferenceEntity> findAllByNameInLanguageContaining(@Param(value = "language") final String language,
															@Param(value = "name") final String name);

}
