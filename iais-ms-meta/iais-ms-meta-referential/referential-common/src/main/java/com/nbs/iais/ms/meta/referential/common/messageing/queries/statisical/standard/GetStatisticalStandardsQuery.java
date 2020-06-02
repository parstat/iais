package com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.statistical.standard.GetStatisticalStandardsRead;


public class GetStatisticalStandardsQuery extends AbstractQuery<GetStatisticalStandardsRead> {

	private static final long serialVersionUID = 20320L;

	private StatisticalStandardType type;

	private String name;
	

    private GetStatisticalStandardsQuery() {
		super(new GetStatisticalStandardsRead());
	}

	private GetStatisticalStandardsQuery(final Language language) {
    	super(new GetStatisticalStandardsRead());
    	setLanguage(language);
	}

	private GetStatisticalStandardsQuery(final StatisticalStandardType type, final String name,  final Language language) {
		super(new GetStatisticalStandardsRead());
		this.type = type;
		this.setName(name);
		setLanguage(language);
	}

	public static GetStatisticalStandardsQuery create(final Language language) {
	        return new GetStatisticalStandardsQuery(language);
    }

	public static GetStatisticalStandardsQuery create(final StatisticalStandardType type, final String name, final Language language) {
		return new GetStatisticalStandardsQuery(type, name, language);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public StatisticalStandardType getType() {
		return type;
	}

	public void setType(final StatisticalStandardType type) {
		this.type = type;
	}

	}

