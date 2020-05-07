package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.documentation.GetProcessDocumentationRead;



public class GetProcessDocumentationQuery extends AbstractQuery<GetProcessDocumentationRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
	private String localId;

	
	
    private GetProcessDocumentationQuery() {
		super(new GetProcessDocumentationRead());
	}

	private GetProcessDocumentationQuery(final Long id) {
    	super(new GetProcessDocumentationRead());
    	this.id = id;
	}

	public static GetProcessDocumentationQuery create() {
	        return new GetProcessDocumentationQuery();
    }

	public static GetProcessDocumentationQuery create(final Long id) {
		return new GetProcessDocumentationQuery(id);
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

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}
}

