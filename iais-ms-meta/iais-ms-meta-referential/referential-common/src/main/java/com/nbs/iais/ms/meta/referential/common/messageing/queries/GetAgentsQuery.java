package com.nbs.iais.ms.meta.referential.common.messageing.queries;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.GetAgentsRead;

public class GetAgentsQuery extends AbstractQuery<GetAgentsRead> {

	private static final long serialVersionUID = 20320L;

	private AgentType agentType;

    private GetAgentsQuery() {
		super(new GetAgentsRead());
	}

	private GetAgentsQuery(final Language language) {
    	super(new GetAgentsRead());
    	setLanguage(language);
	}

	private GetAgentsQuery(final AgentType agentType, final Language language) {
		super(new GetAgentsRead());
		this.agentType = agentType;
		setLanguage(language);
	}

	public static GetAgentsQuery create(final Language language) {
	        return new GetAgentsQuery(language);
    }

	public static GetAgentsQuery create(final AgentType type, final Language language) {
		return new GetAgentsQuery(type, language);
	}


	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}
}

