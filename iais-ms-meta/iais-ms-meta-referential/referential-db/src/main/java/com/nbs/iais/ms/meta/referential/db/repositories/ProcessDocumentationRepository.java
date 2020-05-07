package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessFunction;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;

@Repository
public interface ProcessDocumentationRepository extends CrudRepository<ProcessDocumentationEntity, Long> {


	boolean existsByStatisticalProgramAndBusinessFunction(StatisticalProgram sp, BusinessFunction bf);
	/**
	 * Method to get ProcessDocumentationEntity by frequency
	 * 
	 * @param frequency The frequency of ProcessDocumentation ( DAILY, WEEKLY,
	 *                  MONTHLY, QUARTERLY, SEMIYEARLY, YEARLY, DECENNIAL)
	 * 
	 * @return Iterable<ProcessDocumentationEntity>
	 */
	Iterable<ProcessDocumentationEntity> findByFrequency(Frequency frequency);

	/**
	 * Method to search by name in the selected language
	 * 
	 * @param language The selected language
	 * @param name     The name to search with
	 * @return Iterable<ProcessDocumentationEntity>
	 */
	@Query("SELECT ssr FROM ProcessDocumentation ssr INNER JOIN ssr.name n INNER JOIN n.map m WHERE KEY(m) = :language AND m LIKE %:name%")
	Iterable<ProcessDocumentationEntity> findAllByNameInLanguageContaining(
			@Param(value = "language") final String language, @Param(value = "name") final String name);



}
