package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.output.specification;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.output.specification.GetProcessOutputSpecificationRead;



public class GetProcessOutputSpecificationQuery extends AbstractQuery<GetProcessOutputSpecificationRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
    private GetProcessOutputSpecificationQuery() {
		super(new GetProcessOutputSpecificationRead());
	}

	private GetProcessOutputSpecificationQuery(final Long id) {
    	super(new GetProcessOutputSpecificationRead());
    	this.id = id;
	}

	public static GetProcessOutputSpecificationQuery create() {
	        return new GetProcessOutputSpecificationQuery();
    }

	public static GetProcessOutputSpecificationQuery create(final Long id) {
		return new GetProcessOutputSpecificationQuery(id);
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

