package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessInputSpecificationEntity;

@Repository
public interface ProcessInputSpecificationRepository extends CrudRepository<ProcessInputSpecificationEntity, Long> {

}
