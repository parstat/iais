package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation;

import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.documentation.GetProcessDocumentationsRead;


public class GetProcessDocumentationsQuery extends AbstractQuery<GetProcessDocumentationsRead> {

	private static final long serialVersionUID = 20320L;

	
	private String name;
	
	private Frequency frequency;
	

    private GetProcessDocumentationsQuery() {
		super(new GetProcessDocumentationsRead());
	}

	private GetProcessDocumentationsQuery(final Language language) {
    	super(new GetProcessDocumentationsRead());
    	setLanguage(language);
	}

	private GetProcessDocumentationsQuery(final String name, Frequency frequency,  final Language language) {
		super(new GetProcessDocumentationsRead());
		this.setName(name);
		this.frequency=frequency;
		setLanguage(language);
	}

	 
	public static GetProcessDocumentationsQuery create(final String name, Frequency frequency, final Language language) {
		return new GetProcessDocumentationsQuery( name,frequency, language);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	}

