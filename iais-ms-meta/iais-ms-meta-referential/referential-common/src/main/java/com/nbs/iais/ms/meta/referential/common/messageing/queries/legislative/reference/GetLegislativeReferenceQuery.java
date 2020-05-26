package com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.legislative.reference.GetLegislativeReferenceRead;


public class GetLegislativeReferenceQuery extends AbstractQuery<GetLegislativeReferenceRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
	private String localId;
	
    private GetLegislativeReferenceQuery() {
		super(new GetLegislativeReferenceRead());
	}

	private GetLegislativeReferenceQuery(final Long id, final Language language) {
    	super(new GetLegislativeReferenceRead());
    	this.id = id;
    	setLanguage(language);
    	setClosed(false);
	}

	private GetLegislativeReferenceQuery(final String localId, final Language language) {
		super(new GetLegislativeReferenceRead());
		this.localId = localId;
		setLanguage(language);
		setClosed(false);
	}

	public static GetLegislativeReferenceQuery create() {
	        return new GetLegislativeReferenceQuery();
    }

	public static GetLegislativeReferenceQuery create(final Long id, final Language language) {
		return new GetLegislativeReferenceQuery(id, language);
	}

	public static GetLegislativeReferenceQuery create(final String localId, final Language language) {
		return new GetLegislativeReferenceQuery(localId, language);
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

