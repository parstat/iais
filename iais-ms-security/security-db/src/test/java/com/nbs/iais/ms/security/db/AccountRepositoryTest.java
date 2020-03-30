package com.nbs.iais.ms.security.db;

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
        final Account account = saveAccount();
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getId());
    }

    @Test
    public void get()  {
        final Account save = saveAccount();
        Assert.assertTrue(accountRepository.findById(save.getId()).isPresent());
    }

    private AccountEntity saveAccount() {
        final AccountEntity account = new AccountEntity();
        account.setId(UUID.randomUUID());
        account.setName("Name Surname");
        account.setUsername("username");
        account.setStatus(AccountStatus.ACTIVE);
        account.setRole(AccountRole.USER);
        account.setEmail("name.surname@email.com");
        account.setSigninFails(0);
        account.setPassword("password");
        return accountRepository.save(account);
    }
}
