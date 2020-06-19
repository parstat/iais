package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;

import java.util.Optional;

@Repository
public interface ProcessInputSpecificationRepository extends CrudRepository<ProcessInputSpecificationEntity, Long> {

	/**
	 * Method to get all Process ProcessInputSpecification of a process documentation
	 * @param processDocumentation ProcessDocumentation
	 * @return Iterable<ProcessInputSpecificationEntity> all Process ProcessInputSpecification of a process documentation
	 */
	 Iterable<ProcessInputSpecificationEntity> findByProcessDocumentation(ProcessDocumentation processDocumentation) ;


	 @Query("SELECT processInput from ProcessInputSpecification processInput INNER JOIN processInput.processDocumentation documentation WHERE processInput.id = :inputId AND documentation.id = :documentationId")
	 Optional<ProcessInputSpecificationEntity> findByIdAndProcessDocumentation(Long inputId, Long documentationId);

}
