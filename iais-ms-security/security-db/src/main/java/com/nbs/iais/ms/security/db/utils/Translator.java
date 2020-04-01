package com.nbs.iais.ms.security.db.utils;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.security.db.domains.AccountEntity;

public class Translator {

    public static AccountDTO translate(final AccountEntity account) {

        final AccountDTO accountDTO = new AccountDTO(account.getId());

        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(account.getRole());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setName(account.getName());

        return accountDTO;

    }

    public static DTOList<AccountDTO> translate(final Iterable<AccountEntity> accounts) {

        final DTOList<AccountDTO> accountDTOS = DTOList.empty(AccountDTO.class);
        accounts.forEach(accountEntity -> accountDTOS.add(translate(accountEntity)));

        return accountDTOS;
    }
}

