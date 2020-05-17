package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.quality;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.quality.GetProcessQualitiesRead;

public class GetProcessQualitiesQuery extends AbstractQuery<GetProcessQualitiesRead> {

	private static final long serialVersionUID = 2033420L;

	private Long processDocumentation;

	private GetProcessQualitiesQuery() {
		super(new GetProcessQualitiesRead());
	}

	private GetProcessQualitiesQuery(final Language language) {
		super(new GetProcessQualitiesRead());
		setLanguage(language);
	}

	private GetProcessQualitiesQuery(final Long processDocumentation, final Language language) {
		super(new GetProcessQualitiesRead());
		setLanguage(language);
		this.processDocumentation = processDocumentation;
	}

	public static GetProcessQualitiesQuery create(final Language language) {
		return new GetProcessQualitiesQuery(language);
	}

	public static GetProcessQualitiesQuery create(final Long processDocumentation, final Language language) {
		return new GetProcessQualitiesQuery(processDocumentation, language);
	}

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

}
