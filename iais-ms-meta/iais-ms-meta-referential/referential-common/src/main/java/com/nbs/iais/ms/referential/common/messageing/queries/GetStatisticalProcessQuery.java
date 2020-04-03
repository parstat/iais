package com.nbs.iais.ms.referential.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.referential.common.messageing.reads.GetStatisticalProcessRead;

public class GetStatisticalProcessQuery extends AbstractQuery<GetStatisticalProcessRead> {

	private static final long serialVersionUID = 2343430L;

	private Long id;

	private GetStatisticalProcessQuery(Long id) {
		super(new GetStatisticalProcessRead());
		this.id = id;
	}

	private GetStatisticalProcessQuery() {
		super(new GetStatisticalProcessRead());
	}

	
    public static GetStatisticalProcessQuery create(final Long id) {
        return new GetStatisticalProcessQuery(id);
    }

    public static GetStatisticalProcessQuery create() {
        return new GetStatisticalProcessQuery();
    }

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
