package com.nbs.iais.ms.security.db.utils;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;

public class Translator {

    public static AccountDTO translate(final Account account) {

        final AccountDTO accountDTO = new AccountDTO(account.getId());

        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(account.getRole());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setName(account.getName());

        return accountDTO;

    }
}

