package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;

@Repository
public interface ProcessDocumentRepository extends CrudRepository<ProcessDocumentEntity, Long> {
	
	
	/**
	 * Method to get all Process Documents of a process documentation
	 * @param processDocumentation ProcessDocumentation
	 * @return Iterable<ProcessDocumentEntity> all Process Documents of a process documentation
	 */
	 Iterable<ProcessDocumentEntity> findByProcessDocumentation(ProcessDocumentation processDocumentation) ;
}
