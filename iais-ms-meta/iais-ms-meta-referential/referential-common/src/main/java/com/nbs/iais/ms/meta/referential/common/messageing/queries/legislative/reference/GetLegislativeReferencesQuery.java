package com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference;

import java.time.LocalDateTime;


import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.legislative.reference.GetLegislativeReferencesRead;

public class GetLegislativeReferencesQuery extends AbstractQuery<GetLegislativeReferencesRead> {

	private static final long serialVersionUID = 20320L;

	private LegislativeType type;

    private LocalDateTime approvalDate;

	private String name;
	

    private GetLegislativeReferencesQuery() {
		super(new GetLegislativeReferencesRead());
	}

	private GetLegislativeReferencesQuery(final Language language) {
    	super(new GetLegislativeReferencesRead());
    	setLanguage(language);
	}

	private GetLegislativeReferencesQuery(final LegislativeType type, final String name, final Language language) {
		super(new GetLegislativeReferencesRead());
		this.type = type;
		this.setName(name);
		setLanguage(language);
	}

	
	public static GetLegislativeReferencesQuery create(final LegislativeType type, final String name, final Language language) {
		return new GetLegislativeReferencesQuery(type, name, language);
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}

	public LegislativeType getType() {
		return type;
	}

	public void setType(LegislativeType type) {
		this.type = type;
	}

	}

