package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.documentation.GetProcessDocumentationRead;



public class GetProcessDocumentationQuery extends AbstractQuery<GetProcessDocumentationRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;
	
	private String localId;

	private Long businessFunction;

	private Long statisticalProgram;

	
	
    private GetProcessDocumentationQuery() {
		super(new GetProcessDocumentationRead());
	}

	private GetProcessDocumentationQuery(final Long id, final Language language) {
    	super(new GetProcessDocumentationRead());
    	this.id = id;
    	setClosed(false);
    	setLanguage(language);
	}

	private GetProcessDocumentationQuery(final Long statisticalProgram, final Long businessFunction,
										 final Language language) {
    	super(new GetProcessDocumentationRead());
    	this.setLanguage(language);
    	this.setClosed(false);
    	this.statisticalProgram = statisticalProgram;
    	this.businessFunction = businessFunction;
	}

	public static GetProcessDocumentationQuery create() {
	        return new GetProcessDocumentationQuery();
    }

	public static GetProcessDocumentationQuery create(final Long id, final Language language) {
		return new GetProcessDocumentationQuery(id, language);
	}

	public static GetProcessDocumentationQuery create(final Long statisticalProgram, final Long businessFunction,
													  final Language language) {
    	return new GetProcessDocumentationQuery(statisticalProgram, businessFunction, language);
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

	public Long getBusinessFunction() {
		return businessFunction;
	}

	public void setBusinessFunction(final Long businessFunction) {
		this.businessFunction = businessFunction;
	}

	public Long getStatisticalProgram() {
		return statisticalProgram;
	}

	public void setStatisticalProgram(final Long statisticalProgram) {
		this.statisticalProgram = statisticalProgram;
	}
}

