package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.SigninException;
import com.nbs.iais.ms.common.exceptions.SignupException;
import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.utils.CommandTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommandSecurityService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method to signin using CQRS
     * @param signinCommand the command to signin (CQRS)
     * @return signinCommand including signinEvent
     * @throws SignupException known problems with signin (username or password wrong)
     */
    public SigninCommand signin(final SigninCommand signinCommand) throws SigninException {
        final AccountEntity account =  accountRepository.findByUsername(signinCommand.getUsername()).orElseGet(() ->
                accountRepository.findByEmail(signinCommand.getUsername()).orElseThrow(() ->
                        new SigninException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG)));

        checkAccount(account, signinCommand.getPassword());

        accountRepository.save(account);
        Translator.translate(account).ifPresent(signinCommand.getEvent()::setData);
        return signinCommand;
    }

    /**
     * If this method does not throw any exception the account is ok
     * @param account the account to check
     * @param rawPassword the password to mach
     * @throws SignupException exception when signup
     */
    private void checkAccount(final AccountEntity account, final String rawPassword) throws SignupException {
        if(account.getStatus() == AccountStatus.TERMINATED) {
            throw new SignupException(ExceptionCodes.USER_DELETED);
        }

        if(account.getStatus() == AccountStatus.LOCKED && account.getLockExpiration().isBefore(Instant.now())) {
            throw new SignupException(ExceptionCodes.USER_LOCKED);
        }

        if(passwordEncoder.matches(account.getPassword(), rawPassword)) {
            resetFailedSignins(account);
        } else {
            account.setSigninFails(account.getSigninFails() + 1);
            if(account.getSigninFails() > 5) {
                lockAccount(account);
            }
            throw new SignupException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG);
        }
    }
    private void lockAccount(final AccountEntity accountEntity) {
        accountEntity.setStatus(AccountStatus.LOCKED);
        accountEntity.setLockExpiration(Instant.now().plusSeconds(3600));
    }

    private void resetFailedSignins(final AccountEntity account) {
        account.setSigninFails(0);
        account.setLockExpiration(Instant.EPOCH);
    }

    /**
     * Method to do signup using CQRS
     * @param signupCommand the command for sign up (CQRS)
     * @return signupCommand including SignupEvent
     * @throws SignupException  known problems for signups (email or username exists)
     */
    public SignupCommand signup(final SignupCommand signupCommand) throws SignupException {
        if(accountRepository.findByUsername(signupCommand.getUsername()).isPresent()) {
            throw new SignupException(ExceptionCodes.USERNAME_TAKEN);
        }
        if(accountRepository.findByEmail(signupCommand.getEmail()).isPresent()) {
            throw new SignupException(ExceptionCodes.EMAIL_TAKEN);
        }

        final AccountEntity account = CommandTranslator.translate(signupCommand);

        try {
            Translator.translate(accountRepository.save(account)).ifPresent(signupCommand.getEvent()::setData);
            return signupCommand;

        } catch (Exception ex) {
            throw new SignupException("Internal Server Error", ExceptionCodes.SYSTEM_ERROR);
        }
    }

}
