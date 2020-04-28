package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticalProgramRepository extends CrudRepository<StatisticalProgramEntity, Long> {

    /**
     * @param localId of the statistical program
     * @return Iterable<StatisticalProgramEntity> all versions of a statistical program
     */
    Iterable<StatisticalProgramEntity> findAllByLocalId(String localId);

    /**
     * @param localId of the statistical program
     * @return true if any version of statistical program exists
     */
    boolean existsByLocalId(String localId);

    Optional<StatisticalProgramEntity> findByLocalIdAndVersion(String localId, String version);

    /**
     * @param localId id of the statistical program
     * @return Optional<StatisticalProgramEntity> the latest version of the statistical program
     */
    Optional<StatisticalProgramEntity> findAllTopByLocalIdOrderByVersionDateDesc(String localId);

}
