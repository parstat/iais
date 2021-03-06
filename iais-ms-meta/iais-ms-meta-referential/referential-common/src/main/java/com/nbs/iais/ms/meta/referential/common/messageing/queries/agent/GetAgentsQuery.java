package com.nbs.iais.ms.meta.referential.common.messageing.queries.agent;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.reads.agent.GetAgentsRead;


public class GetAgentsQuery extends AbstractQuery<GetAgentsRead> {

	private static final long serialVersionUID = 20320L;

	private AgentType agentType;
	
	private String name;
	
	private Long parent;

    private GetAgentsQuery() {
		super(new GetAgentsRead());
	}

	private GetAgentsQuery(final Language language) {
    	super(new GetAgentsRead());
    	setLanguage(language);
	}

	private GetAgentsQuery(final AgentType agentType, String name, Long parent, final Language language) {
		super(new GetAgentsRead());
		this.agentType = agentType;
		this.setName(name);
		this.setParent(parent);
		setLanguage(language);
	}

	public static GetAgentsQuery create(final Language language) {
	        return new GetAgentsQuery(language);
    }

	public static GetAgentsQuery create(final AgentType type, String name, Long parent, final Language language) {
		return new GetAgentsQuery(type, name, parent, language);
	}


	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}
}

