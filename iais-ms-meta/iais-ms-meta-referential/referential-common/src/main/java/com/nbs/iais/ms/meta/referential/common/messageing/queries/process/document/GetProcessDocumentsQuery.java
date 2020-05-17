package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.document;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.document.GetProcessDocumentsRead;
 
public class GetProcessDocumentsQuery extends AbstractQuery<GetProcessDocumentsRead> {

	private static final long serialVersionUID = 203320L;

	private Long processDocumentation;
	
	private GetProcessDocumentsQuery() {
		super(new GetProcessDocumentsRead());
	}

	private GetProcessDocumentsQuery(final Language language) {
		super(new GetProcessDocumentsRead());
		setLanguage(language);
	}

	private GetProcessDocumentsQuery(final Long processDocumentation,final Language language) {
		super(new GetProcessDocumentsRead());
		setLanguage(language);
		this.processDocumentation=processDocumentation;
	}
	public static GetProcessDocumentsQuery create(final Language language) {
		return new GetProcessDocumentsQuery(language);
	}

	public static GetProcessDocumentsQuery create(final Long processDocumentation,final Language language) {
		return new GetProcessDocumentsQuery(processDocumentation,language);
	}

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

}
