package com.nbs.iais.ms.meta.referential.common.messageing.queries.business.service;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.business.service.GetBusinessServiceRead;

public class GetBusinessServiceQuery extends AbstractQuery<GetBusinessServiceRead> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private String localId;

    private String version;

    private GetBusinessServiceQuery() {
        super(new GetBusinessServiceRead());
    }

    private GetBusinessServiceQuery(final Long id, final Language language) {

        super(new GetBusinessServiceRead());
        this.id = id;
        setLanguage(language);
        setClosed(false);
    }

    private GetBusinessServiceQuery(final String localId, final String version, final Language language) {

        super(new GetBusinessServiceRead());
        this.version = version;
        this.localId = localId;
        setLanguage(language);
        setClosed(false);
    }

    public static GetBusinessServiceQuery create(final Long id, final Language language) {
        return new GetBusinessServiceQuery(id, language);
    }

    public static GetBusinessServiceQuery create(final String localId, final String version, final Language language) {
        return new GetBusinessServiceQuery(localId, version, language);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }
}
