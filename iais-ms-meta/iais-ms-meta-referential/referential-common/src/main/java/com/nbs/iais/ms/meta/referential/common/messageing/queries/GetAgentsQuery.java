package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetAgentsRead;

public class GetAgentsQuery extends AbstractQuery<GetAgentsRead> {

	private static final long serialVersionUID = 20320L;

    private GetAgentsQuery() {
		super(new GetAgentsRead());
	}

	public static GetAgentsQuery create() {
	        return new GetAgentsQuery();
	    }


}

