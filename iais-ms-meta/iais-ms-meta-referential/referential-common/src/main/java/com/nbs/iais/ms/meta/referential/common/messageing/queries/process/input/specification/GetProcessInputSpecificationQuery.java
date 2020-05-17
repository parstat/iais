package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.input.specification;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.input.specification.GetProcessInputSpecificationRead;



public class GetProcessInputSpecificationQuery extends AbstractQuery<GetProcessInputSpecificationRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
    private GetProcessInputSpecificationQuery() {
		super(new GetProcessInputSpecificationRead());
	}

	private GetProcessInputSpecificationQuery(final Long id) {
    	super(new GetProcessInputSpecificationRead());
    	this.id = id;
	}

	public static GetProcessInputSpecificationQuery create() {
	        return new GetProcessInputSpecificationQuery();
    }

	public static GetProcessInputSpecificationQuery create(final Long id) {
		return new GetProcessInputSpecificationQuery(id);
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

