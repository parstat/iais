package com.nbs.iais.ms.meta.referential.common.messageing.commands.agent;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;

import com.nbs.iais.ms.meta.referential.common.messageing.events.agent.UpdateAgentEvent;

public class UpdateAgentCommand extends AbstractCommand<UpdateAgentEvent> {

	private static final long serialVersionUID = 2610L;

	/**
	 * Agent id
	 */
	private Long id;
	/**
	 * Agent name in selected language
	 */
	private String name;

	/**
	 * Description of the agent in the selected language
	 */
	private String description;

	/**
	 * The localId
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

	private UpdateAgentCommand() {
		super(new UpdateAgentEvent());
	}

	private UpdateAgentCommand(final String jwt,final Long id, final String name, final String description, final AgentType type,
			final String localId, final Long parent, final Long account, final Language language) {
		super(new UpdateAgentEvent());
		this.setId(id);
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

	public static UpdateAgentCommand create(final String jwt, final Long id, final String name,
			final String description, final AgentType type, final String localId, final Long parent, final Long account,
			final Language language) {

		return new UpdateAgentCommand(jwt, id,name, description, type, localId, parent, account, language);

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
