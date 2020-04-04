package com.nbs.iais.ms.security.db.utils;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.db.domains.AccountEntity;

import java.time.Instant;

public class CommandTranslator {

    public static AccountEntity translate(final SignupCommand command) {
        final AccountEntity account = new AccountEntity();
        account.setId(command.getAccountId());
        account.setUsername(command.getUsername());
        account.setPassword(command.getPassword());
        account.setSigninFails(0);
        account.setEmail(command.getEmail());
        account.setRole(AccountRole.USER);
        account.setStatus(AccountStatus.ACTIVE);
        account.setName(command.getName());
        account.setConfirmation(null);
        account.setLockExpiration(Instant.EPOCH);
        return account;
    }
}

