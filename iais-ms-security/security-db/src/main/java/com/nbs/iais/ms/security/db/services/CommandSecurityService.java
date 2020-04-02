package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.SigninException;
import com.nbs.iais.ms.common.exceptions.SignupException;
import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.utils.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommandSecurityService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method to do signup
     * @param id uuid of the user
     * @param username username
     * @param password password
     * @param name name
     * @param email email
     * @param role role
     * @return AccountDTO
     * @throws SignupException known problems for signups (email or username exists)
     */
    public AccountDTO signup(final UUID id, final String username, final String password,
                             final String name, final String email, final AccountRole role) throws SignupException {

        if(accountRepository.findByUsername(username).isPresent()) {
            throw new SignupException(ExceptionCodes.USERNAME_TAKEN);
        }
        if(accountRepository.findByEmail(email).isPresent()) {
            throw new SignupException(ExceptionCodes.EMAIL_TAKEN);
        }

        final AccountEntity account = new AccountEntity();
        account.setId(id);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setName(name);
        account.setSigninFails(0);
        account.setEmail(email);
        account.setRole(role);
        account.setStatus(AccountStatus.ACTIVE);

        try {
            return Translator.translate(accountRepository.save(account));

        } catch (Exception ex) {
            throw new SignupException("Internal Server Error", ExceptionCodes.SYSTEM_ERROR);
        }

    }

    /**
     * Methot to do signin
     * @param username username
     * @param password password
     * @return AccountDTO
     * @throws SigninException known problems with signin (username or password wrong)
     */
    public AccountDTO signin(final String username, final String password) throws SigninException {
        //check if username or email exists
        final AccountEntity account =  accountRepository.findByUsername(username).orElseGet(() ->
                accountRepository.findByEmail(username).orElseThrow(() ->
                        new SigninException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG)));

        //checking password
        if(passwordEncoder.matches(password, account.getPassword())) {
            return Translator.translate(account);
        }

        throw  new SigninException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG);
    }

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

        //check password
        if(passwordEncoder.matches(signinCommand.getPassword(), account.getPassword())) {
            signinCommand.getEvent().setData(Translator.translate(account));
            return signinCommand;
        }
        throw  new SigninException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG);
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

        final AccountEntity account = new AccountEntity();
        account.setId(signupCommand.getAccountId());
        account.setUsername(signupCommand.getUsername());
        account.setPassword(passwordEncoder.encode(signupCommand.getPassword()));
        account.setName(signupCommand.getName());
        account.setSigninFails(0);
        account.setEmail(signupCommand.getEmail());
        account.setRole(signupCommand.getRole());
        account.setStatus(AccountStatus.ACTIVE);

        try {
            signupCommand.getEvent().setData(Translator.translate(accountRepository.save(account)));
            return signupCommand;

        } catch (Exception ex) {
            throw new SignupException("Internal Server Error", ExceptionCodes.SYSTEM_ERROR);
        }
    }

}
