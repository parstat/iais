package com.nbs.iais.ms.security.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.security.common.messageing.reads.IsAuthenticatedRead;

public class IsAuthenticatedQuery extends AbstractQuery<IsAuthenticatedRead> {

    private static final long serialVersionUID = 200L;

    private String requestUri;


    private IsAuthenticatedQuery() {
        super(new IsAuthenticatedRead());
    }

    private IsAuthenticatedQuery(final String requestUri) {
        super(new IsAuthenticatedRead());
        this.requestUri = requestUri;
    }

    public static IsAuthenticatedQuery create(final String requestUri) {
        return new IsAuthenticatedQuery(requestUri);
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(final String requestUri) {
        this.requestUri = requestUri;
    }
}
