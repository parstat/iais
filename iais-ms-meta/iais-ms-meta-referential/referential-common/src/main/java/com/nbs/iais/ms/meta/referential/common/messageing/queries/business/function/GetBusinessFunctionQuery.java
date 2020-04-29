package com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.business.function.GetBusinessFunctionRead;

public class GetBusinessFunctionQuery extends AbstractQuery<GetBusinessFunctionRead> {

    private static final long serialVersionUID = 200L;

    /**
     * Getting Business function by id
     */
    private Long id;

    /**
     * if id is not provided than the business function can be retrieved from
     * sub-phase number and version
     */
    private String localId;

    /**
     * default version is '5.1'
     */
    private String version = "5.1";

    private GetBusinessFunctionQuery() {
        super(new GetBusinessFunctionRead());
    }

    private GetBusinessFunctionQuery(final Long id, final Language language) {
        super(new GetBusinessFunctionRead());
        this.id = id;
        setLanguage(language);
    }

    private GetBusinessFunctionQuery(final String localId, final Language language) {
        super(new GetBusinessFunctionRead());
        this.localId = localId;
        setLanguage(language);
    }

    private GetBusinessFunctionQuery(final String localId, final String version, final Language language) {
        super(new GetBusinessFunctionRead());
        this.localId = localId;
        this.version = version;
        setLanguage(language);
    }

    public static GetBusinessFunctionQuery create(final Long id, final Language language) {
        return new GetBusinessFunctionQuery(id, language);
    }

    public static GetBusinessFunctionQuery create(final String localId, final Language language) {
        return new GetBusinessFunctionQuery(localId, language);
    }

    public static GetBusinessFunctionQuery create(final String localId, final String version, final Language language) {
        return new GetBusinessFunctionQuery(localId, version, language);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
