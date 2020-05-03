package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;

import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.standard.CreateStatisticalStandardEvent;

import java.time.LocalDateTime;

public class CreateStatisticalStandardCommand extends AbstractCommand<CreateStatisticalStandardEvent> {

	private static final long serialVersionUID = 2300L;

	/**
	 * The statistical standard name in selected language
	 */
	private String name;

	/**
	 * Description of the Statistical standard in the selected language
	 */
	private String description;

	/**
	 * The statistical standard localId
	 */
	private String localId;

	/**
	 * Version being created default 1.0
	 */
	private String version = "1.0";

	/**
	 * Version description default "First Version"
	 */
	private String versionRationale = "First Version";

	/**
	 * Date of the version Default current time
	 */
	private LocalDateTime versionDate = LocalDateTime.now();
	/**
	 * statistical standard type
	 **/
	private StatisticalStandardType type;

	private CreateStatisticalStandardCommand() {
		super(new CreateStatisticalStandardEvent());
	}

	private CreateStatisticalStandardCommand(final String jwt, final String name, final String description,
			final String localId, final StatisticalStandardType type, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {
		super(new CreateStatisticalStandardEvent());
		setJwt(jwt);
		this.name = name;
		this.description = description;
		this.localId = localId;
		if (version != null)
			this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		if (versionRationale != null)
			this.versionRationale = versionRationale;
		this.type = type;
		setLanguage(language);
		setClosed(true);
	}

	public StatisticalStandardType getType() {
		return type;
	}

	public void setType(StatisticalStandardType type) {
		this.type = type;
	}

	public static CreateStatisticalStandardCommand create(final String jwt, final String name, final String description,
			final String localId, final StatisticalStandardType type, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {

		return new CreateStatisticalStandardCommand(jwt, name, description, localId, type, version, versionDate,
				versionRationale, language);

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

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getVersionRationale() {
		return versionRationale;
	}

	public void setVersionRationale(final String versionRationale) {
		this.versionRationale = versionRationale;
	}

	public LocalDateTime getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(final LocalDateTime versionDate) {
		this.versionDate = versionDate;
	}

}
