package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetBusinessFunctionsRead;

public class GetBusinessFunctionsQuery extends AbstractQuery<GetBusinessFunctionsRead> {

    private static final long serialVersionUID = 200L;

    /**
     * Search for GSBPM sub-phases by name
     */
    private String name;

    /**
     * Get all GSBPM sub-phases of a phase
     */
    private int phase = 0;

    /**
     * Get all GSBPM sub-phases by version
     * default 5.1
     */
    private String version = "5.1";


    private GetBusinessFunctionsQuery() {
        super(new GetBusinessFunctionsRead());
    }

    private GetBusinessFunctionsQuery(final String name, final int phase, final String version, final Language language) {

        super(new GetBusinessFunctionsRead());

        if(StringTools.isNotEmpty(name)) {
            this.name = name;
        }

        if(phase >= 1 && phase <= 8) {
            this.phase = phase;
        }

        if(StringTools.isNotEmpty(version)) {
            this.version = version;
        }

        setLanguage(language);
    }

    public static GetBusinessFunctionsQuery create(final String name, final int phase, final String version, final Language language) {
        return new GetBusinessFunctionsQuery(name, phase, version, language);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(final int phase) {
        this.phase = phase;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }
}
