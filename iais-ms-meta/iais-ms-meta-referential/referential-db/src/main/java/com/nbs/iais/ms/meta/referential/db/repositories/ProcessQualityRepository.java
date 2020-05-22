package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessQualityEntity;

@Repository
public interface ProcessQualityRepository extends CrudRepository<ProcessQualityEntity, Long> {

	/**
	 * Method to get all Process quality of a process documentation
	 * @param processDocumentation ProcessDocumentation
	 * @return Iterable<ProcessQualityEntity> all Process quality of a process documentation
	 */
	 Iterable<ProcessQualityEntity> findByProcessDocumentation(ProcessDocumentation processDocumentation) ;
}
