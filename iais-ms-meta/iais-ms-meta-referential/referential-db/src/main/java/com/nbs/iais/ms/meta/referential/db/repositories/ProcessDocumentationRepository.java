package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessFunction;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentationEntity;

import java.util.Optional;

@Repository
public interface ProcessDocumentationRepository extends CrudRepository<ProcessDocumentationEntity, Long> {


	/**
	 * Method to get all Process Documentations of a statistical program (including different versions)
	 * @param sp Statistical Program
	 * @return Iterable<ProcessDocumentationEntity> all process documentation of a statistical program
	 */
	Iterable<ProcessDocumentationEntity> findAllByStatisticalProgram(StatisticalProgram sp);

	/**
	 * Method to get all versions of Process documentation of a statistical program for a single business function
	 * @param sp Statistical Program
	 * @param bf Business Function
	 * @return Iterable<ProcessDocumentationEntity> all version of a process documentation
	 */
	Iterable<ProcessDocumentationEntity> findAllByStatisticalProgramAndBusinessFunction(StatisticalProgram sp,
																						BusinessFunction bf);

	/**
	 * Find the latest version of Process documentation
	 * @param sp The statistical program
	 * @param bf The business function
	 * @return Optional<ProcessDocumentationEntity>
	 */
	Optional<ProcessDocumentationEntity> findAllTopByStatisticalProgramAndBusinessFunctionOrderByVersionDateDesc(
			StatisticalProgram sp, BusinessFunction bf);

	/**
	 * Method to check if a PorcessDcoumentation already exists for a statistical program process
	 * @param sp Statistical Program
	 * @param bf Business Function
	 * @return boolean true if the process documentation already exist
	 */
	boolean existsByStatisticalProgramAndBusinessFunction(StatisticalProgram sp, BusinessFunction bf);
	
	
	/**
	 * Method to check if a ProcessDocumentation already exists for a statistical program process , business function and version
	 * @param sp Statistical Program
	 * @param bf Business Function
	 * @param version  Version of ProcessDocumentation
	 * @return boolean true if the process documentation already exist
	 */
	boolean existsByStatisticalProgramAndBusinessFunctionAndVersion(StatisticalProgram sp, BusinessFunction bf,String version);

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
