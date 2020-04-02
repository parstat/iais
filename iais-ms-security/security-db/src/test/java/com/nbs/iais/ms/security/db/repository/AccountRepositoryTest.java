package com.nbs.iais.ms.security.db.repository;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AccountRepositoryTest extends RepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void save() {
        final Account account = saveAccount("username", "username@email.com");
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getId());
    }

    @Test
    public void getById()  {
        final Account save = saveAccount("username2", "username2@email.com");
        Assert.assertTrue(accountRepository.findById(save.getId()).isPresent());
    }

    @Test
    public void getByUsername() {
        final Account save = saveAccount("username3", "username3@email.com");
        Assert.assertTrue(accountRepository.findByUsername(save.getUsername()).isPresent());
    }

    private AccountEntity saveAccount(final String username, final String email) {
        final AccountEntity account = new AccountEntity();
        account.setId(UUID.randomUUID());
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
        Assert.assertTrue(accountEntities.iterator().hasNext());

    }

    @Test
    public void getAccountsByName() {
        final Account save = saveAccount("username5", "username5@email.com");
        Iterable<AccountEntity> accountEntities = accountRepository.findAllByNameContaining("name");
        Assert.assertTrue(accountEntities.iterator().hasNext());
    }
}
