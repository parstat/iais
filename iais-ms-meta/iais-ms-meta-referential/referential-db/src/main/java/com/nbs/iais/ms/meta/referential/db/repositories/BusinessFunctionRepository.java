package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessFunctionRepository extends CrudRepository<BusinessFunctionEntity, Long> {

    Optional<BusinessFunctionEntity> findByLocalIdAndVersion(String localId, String version);

    Iterable<BusinessFunctionEntity> findAllByLocalIdStartingWith(String phase);

    //A named query because search should be done only for selected language texts
    @Query("SELECT bf FROM BusinessFunction bf INNER JOIN bf.name n INNER JOIN n.map m WHERE KEY(m) = :language AND LOWER(m) LIKE LOWER(CONCAT('%', :name, '%'))")
    Iterable<BusinessFunctionEntity> findAllByNameInLanguageContaining(@Param(value = "language") String language,
                                                                       @Param(value = "name") String name);

}
