package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import com.nbs.iais.ms.security.db.utils.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuerySecurityService {

    @Autowired
    private AccountRepository accountRepository;


    public GetAccountsQuery getAccounts(final GetAccountsQuery query) {

        if(StringTools.isNotEmpty(query.getName())) {
            final Iterable<AccountEntity> accountEntities = accountRepository.findAllByNameContaining(query.getName());
            query.getRead().setData(Translator.translate(accountEntities));
            return query;
        }
        if(query.getStatus() != AccountStatus.ACTIVE && query.getAccountRole() == AccountRole.USER) {
            //normal user can not query for non active accounts
            query.getRead().setData(DTOList.empty(AccountDTO.class));
            return query;
        }

        final Iterable<AccountEntity> accountEntities = accountRepository.findAllByStatus(query.getStatus());
        query.getRead().setData(Translator.translate(accountEntities));
        return query;
    }
}
