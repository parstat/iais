package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessOutputSpecificationEntity;

@Repository
public interface ProcessOutputSpecificationRepository extends CrudRepository<ProcessOutputSpecificationEntity, Long> {
	/**
	 * Method to get all Process ProcessOutputSpecification of a process
	 * documentation
	 * 
	 * @param sp ProcessDocumentation
	 * @return Iterable<ProcessOutputSpecificationEntity> all Process
	 *         ProcessOutputSpecification of a process documentation
	 */
	Iterable<ProcessOutputSpecificationEntity> findByProcessDocumentation(ProcessDocumentation processDocumentation);
}
