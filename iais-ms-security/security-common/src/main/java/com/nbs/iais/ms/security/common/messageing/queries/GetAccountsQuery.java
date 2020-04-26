package com.nbs.iais.ms.security.common.messageing.queries;

import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.security.common.messageing.reads.GetAccountsRead;

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

    private GetAccountsQuery(final AccountStatus status, final String name) {
        super(new GetAccountsRead());
        this.status = status;
        this.name = name;
    }

    private GetAccountsQuery(final String jwt, final AccountStatus status) {
        super(new GetAccountsRead());
        this.status = status;
        setJwt(jwt);
    }

    private GetAccountsQuery(final String jwt, final AccountStatus status, final String name) {
        super(new GetAccountsRead());
        this.status = status;
        this.name = name;
        setJwt(jwt);

    }

    private GetAccountsQuery(final String jwt, final String name) {
        super(new GetAccountsRead());
        setJwt(jwt);
        this.name = name;
    }

    public static GetAccountsQuery create(final AccountStatus status) {
        return new GetAccountsQuery(status);
    }

    public static GetAccountsQuery create(final AccountStatus status, final String name) {
        return new GetAccountsQuery(status, name);
    }

    public static GetAccountsQuery create(final String jwt, final AccountStatus status) {
        return new GetAccountsQuery(jwt, status);
    }

    public static GetAccountsQuery create(final String jwt, final AccountStatus status, final String name) {
        return new GetAccountsQuery(jwt, status, name);
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

