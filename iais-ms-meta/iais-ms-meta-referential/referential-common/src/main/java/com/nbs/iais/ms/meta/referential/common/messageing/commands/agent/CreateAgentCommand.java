package com.nbs.iais.ms.meta.referential.common.messageing.commands.agent;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.agent.CreateAgentEvent;

public class CreateAgentCommand extends AbstractCommand<CreateAgentEvent> {

	private static final long serialVersionUID = 2600L;

	/**
	 * Agent name in selected language
	 */
	private String name;

	/**
	 * Description of the agent in the selected language
	 */
	private String description;

	/**
	 * The internal id of agent If the user does not provide one the client should
	 * generate a guid
	 */
	private String localId;

	/**
	 * Agent type
	 * 
	 * ORGANIZATION, DIVISION, INDIVIDUAL
	 * 
	 */
	private AgentType type;

	/**
	 * Parent id agent
	 */
	private Long parent;

	/**
	 * Account id
	 */
	private Long account;

	private CreateAgentCommand() {
		super(new CreateAgentEvent());
	}

	private CreateAgentCommand(final String jwt, final String name, final String description, final AgentType type, final String localId,
			final Long parent, final Long account, final Language language) {
		super(new CreateAgentEvent());
		this.name = name;
		this.description = description;
		this.type = type;
		this.localId = localId;
		this.parent = parent;
		this.account = account;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static CreateAgentCommand create(final String jwt, final String name, final String description, final AgentType type,
			final String localId, final Long parent, final Long account, final Language language) {

		return new CreateAgentCommand(jwt, name, description, type, localId, parent, account, language);

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(final String localId) {
		this.localId = localId;
	}

	public AgentType getType() {
		return type;
	}

	public void setType(final AgentType type) {
		this.type = type;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(final Long parent) {
		this.parent = parent;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}
}
