package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.method.GetProcessMethodRead;

public class GetProcessMethodQuery extends AbstractQuery<GetProcessMethodRead> {

    private static final long serialVersionUID = 210320L;

    private Long id;

    private GetProcessMethodQuery() {
        super(new GetProcessMethodRead());
    }

    private GetProcessMethodQuery(final Long id, final Language language) {
        super(new GetProcessMethodRead());
        this.id = id;
        setLanguage(language);
        setClosed(false);
    }

    public static GetProcessMethodQuery create(final Long id, final Language language) {
        return new GetProcessMethodQuery(id, language);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
