package com.nbs.iais.ms.common.utils;

import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;

import java.util.UUID;

public class DTOMocks {

    public static AccountDTO account() {
        AccountDTO accountDTO = new AccountDTO(UUID.randomUUID());
        accountDTO.setName("Name Surname");
        accountDTO.setRole(AccountRole.USER);
        accountDTO.setUsername("username");
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setEmail("name.surname@email.com");
        return accountDTO;
    }
}
