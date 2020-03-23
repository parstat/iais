package com.nbs.iais.ms.security.db.repositories;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {

}
