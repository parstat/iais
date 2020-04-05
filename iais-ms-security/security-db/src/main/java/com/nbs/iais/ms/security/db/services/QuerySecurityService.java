package com.nbs.iais.ms.security.db.services;

import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.SigninException;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import com.nbs.iais.ms.security.common.messageing.queries.IsAuthenticatedQuery;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuerySecurityService {

    @Autowired
    private AccountRepository accountRepository;


    /**
     * Service method to get accounts using different filters
     * @param query the request query
     * @return the same query including the query result
     */
    public GetAccountsQuery getAccounts(final GetAccountsQuery query) {

        if(StringTools.isNotEmpty(query.getName())) {
            final Iterable<AccountEntity> accountEntities = accountRepository.findAllByNameContaining(query.getName());
            Translator.translate(accountEntities).ifPresent(query.getRead()::setData);
            return query;
        }
        if(query.getStatus() != AccountStatus.ACTIVE && query.getAccountRole() == AccountRole.USER) {
            //normal user can not query for non active accounts
            query.getRead().setData(DTOList.empty(AccountDTO.class));
            return query;
        }

        final Iterable<AccountEntity> accountEntities = accountRepository.findAllByStatus(query.getStatus());
        Translator.translate(accountEntities).ifPresent(query.getRead()::setData);
        return query;
    }

    /**
     * Sometimes is important to do a double check if the jwt user is still valid
     * @param query request query
     * @return the same query including the boolean query result (true or false)
     * @throws SigninException it throws an exception if the user does not exist in db
     */
    public IsAuthenticatedQuery authenticate(final IsAuthenticatedQuery query) throws SigninException {

        final AccountEntity account = accountRepository.findById(query.getAccountId()).orElseThrow(() ->
                new SigninException(ExceptionCodes.NOT_FOUND));

        if(account.getStatus() != AccountStatus.ACTIVE) {
            query.getRead().setData(DTOBoolean.TRUE);
            return query;
        }

        query.getRead().setData(DTOBoolean.FALSE);
        return query;
    }


}
