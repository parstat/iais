package com.nbs.iais.ms.security.db.service;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.services.SecurityService;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class SecurityServiceTest extends ServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private SecurityService securityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void signin() {

        //Setup
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(UUID.randomUUID());
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
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        //call
        securityService.signin(signinCommand);

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
                    return (AccountEntity) args[0];
                });

        //Call
        securityService.signup(command);

        //Verify
        ArgumentCaptor<AccountEntity> captor = ArgumentCaptor.forClass(AccountEntity.class);
        verify(accountRepository).findByUsername(eq("username"));
        verify(accountRepository).findByEmail(eq("name.surname@email.com"));
        verify(accountRepository).save(captor.capture());
        assertThat(captor.getValue().getId()).isNotNull();
    }
}
