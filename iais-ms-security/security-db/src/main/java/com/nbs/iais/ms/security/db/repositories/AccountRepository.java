package com.nbs.iais.ms.security.db.repositories;

import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

   Optional<AccountEntity> findByUsername(String username);

   Optional<AccountEntity> findByEmail(String email);

   Iterable<AccountEntity> findAllByStatus(AccountStatus status);

   Iterable<AccountEntity> findAllByNameContaining(String name);

   Optional<AccountEntity> findByConfirmation(String confirmation);
}
