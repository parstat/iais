package com.nbs.iais.ms.security.common.messageing.queries;

import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.security.common.messageing.reads.GetAccountsRead;

public class GetAccountsQuery extends AbstractQuery<GetAccountsRead> {

    private static final long serialVersionUID = 200L;

    private AccountStatus status = AccountStatus.ACTIVE;

    private GetAccountsQuery() {
        super(new GetAccountsRead());
    }

    private GetAccountsQuery(final AccountStatus status) {
        super(new GetAccountsRead());
        this.status = status;
    }

    public static GetAccountsQuery create(final AccountStatus status) {
        return new GetAccountsQuery(status);
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(final AccountStatus status) {
        this.status = status;
    }
}

