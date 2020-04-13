package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetStatisticalProgramsRead;

public class GetStatisticalProgramsQuery extends AbstractQuery<GetStatisticalProgramsRead> {

	private static final long serialVersionUID = 20320L;

    private GetStatisticalProgramsQuery() {
		super(new GetStatisticalProgramsRead());
	}

	public static GetStatisticalProgramsQuery create() {
	        return new GetStatisticalProgramsQuery();
	    }


}

