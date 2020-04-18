package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetStatisticalProgramRead;

public class GetStatisticalProgramQuery extends AbstractQuery<GetStatisticalProgramRead> {

	private static final long serialVersionUID = 20320L;

	private Long id;
	
    private GetStatisticalProgramQuery() {
		super(new GetStatisticalProgramRead());
	}

	private GetStatisticalProgramQuery(final Long id) {
    	super(new GetStatisticalProgramRead());
    	this.id = id;
	}

	public static GetStatisticalProgramQuery create() {
	        return new GetStatisticalProgramQuery();
    }

	public static GetStatisticalProgramQuery create(final Long id) {
		return new GetStatisticalProgramQuery(id);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

