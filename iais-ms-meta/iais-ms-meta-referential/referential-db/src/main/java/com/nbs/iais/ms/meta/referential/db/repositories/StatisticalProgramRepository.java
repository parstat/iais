package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalProgramRepository extends CrudRepository<StatisticalProgramEntity, Long> {

    Iterable<StatisticalProgramEntity> findAllByLocalId(String localId);

    boolean existsByLocalId(String localId);

}
