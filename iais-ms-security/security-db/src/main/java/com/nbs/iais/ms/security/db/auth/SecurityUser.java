package com.nbs.iais.ms.security.db.auth;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.enums.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private String username;
    private String password;
    private boolean isLocked = false;
    private boolean isExpired = false;
    private boolean isEnabled = true;
    private List<GrantedAuthority> grantedAuthorities;

    public SecurityUser(final Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.isEnabled = account.getStatus() == AccountStatus.ACTIVE;
        this.isLocked = account.getStatus() == AccountStatus.LOCKED;
        this.isExpired = false;
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().toString()));
        this.grantedAuthorities = grantedAuthorities;
    }

    public SecurityUser(final String username, final String role) {
        this.username = username;
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
