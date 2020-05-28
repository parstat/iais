package com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.statistical.standard.GetStatisticalStandardRead;



public class GetStatisticalStandardQuery extends AbstractQuery<GetStatisticalStandardRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
	private String localId;

	private String version;
	
    private GetStatisticalStandardQuery() {
		super(new GetStatisticalStandardRead());
	}

	private GetStatisticalStandardQuery(final Long id, final Language language) {
    	super(new GetStatisticalStandardRead());
    	this.id = id;
    	setLanguage(language);
    	setClosed(false);
	}

	private GetStatisticalStandardQuery(final String localId, final String version, final Language language) {
		super(new GetStatisticalStandardRead());
		this.localId = localId;
		this.version = version;
		setLanguage(language);
		setClosed(false);
	}

	public static GetStatisticalStandardQuery create() {
	        return new GetStatisticalStandardQuery();
    }

	public static GetStatisticalStandardQuery create(final Long id, final Language language) {
		return new GetStatisticalStandardQuery(id, language);
	}

	public static GetStatisticalStandardQuery create(final String localId, final String version, final Language language) {
    	return new GetStatisticalStandardQuery(localId, version, language);
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

	public void setVersion(final String version) {
		this.version = version;
	}
}

