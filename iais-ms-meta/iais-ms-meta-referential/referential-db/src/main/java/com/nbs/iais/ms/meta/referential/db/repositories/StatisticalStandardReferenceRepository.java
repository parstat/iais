package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalStandardReferenceRepository extends CrudRepository<StatisticalStandardReferenceEntity, Long> {

}
