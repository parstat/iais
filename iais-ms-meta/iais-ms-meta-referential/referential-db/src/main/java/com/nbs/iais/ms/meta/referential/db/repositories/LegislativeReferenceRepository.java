package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegislativeReferenceRepository extends CrudRepository<LegislativeReferenceEntity, Long> {

}
