package com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.statistical.standard.GetStatisticalStandardRead;



public class GetStatisticalStandardQuery extends AbstractQuery<GetStatisticalStandardRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
	private String localId;

	//FIXME add String version (localId + version) are unique
	
    private GetStatisticalStandardQuery() {
		super(new GetStatisticalStandardRead());
	}

	private GetStatisticalStandardQuery(final Long id) {
    	super(new GetStatisticalStandardRead());
    	this.id = id;
	}

	public static GetStatisticalStandardQuery create() {
	        return new GetStatisticalStandardQuery();
    }

	public static GetStatisticalStandardQuery create(final Long id) {
		return new GetStatisticalStandardQuery(id);
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

