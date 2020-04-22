package com.nbs.iais.ms.security.db.service;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.services.CommandSecurityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandSecurityServiceTest extends ServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CommandSecurityService commandSecurityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        when(passwordEncoder.encode(any())).thenReturn("password");
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
    }

    @Test
    public void signin() {

        //Setup
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setName("Name Surname");
        accountEntity.setUsername("username");
        accountEntity.setPassword(passwordEncoder.encode("password"));
        accountEntity.setEmail("name.surname@email.com");
        accountEntity.setRole(AccountRole.USER);
        accountEntity.setStatus(AccountStatus.ACTIVE);
        accountEntity.setSigninFails(0);

        final SigninCommand signinCommand = SigninCommand.create("username", "password");

        when(accountRepository.findByUsername(eq(signinCommand.getUsername()))).thenReturn(Optional.of(accountEntity));
        when(accountRepository.save(any())).thenReturn(accountEntity);

        //call
        commandSecurityService.signin(signinCommand);

        //verify
        verify(accountRepository).findByUsername(eq(signinCommand.getUsername()));
        verify(passwordEncoder).matches(eq(signinCommand.getPassword()),
                ArgumentMatchers.eq(accountEntity.getPassword()));
    }

    @Test
    public void signup() {

        //Setup
        final SignupCommand command = SignupCommand.create("username", "password",
                "name.surname@email.com", "Name Surname", AccountRole.USER);

        when(accountRepository.save(any(AccountEntity.class))).thenAnswer(
                (Answer<AccountEntity>) invocation -> {
                    Object[] args = invocation.getArguments();
                    final AccountEntity account = (AccountEntity) args[0];
                    account.setId(1L);
                    return account;
                });

        //Call
        commandSecurityService.signup(command);

        //Verify
        ArgumentCaptor<AccountEntity> captor = ArgumentCaptor.forClass(AccountEntity.class);
        verify(accountRepository).findByUsername(eq("username"));
        verify(accountRepository).findByEmail(eq("name.surname@email.com"));
        verify(accountRepository).save(captor.capture());
        assertThat(captor.getValue().getId()).isNotNull();
    }
}
