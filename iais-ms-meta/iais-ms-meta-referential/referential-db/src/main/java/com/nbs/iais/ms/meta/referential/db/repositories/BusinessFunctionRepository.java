package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessFunctionRepository extends CrudRepository<BusinessFunctionEntity, Long> {

    Optional<BusinessFunctionEntity> findByLocalIdAndVersion(String localId, String version);

    Iterable<BusinessFunctionEntity> findAllByLocalIdStartingWith(String phase);

    Iterable<BusinessFunctionEntity> findAllByNameContaining(String name);

}
