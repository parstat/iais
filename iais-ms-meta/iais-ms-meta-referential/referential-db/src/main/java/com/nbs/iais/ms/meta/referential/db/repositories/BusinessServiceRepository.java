package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessServiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessServiceRepository extends CrudRepository<BusinessServiceEntity, Long> {

    /**
     * Method to check if local id already exists
     * @param localId Local id of business service
     * @return true if at least one version of business service exists
     */
    boolean existsByLocalId(String localId);

    /**
     * Method to search by name in the selected language
     * @param language The selected language
     * @param name The name to search with
     * @return Iterable<BusinessServiceEntity>
     */
    @Query("SELECT bs FROM BusinessService bs INNER JOIN bs.name n INNER JOIN n.map m WHERE KEY(m) = :language AND m LIKE %:name%")
    Iterable<BusinessServiceEntity> findAllByNameInLanguageContaining(@Param(value = "language") String language,
                                                                      @Param(value = "name") String name);

}
