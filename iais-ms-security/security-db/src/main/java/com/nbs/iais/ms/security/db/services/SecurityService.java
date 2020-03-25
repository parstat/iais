package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.SigninException;
import com.nbs.iais.ms.common.exceptions.SignupException;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.utils.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {

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
     * @throws SignupException
     */
    public AccountDTO signup(final UUID id, final String username, final String password,
                             final String name, final String email, final AccountRole role) throws SignupException {

        if(accountRepository.findByUsername(username).isPresent()) {
            throw new SignupException(ExceptionCodes.USERNAME_TAKEN);
        }
        if(accountRepository.findByEmail(email).isPresent()) {
            throw new SignupException(ExceptionCodes.EMAIL_TAKEN);
        }

        final Account account = new AccountEntity();
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
     * @throws SigninException
     */
    public AccountDTO signin(final String username, final String password) throws SigninException {
        //check if username or email exists
        final Account account =  accountRepository.findByUsername(username).orElseGet(() ->
                accountRepository.findByEmail(username).orElseThrow(() ->
                        new SigninException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG)));

        //checking password
        if(passwordEncoder.matches(password, account.getPassword())) {
            return Translator.translate(account);
        }

        throw  new SigninException(ExceptionCodes.USERNAME_OR_PASSWORD_WRONG);
    }
}
