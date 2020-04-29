package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
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

    /**
     * Query to find statistical program by LocalIdAndVersion
     * @param localId of the statistical program
     * @param version of the statistical program
     * @return Optional<StatisticalProgramEntity>
     */
    Optional<StatisticalProgramEntity> findByLocalIdAndVersion(String localId, String version);


    /**
     * To query if previous new version already exists
     * @param localId of the statistical program
     * @param version of the statistical program
     * @return true if statistical program of requested version exists
     */
    boolean existsByLocalIdAndVersion(String localId, String version);

    /**
     * @param localId id of the statistical program
     * @return Optional<StatisticalProgramEntity> the latest version of the statistical program
     */
    Optional<StatisticalProgramEntity> findAllTopByLocalIdOrderByVersionDateDesc(String localId);

    /**
     * @param language to search by name
     * @param name to search
     * @return Iterable<StatisticalProgramEntity> 
     */
    @Query("SELECT sp FROM StatisticalProgram sp INNER JOIN sp.name n INNER JOIN n.map m WHERE KEY(m) = :language AND m LIKE %:name%")
    Iterable<StatisticalProgramEntity> findAllByNameInLanguageContaining(@Param(value = "language") String language,
                                                                       @Param(value = "name") String name);

}
