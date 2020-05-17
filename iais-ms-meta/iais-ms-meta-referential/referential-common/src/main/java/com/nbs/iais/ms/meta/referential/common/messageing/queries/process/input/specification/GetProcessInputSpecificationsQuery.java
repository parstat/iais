package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.input.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.input.specification.GetProcessInputSpecificationsRead;
 
public class GetProcessInputSpecificationsQuery extends AbstractQuery<GetProcessInputSpecificationsRead> {

	private static final long serialVersionUID = 203320L;

	private Long processDocumentation;
	
	private GetProcessInputSpecificationsQuery() {
		super(new GetProcessInputSpecificationsRead());
	}

	private GetProcessInputSpecificationsQuery(final Language language) {
		super(new GetProcessInputSpecificationsRead());
		setLanguage(language);
	}

	private GetProcessInputSpecificationsQuery(final Long processDocumentation,final Language language) {
		super(new GetProcessInputSpecificationsRead());
		setLanguage(language);
		this.setProcessDocumentation(processDocumentation);
	}
	public static GetProcessInputSpecificationsQuery create(final Language language) {
		return new GetProcessInputSpecificationsQuery(language);
	}

	public static GetProcessInputSpecificationsQuery create(final Long processDocumentation,final Language language) {
		return new GetProcessInputSpecificationsQuery(processDocumentation,language);
	}

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

 

}
