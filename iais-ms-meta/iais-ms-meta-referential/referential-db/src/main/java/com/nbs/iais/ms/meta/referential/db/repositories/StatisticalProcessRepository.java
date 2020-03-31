package com.nbs.iais.ms.meta.referential.db.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.nbs.iais.ms.meta.referential.db.domains.StatisticalProcessEntity;

@Repository
public interface StatisticalProcessRepository extends CrudRepository<StatisticalProcessEntity, Long> {

}
