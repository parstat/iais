package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetStatisticalProgramsRead;

public class GetStatisticalProgramsQuery extends AbstractQuery<GetStatisticalProgramsRead> {

	private static final long serialVersionUID = 20320L;

	private String name;

	private String localId;

	private Long maintainer;

	private boolean mine = false;

    private GetStatisticalProgramsQuery(final Language language) {
		super(new GetStatisticalProgramsRead());
		setLanguage(language);
	}



	public static GetStatisticalProgramsQuery create(final Language language) {
	        return new GetStatisticalProgramsQuery(language);
    }


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(final String localId) {
		this.localId = localId;
	}

	public Long getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(final Long maintainer) {
		this.maintainer = maintainer;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(final boolean mine) {
		this.mine = mine;
	}
}

