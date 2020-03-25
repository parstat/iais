package com.nbs.iais.ms.common.db.domains.interfaces.security;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;

public interface Account extends DomainObject {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getName();

    void setName(String name);

    AccountStatus getStatus();

    void setStatus(AccountStatus status);

    String getEmail();

    void setEmail(String email);

    AccountRole getRole();

    void setRole(AccountRole role);

    int getSigninFails();

    void setSigninFails(int signinFails);

}
