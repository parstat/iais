package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessServiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessServiceRepository extends CrudRepository<BusinessServiceEntity, Long> {

    /**
     * Method to check if local id already exists
     * @param localId Local id of business service
     * @return true if at least one version of business service exists
     */
    boolean existsByLocalId(String localId);

    /**
     * Method all versions of business service
     * @param localId the local id of business service
     * @return Iterable<BusinessServiceEntity>
     */
    Iterable<BusinessServiceEntity> findAllByLocalId(String localId);

    /**
     * Method to get the latest version of business service
     * @param localId The local id of business service
     * @return Optional<BusinessServiceEntity>
     */
    Optional<BusinessServiceEntity> findAllTopByLocalIdOrderByVersionDateDesc(String localId);

    /**
     * Method to get the business service by local id and version
     * @param localId the local id of the business service
     * @param version the version of business service
     * @return Optional<BusinessServiceEntity>
     */
    Optional<BusinessServiceEntity> findByLocalIdAndVersion(String localId, String version);

    /**
     * Method to search by name in the selected language
     * @param language The selected language
     * @param name The name to search with
     * @return Iterable<BusinessServiceEntity>
     */
    @Query("SELECT bs FROM BusinessService bs INNER JOIN bs.name n INNER JOIN n.map m WHERE KEY(m) = :language AND LOWER(m) LIKE LOWER(CONCAT('%', :name, '%'))")
    Iterable<BusinessServiceEntity> findAllByNameInLanguageContaining(@Param(value = "language") String language,
                                                                      @Param(value = "name") String name);

}
