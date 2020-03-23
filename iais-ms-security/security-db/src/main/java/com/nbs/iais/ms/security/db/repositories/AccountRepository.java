package com.nbs.iais.ms.security.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

   Optional<Account> findByUsername(String username);

   Optional<Account> findByEmail(String email);

}
