package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.output.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.output.specification.GetProcessOutputSpecificationsRead;
 
public class GetProcessOutputSpecificationsQuery extends AbstractQuery<GetProcessOutputSpecificationsRead> {

	private static final long serialVersionUID = 2031320L;

	 private Long processDocumentation;
	
	private GetProcessOutputSpecificationsQuery() {
		super(new GetProcessOutputSpecificationsRead());
	}

	private GetProcessOutputSpecificationsQuery(final Language language) {
		super(new GetProcessOutputSpecificationsRead());
		setLanguage(language);
	}

	private GetProcessOutputSpecificationsQuery(final Long processDocumentation,final Language language) {
		super(new GetProcessOutputSpecificationsRead());
		setLanguage(language);
		this.processDocumentation=processDocumentation;
	}
	public static GetProcessOutputSpecificationsQuery create(final Language language) {
		return new GetProcessOutputSpecificationsQuery(language);
	}

	public static GetProcessOutputSpecificationsQuery create(final Long processDocumentation,final Language language) {
		return new GetProcessOutputSpecificationsQuery(processDocumentation,language);
	}

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}
 

}
