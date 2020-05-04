package com.nbs.iais.ms.meta.referential.db.repositories;

import com.nbs.iais.ms.common.enums.StatisticalStandardType;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalStandardReferenceRepository
		extends CrudRepository<StatisticalStandardReferenceEntity, Long> {

	//FIXME if this is not a combination of filters, so finding statistical standard by name while filtered by type then it is better to device in two methods
	// the method return a list of StatisticalStandardReferenceEntity filtered by selected parameters in type and name by language
	@Query("SELECT DISTINCT (ss) FROM StatisticalStandardReference ss INNER JOIN ss.name n INNER JOIN n.map m WHERE 1=1 AND ((:type IS NULL) OR (ss.type=:type))  AND  ((:name IS NULL) OR ( KEY(m) = :language AND m LIKE %:name% ))")
  	List<StatisticalStandardReferenceEntity> findAllOrBySelectedNameAndType(@Param("type")StatisticalStandardType type,
			@Param("name") String name, @Param("language")  String language);

	//FIXME I think statistical standards can have different version also, this can return many standards
	//to make this return one, version should be added to keep it in sync with others
	//or we can just remove it for now, we can add it later if requested
	Optional<StatisticalStandardReferenceEntity> findByLocalId(String localId);


}
