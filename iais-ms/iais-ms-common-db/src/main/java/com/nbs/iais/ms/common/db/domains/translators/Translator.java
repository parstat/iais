package com.nbs.iais.ms.common.db.domains.translators;

import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;

import javax.persistence.GeneratedValue;
import java.util.Optional;

public class Translator {


    private String rootUrl;

    public static <A extends Account> Optional<AccountDTO> translate(final A account) {

        if(account == null) {
            return Optional.empty();
        }
        final AccountDTO accountDTO = new AccountDTO(account.getId());

        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(account.getRole());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setName(account.getName());
        accountDTO.setLink("/accounts/" + account.getId().toString());
        return Optional.of(accountDTO);

    }


    public static <A extends Account> Optional<DTOList<AccountDTO>> translate(final Iterable<A> accounts) {

        if(!accounts.iterator().hasNext()) {
            return Optional.empty();
        }
        final DTOList<AccountDTO> accountDTOS = DTOList.empty(AccountDTO.class);

        accounts.forEach(accountEntity -> translate(accountEntity)
                .ifPresent(accountDTOS::add));

        return Optional.of(accountDTOS);
    }
}
