package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.method.GetProcessMethodsRead;

public class GetProcessMethodsQuery extends AbstractQuery<GetProcessMethodsRead> {

    private static final long serialVersionUID = 2004L;

    private String name;

    private GetProcessMethodsQuery() {
        super(new GetProcessMethodsRead());
    }

    private GetProcessMethodsQuery(final String name, final Language language) {
        super(new GetProcessMethodsRead());
        this.name = name;
        setLanguage(language);
        setClosed(false);
    }

    public static GetProcessMethodsQuery crate(final String name, final Language language) {
        return new GetProcessMethodsQuery(name, language);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
