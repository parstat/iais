package com.nbs.iais.ms.meta.referential.common.messageing.queries.process.quality;

import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.process.quality.GetProcessQualityRead;

public class GetProcessQualityQuery extends AbstractQuery<GetProcessQualityRead> {

	private static final long serialVersionUID = 210320L;

	private Long id;

	private GetProcessQualityQuery() {
		super(new GetProcessQualityRead());
	}

	private GetProcessQualityQuery(final Long id) {
		super(new GetProcessQualityRead());
		this.id = id;
	}

	public static GetProcessQualityQuery create() {
		return new GetProcessQualityQuery();
	}

	public static GetProcessQualityQuery create(final Long id) {
		return new GetProcessQualityQuery(id);
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

}
