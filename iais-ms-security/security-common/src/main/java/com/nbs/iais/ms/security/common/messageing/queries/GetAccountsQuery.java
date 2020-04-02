package com.nbs.iais.ms.security.common.messageing.queries;

import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.security.common.messageing.reads.GetAccountsRead;

import java.util.UUID;

public class GetAccountsQuery extends AbstractQuery<GetAccountsRead> {

    private static final long serialVersionUID = 200L;

    private String name;

    private AccountStatus status = AccountStatus.ACTIVE;


    private GetAccountsQuery() {
        super(new GetAccountsRead());
    }

    private GetAccountsQuery(final AccountStatus status) {
        super(new GetAccountsRead());
        this.status = status;
    }

    private GetAccountsQuery(final UUID requester, final AccountStatus status) {
        super(new GetAccountsRead());
        this.status = status;
        setAccountId(requester);
    }

    private GetAccountsQuery(final UUID requester, final AccountStatus status, final String name) {
        super(new GetAccountsRead());
        this.status = status;
        this.name = name;
        setAccountId(requester);

    }

    private GetAccountsQuery(final UUID requester, final String name) {
        super(new GetAccountsRead());
        setAccountId(requester);
        this.name = name;
    }

    public static GetAccountsQuery create(final AccountStatus status) {
        return new GetAccountsQuery(status);
    }

    public static GetAccountsQuery create(final UUID requester, final AccountStatus status) {
        return new GetAccountsQuery(requester, status);
    }

    public static GetAccountsQuery create(final UUID requester, final AccountStatus status, final String name) {
        return new GetAccountsQuery(requester, status, name);
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(final AccountStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

