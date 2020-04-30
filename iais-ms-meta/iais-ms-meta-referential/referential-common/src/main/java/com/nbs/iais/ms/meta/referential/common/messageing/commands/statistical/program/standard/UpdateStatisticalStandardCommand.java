package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.standard.UpdateStatisticalStandardEvent;

public class UpdateStatisticalStandardCommand extends AbstractCommand<UpdateStatisticalStandardEvent> {

	private static final long serialVersionUID = 2610L;

	/**
	 * StatisticalStandard id
	 */
	private Long id;
	/**
	 * StatisticalStandard name in selected language
	 */
	private String name;

	/**
	 * Description of the StatisticalStandard in the selected language
	 */
	private String description;

	/**
	 * The localId
	 */
	private String localId;

	/**
	 * StatisticalStandard type
	 * 
	 * CLASSIFICATIONS, CONCEPTS, DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 * RECOMMENDATIONS, FRAMEWORK
	 * 
	 */
	private StatisticalStandardType type;

	private UpdateStatisticalStandardCommand() {
		super(new UpdateStatisticalStandardEvent());
	}

	private UpdateStatisticalStandardCommand(final String jwt, final Long id, final String name,
			final String description, final StatisticalStandardType type, final String localId,
			final Language language) {
		super(new UpdateStatisticalStandardEvent());
		this.setId(id);
		this.name = name;
		this.description = description;
		this.type = type;
		this.localId = localId;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static UpdateStatisticalStandardCommand create(final String jwt, final Long id, final String name,
			final String description, final StatisticalStandardType type, final String localId,
			final Language language) {

		return new UpdateStatisticalStandardCommand(jwt, id, name, description, type, localId, language);

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

	public StatisticalStandardType getType() {
		return type;
	}

	public void setType(final StatisticalStandardType type) {
		this.type = type;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
