package com.nbs.iais.ms.meta.referential.db.repositories;


import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LegislativeReferenceRepository extends CrudRepository<LegislativeReferenceEntity, Long> {
 	
	// the method retun a list of LegislativeReferenceEntity filtered by selected parameters in type and name by language
		@Query("SELECT DISTINCT (ss) FROM LegislativeReference ss INNER JOIN ss.name n INNER JOIN n.map m WHERE 1=1 AND ((:type IS NULL) OR (ss.type=:type))  AND  ((:name IS NULL) OR ( KEY(m) = :language AND m LIKE %:name% ))")
	  	List<LegislativeReferenceEntity> findAllOrBySelectedNameAndType(@Param("type")LegislativeType type,
				@Param("name") String name, @Param("language")  String language);


	Optional<LegislativeReferenceEntity> findByLocalId(String localId);

}
