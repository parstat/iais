package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.document.GetProcessDocumentRead;



public class GetProcessDocumentQuery extends AbstractQuery<GetProcessDocumentRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
    private GetProcessDocumentQuery() {
		super(new GetProcessDocumentRead());
	}

	private GetProcessDocumentQuery(final Long id) {
    	super(new GetProcessDocumentRead());
    	this.id = id;
	}

	public static GetProcessDocumentQuery create() {
	        return new GetProcessDocumentQuery();
    }

	public static GetProcessDocumentQuery create(final Long id) {
		return new GetProcessDocumentQuery(id);
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

