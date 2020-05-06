package com.nbs.iais.ms.security.db.services;

import com.auth0.jwt.JWT;
import com.nbs.iais.ms.common.db.domains.translators.Translator;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.AccountNotFoundException;
import com.nbs.iais.ms.common.exceptions.SigninException;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountQuery;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import com.nbs.iais.ms.security.common.messageing.queries.IsAuthenticatedQuery;
import com.nbs.iais.ms.security.db.domains.AccountEntity;
import com.nbs.iais.ms.security.db.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class QuerySecurityService {

    private static Logger LOG = LoggerFactory.getLogger(QuerySecurityService.class);

    @Autowired
    private AccountRepository accountRepository;


    /**
     * Service method to get accounts using different filters
     * @param query the request query
     * @return the same query including the query result
     */
    public GetAccountsQuery getAccounts(final GetAccountsQuery query) {

        final List<AccountStatus> statuses = getStatuses(query);
        if(StringTools.isNotEmpty(query.getName())) {
            Translator.translate(accountRepository.findAllByNameContainingAndStatusIn(query.getName(), statuses))
                    .ifPresent(query.getRead()::setData);
            LOG.debug("Returning GetAccountsQuery: {}", query.toString());
            return query;
        }

        Translator.translate(accountRepository.findAllByStatus(statuses.get(0)))
                .ifPresent(query.getRead()::setData);

        return query;
    }

    private List<AccountStatus> getStatuses(final GetAccountsQuery query) {
        final AccountRole role = AccountRole.valueOf(JWT.decode(query.getJwt()).getClaim("role").asString());
        if(query.isClosed()) {
            if(role == AccountRole.ROOT || role == AccountRole.ADMIN) {
                if(StringTools.isNotEmpty(query.getName())) {
                    return Arrays.asList(AccountStatus.ACTIVE, AccountStatus.UNCONFIRMED, AccountStatus.TERMINATED,
                            AccountStatus.LOCKED);
                }
                if(query.getStatus() != null) {
                    return Collections.singletonList(query.getStatus());
                }
            }
        }
        return Collections.singletonList(AccountStatus.ACTIVE);
    }

    /**
     * Sometimes is important to do a double check if the jwt user is still valid
     * @param query request query
     * @return the same query including the boolean query result (true or false)
     * @throws SigninException it throws an exception if the user does not exist in db
     */
    public IsAuthenticatedQuery authenticate(final IsAuthenticatedQuery query) throws SigninException {

        final Long accountId = JWT.decode(query.getJwt()).getClaim("user").asLong();
        final AccountEntity account = accountRepository.findById(accountId).orElseThrow(() ->
                new SigninException(ExceptionCodes.NOT_FOUND));

        if(account.getStatus() != AccountStatus.ACTIVE) {
            query.getRead().setData(DTOBoolean.TRUE);
            return query;
        }

        query.getRead().setData(DTOBoolean.FALSE);
        return query;
    }

    /**
     * Method to get the account from username or id (username can be also the email)
     * @param query to request the user
     * @return the query including accountDTO in read
     * @throws AccountNotFoundException when user can not be fund or username, email or id was not provided
     */
    public GetAccountQuery getAccount(final GetAccountQuery query) throws AccountNotFoundException {
        final AccountEntity account;
        if(query.getId() != null) {
            account = accountRepository.findById(query.getId())
                    .orElseThrow(() -> new AccountNotFoundException(ExceptionCodes.NOT_FOUND));
                Translator.translate(account).ifPresent(query.getRead()::setData);
                return query;
        }
        if(StringTools.isNotEmpty(query.getUsername())) {
            account = accountRepository.findByUsername(query.getUsername()).orElse(
                    accountRepository.findByEmail(query.getUsername())
                            .orElseThrow(() -> new AccountNotFoundException(ExceptionCodes.NOT_FOUND)));
            Translator.translate(account).ifPresent(query.getRead()::setData);
            return query;
        }
        throw  new AccountNotFoundException(ExceptionCodes.USERNAME_OR_ID_REQUIRED);
    }



}
