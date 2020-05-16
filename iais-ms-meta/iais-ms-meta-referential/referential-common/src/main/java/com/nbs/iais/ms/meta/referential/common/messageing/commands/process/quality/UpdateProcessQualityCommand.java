package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.QualityType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.quaity.UpdateProcessQualityEvent;

public class UpdateProcessQualityCommand extends AbstractCommand<UpdateProcessQualityEvent> {

	private static final long serialVersionUID = 2610L;

	/**
	 * process quality id
	 */
	private Long id;
	/**
	 * process quality name in selected language
	 */
	private String name;

	/**
	 * Description of the process quality in the selected language
	 */
	private String description;

	/**
	 * The localId
	 */
	private String localId;

	/**
	 * QualityType type
	 * 
	 * INDICATOR, CONTROL,
	 * 
	 */
	private QualityType type;

	/**
	 * new version
	 */
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionRationale() {
		return versionRationale;
	}

	public void setVersionRationale(String versionRationale) {
		this.versionRationale = versionRationale;
	}

	public LocalDateTime getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(LocalDateTime versionDate) {
		this.versionDate = versionDate;
	}

	/**
	 * Version description
	 */
	private String versionRationale;

	/**
	 * Date of the version Default current time
	 */
	private LocalDateTime versionDate = LocalDateTime.now();

	private UpdateProcessQualityCommand() {
		super(new UpdateProcessQualityEvent());
	}

	private UpdateProcessQualityCommand(final String jwt, final Long id, final String name, final String description,
			final QualityType type, final String localId, final String version, final LocalDateTime versionDate,
			final String versionRationale, final Language language) {
		super(new UpdateProcessQualityEvent());
		this.setId(id);
		this.name = name;
		this.description = description;
		this.setType(type);
		this.localId = localId;
		this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		this.versionRationale = versionRationale;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static UpdateProcessQualityCommand create(final String jwt, final Long id, final String name,
			final String description, final QualityType type, final String localId, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {

		return new UpdateProcessQualityCommand(jwt, id, name, description, type, localId, version, versionDate,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QualityType getType() {
		return type;
	}

	public void setType(QualityType type) {
		this.type = type;
	}
}
