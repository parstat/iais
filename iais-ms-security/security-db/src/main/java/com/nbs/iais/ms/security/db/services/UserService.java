package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.security.db.auth.SecurityUser;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final Account account = accountRepository.findByUsername(s).orElseGet(() ->
            accountRepository.findByEmail(s).orElseThrow(() -> {
                throw new RuntimeException("user not found");
            }));
        return new SecurityUser(account);
    }
}
