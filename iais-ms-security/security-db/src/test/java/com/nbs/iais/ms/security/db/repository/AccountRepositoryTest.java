package com.nbs.iais.ms.security.db.repository;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryTest extends RepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void save() {
        final Account account = saveAccount("username", "username@email.com");
        Assertions.assertNotNull(account);
        Assertions.assertNotNull(account.getId());
    }

    @Test
    public void getById()  {
        final Account save = saveAccount("username2", "username2@email.com");
        Assertions.assertTrue(accountRepository.findById(save.getId()).isPresent());
    }

    @Test
    public void getByUsername() {
        final Account save = saveAccount("username3", "username3@email.com");
        Assertions.assertTrue(accountRepository.findByUsername(save.getUsername()).isPresent());
    }

    private AccountEntity saveAccount(final String username, final String email) {
        final AccountEntity account = new AccountEntity();
        account.setName("Name Surname");
        account.setUsername(username);
        account.setStatus(AccountStatus.ACTIVE);
        account.setRole(AccountRole.USER);
        account.setEmail(email);
        account.setSigninFails(0);
        account.setPassword("password");
        return accountRepository.save(account);
    }

    @Test
    public void getAccounts() {
        final Account save = saveAccount("username4", "username4@email.com");

        Iterable<AccountEntity> accountEntities = accountRepository.findAllByStatus(AccountStatus.ACTIVE);
        Assertions.assertTrue(accountEntities.iterator().hasNext());

    }

    @Test
    public void getAccountsByName() {
        final Account save = saveAccount("username5", "username5@email.com");
        Iterable<AccountEntity> accountEntities = accountRepository.findAllByNameContaining("name");
        Assertions.assertTrue(accountEntities.iterator().hasNext());
    }
}
