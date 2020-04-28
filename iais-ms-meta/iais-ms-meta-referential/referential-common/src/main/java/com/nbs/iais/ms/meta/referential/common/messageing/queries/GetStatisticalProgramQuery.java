package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetStatisticalProgramRead;

public class GetStatisticalProgramQuery extends AbstractQuery<GetStatisticalProgramRead> {

	private static final long serialVersionUID = 20320L;

	private Long id;

	private String localId;

	private String version;
	
    private GetStatisticalProgramQuery() {
		super(new GetStatisticalProgramRead());
	}

	private GetStatisticalProgramQuery(final Long id, final Language language) {
    	super(new GetStatisticalProgramRead());
    	this.id = id;
    	setLanguage(language);
	}


	private GetStatisticalProgramQuery(final String localId, final String version, final Language language) {
    	super(new GetStatisticalProgramRead());
    	this.localId = localId;
    	this.version = version;
    	setLanguage(language);
	}

	public static GetStatisticalProgramQuery create() {
	        return new GetStatisticalProgramQuery();
    }


	public static GetStatisticalProgramQuery create(final String localId, final String version, final Language language) {
		return new GetStatisticalProgramQuery(localId, version, language);
	}

	public static GetStatisticalProgramQuery create(final Long id, final Language language) {
		return new GetStatisticalProgramQuery(id, language);
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}

