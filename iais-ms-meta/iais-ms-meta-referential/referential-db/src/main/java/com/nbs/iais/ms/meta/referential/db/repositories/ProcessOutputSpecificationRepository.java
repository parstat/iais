package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessOutputSpecificationEntity;

@Repository
public interface ProcessOutputSpecificationRepository extends CrudRepository<ProcessOutputSpecificationEntity, Long> {

}
