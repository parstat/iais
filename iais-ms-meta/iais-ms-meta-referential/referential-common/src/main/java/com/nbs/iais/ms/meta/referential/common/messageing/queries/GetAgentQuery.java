package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetAgentRead;


public class GetAgentQuery extends AbstractQuery<GetAgentRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
    private GetAgentQuery() {
		super(new GetAgentRead());
	}

	private GetAgentQuery(final Long id) {
    	super(new GetAgentRead());
    	this.id = id;
	}

	public static GetAgentQuery create() {
	        return new GetAgentQuery();
    }

	public static GetAgentQuery create(final Long id) {
		return new GetAgentQuery(id);
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

