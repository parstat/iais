package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.agent.CreateAgentEvent;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
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
	 * Children id agent list
	 */
	private List<Long> children;

	/**
	 * Account id
	 */
	private Long account;

	/**
	 * administrativeDetails id
	 */
	private Long administrativeDetails;

	private CreateAgentCommand() {
		super(new CreateAgentEvent());
	}

	private CreateAgentCommand(final String name, final String description, final AgentType type, final String localId,
			final Long parent, final Long account, List<Long> children, final Long administrativeDetails,
			final Language language) {
		super(new CreateAgentEvent());
		this.name = name;
		this.description = description;
		this.type = type;
		this.localId = localId;
		this.parent = parent;
		this.children = children;
		this.account = account;
		this.administrativeDetails = administrativeDetails;
		setLanguage(language);
		setClosed(true);
	}

	public static CreateAgentCommand create(final String name, final String description, final AgentType type,
			final String localId, final Long parent, final Long account, List<Long> children,
			final Long administrativeDetails, final Language language) {

		return new CreateAgentCommand(name, description, type, localId, parent, account, children,
				administrativeDetails, language);

	}

}
