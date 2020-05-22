package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessMethodEntity;

@Repository
public interface ProcessMethodRepository extends CrudRepository<ProcessMethodEntity, Long> {

    /**
     * Method to search by name in the selected language
     * @param language The selected language
     * @param name The name to search with
     * @return Iterable<ProcessMethodEntity>
     */
    @Query("SELECT pm FROM ProcessMethod pm INNER JOIN pm.name n INNER JOIN n.map m WHERE KEY(m) = :language AND m LIKE %:name%")
    Iterable<ProcessMethodEntity> findAllByNameInLanguageContaining(@Param(value = "language") String language,
                                                                     @Param(value = "name") String name);
}
