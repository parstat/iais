package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;

@Repository
public interface ProcessInputSpecificationRepository extends CrudRepository<ProcessInputSpecificationEntity, Long> {

	/**
	 * Method to get all Process ProcessInputSpecification of a process documentation
	 * @param sp ProcessDocumentation
	 * @return Iterable<ProcessInputSpecificationEntity> all Process ProcessInputSpecification of a process documentation
	 */
	 Iterable<ProcessInputSpecificationEntity> findByProcessDocumentation(ProcessDocumentation processDocumentation) ;
}
