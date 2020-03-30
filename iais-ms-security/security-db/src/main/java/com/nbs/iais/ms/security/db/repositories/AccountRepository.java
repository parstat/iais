package com.nbs.iais.ms.security.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {

   Optional<AccountEntity> findByUsername(String username);

   Optional<AccountEntity> findByEmail(String email);

}
