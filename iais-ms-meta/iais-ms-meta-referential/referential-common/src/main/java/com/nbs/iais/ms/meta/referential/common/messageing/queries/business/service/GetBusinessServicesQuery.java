package com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.business.service.GetBusinessServicesRead;

public class GetBusinessServicesQuery extends AbstractQuery<GetBusinessServicesRead> {

    private static final long serialVersionUID = 200L;

    private String localId;

    private String name;

    private GetBusinessServicesQuery() {
        super(new GetBusinessServicesRead());
    }

    private GetBusinessServicesQuery(final String localId, final String name, final Language language) {
        super(new GetBusinessServicesRead());
        this.localId = localId;
        this.name = name;
        setLanguage(language);
        setClosed(false);
    }

    public static GetBusinessServicesQuery create(final String localId, final String name, final Language language) {
        return new GetBusinessServicesQuery(localId, name, language);
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
