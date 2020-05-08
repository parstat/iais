package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessDocumentEntity;

@Repository
public interface ProcessDocumentRepository extends CrudRepository<ProcessDocumentEntity, Long> {

}
