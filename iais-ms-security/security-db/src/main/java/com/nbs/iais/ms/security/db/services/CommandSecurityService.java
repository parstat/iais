package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.ChangePasswordException;
import com.nbs.iais.ms.common.exceptions.ConfirmationException;
import com.nbs.iais.ms.common.exceptions.SigninException;
import com.nbs.iais.ms.common.exceptions.SignupException;
import com.nbs.iais.ms.security.common.messageing.commands.*;
import com.nbs.iais.ms.security.common.messageing.queries.IsAuthenticatedQuery;
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

    /**
     * Method to change password (the user remembers the old password)
     * @param command to execute
     * @return command including true DTOBoolean if succeed
     * @throws ChangePasswordException when old password doesn't match or the requested user was not found
     */
    public ChangePasswordCommand changePassword(final ChangePasswordCommand command) throws ChangePasswordException {

        final AccountEntity account = accountRepository.findById(command.getAccountId()).orElseThrow(() ->
                new ChangePasswordException(ExceptionCodes.NOT_FOUND));
        if (passwordEncoder.matches(command.getOldPassword(), account.getPassword())) {
            resetFailedSignins(account);
            account.setPassword(passwordEncoder.encode(command.getNewPassword()));
            accountRepository.save(account);
            command.getEvent().setData(DTOBoolean.TRUE);
            return command;
        } else {
            throw new ChangePasswordException(ExceptionCodes.PASSWORD_NO_MACH);
        }
    }

    /**
     * Method to reset password when the user has the reset link that included the confirmation string
     * @param command to reset the password
     * @return the command including DTOBoolen true if succeed or false when not
     * @throws ChangePasswordException when the user is not found in db
     */
    public ResetPasswordCommand resetPassword(final ResetPasswordCommand command) throws ChangePasswordException {

        final AccountEntity accountEntity = accountRepository.findByConfirmation(command.getConfirmation())
                .orElseThrow(() -> new ChangePasswordException(ExceptionCodes.NOT_FOUND));

        if(accountEntity.getStatus() != AccountStatus.ACTIVE) {
            throw new ChangePasswordException(ExceptionCodes.SYSTEM_ERROR);
        }

        if(accountEntity.getConfirmationExpiration().isBefore(Instant.now())) {
            throw new ChangePasswordException(ExceptionCodes.CONFIRMATION_EXPIRED);
        }
        resetFailedSignins(accountEntity);
        accountEntity.setPassword(passwordEncoder.encode(command.getNewPassword()));
        accountEntity.setConfirmation(null);
        accountEntity.setConfirmationExpiration(Instant.EPOCH);
        accountRepository.save(accountEntity);
        command.getEvent().setData(DTOBoolean.TRUE);
        return command;
    }

    /**
     * Method to confirm email when creating new user
     * @param command command to confirm email
     * @return command including the AccountDTO
     * @throws ConfirmationException when confirmation is expire or not found
     */
    public ConfirmEmailCommand confirmEmail(final ConfirmEmailCommand command) throws ConfirmationException {

        final AccountEntity account = accountRepository.findByConfirmation(command.getConfirmation())
                .orElseThrow(() -> new ConfirmationException(ExceptionCodes.NOT_FOUND));
        if(account.getConfirmationExpiration().isBefore(Instant.now())) {
            throw new ConfirmationException(ExceptionCodes.CONFIRMATION_EXPIRED);
        }

        if(account.getStatus() != AccountStatus.UNCONFIRMED) {
            //this should not happen
            throw new ConfirmationException(ExceptionCodes.SYSTEM_ERROR);
        }
        resetFailedSignins(account);
        account.setConfirmation(null);
        account.setConfirmationExpiration(Instant.EPOCH);
        account.setStatus(AccountStatus.ACTIVE);
        Translator.translate(accountRepository.save(account)).ifPresent(command.getEvent()::setData);
        return command;
    }

    public SendResetLinkCommand sendResetLink(final SendResetLinkCommand command) throws ChangePasswordException {

        final AccountEntity account = accountRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new ChangePasswordException(ExceptionCodes.NOT_FOUND));

        return null;


        //TODO add an email client to send email with a link


    }

}
