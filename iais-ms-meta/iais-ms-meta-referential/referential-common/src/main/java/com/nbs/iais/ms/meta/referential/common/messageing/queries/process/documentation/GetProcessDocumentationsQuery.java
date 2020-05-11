package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.documentation.GetProcessDocumentationsRead;

public class GetProcessDocumentationsQuery extends AbstractQuery<GetProcessDocumentationsRead> {

	private static final long serialVersionUID = 20320L;

	private GetProcessDocumentationsQuery() {
		super(new GetProcessDocumentationsRead());
	}

	private GetProcessDocumentationsQuery(final Language language) {
		super(new GetProcessDocumentationsRead());
		setLanguage(language);
	}

	public static GetProcessDocumentationsQuery create(final Language language) {
		return new GetProcessDocumentationsQuery(language);
	}

}
