package com.nbs.iais.ms.security.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.security.common.messageing.reads.GetAccountRead;

public class GetAccountQuery extends AbstractQuery<GetAccountRead> {

    private static final long serialVersionUID = 200L;

    private Long id;
    private String username;

    private GetAccountQuery() {
        super(new GetAccountRead());
    }

    private GetAccountQuery(final Long id) {
        super(new GetAccountRead());
        this.id = id;
    }

    private GetAccountQuery(final String username) {
        super(new GetAccountRead());
        this.username = username;
    }

    public static GetAccountQuery create(final Long id) {
        return new GetAccountQuery(id);
    }

    public static GetAccountQuery create(final String username) {
        return new GetAccountQuery(username);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
