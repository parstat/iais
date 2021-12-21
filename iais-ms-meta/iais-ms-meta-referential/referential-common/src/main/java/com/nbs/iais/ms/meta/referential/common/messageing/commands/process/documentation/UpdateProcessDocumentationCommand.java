package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.UpdateProcessDocumentationEvent;

public class UpdateProcessDocumentationCommand extends AbstractCommand<UpdateProcessDocumentationEvent> {

	private static final long serialVersionUID = 2610L;

	/**
	 * ProcessDocumentation id
	 */
	private Long id;
	/**
	 * ProcessDocumentation name in selected language
	 */
	private String name;

	/**
	 * Description of the ProcessDocumentation in the selected language
	 */
	private String description;

	/**
	 * The localId
	 */
	private String localId;
	/**
	 * new version
	 */
	private String version;

	private Long owner;

	private Frequency frequency;

	private Long maintainer;

	private String nextSubPhase;

	/**
	 * Version description
	 */
	private String versionRationale;

	/**
	 * Date of the version Default current time
	 */
	private LocalDateTime versionDate = LocalDateTime.now();

	private LocalDateTime validTo;

	private UpdateProcessDocumentationCommand() {
		super(new UpdateProcessDocumentationEvent());
	}

	private UpdateProcessDocumentationCommand(final String jwt, final Long id, final String name,
			final String description, final String localId, final String version, final LocalDateTime versionDate,
			final String versionRationale, final Long owner,
			final Frequency frequency, final Long maintainer, final String nextSubPhase, final LocalDateTime validTo, final Language language) {
		super(new UpdateProcessDocumentationEvent());
		this.setId(id);
		this.name = name;
		this.description = description;

		this.localId = localId;
		this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		this.versionRationale = versionRationale;
		this.owner = owner;
		this.frequency = frequency;
		this.maintainer = maintainer;
		this.nextSubPhase = nextSubPhase;
		this.validTo = validTo;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static UpdateProcessDocumentationCommand create(final String jwt, final Long id, final String name,
			final String description, final String localId, final String version, final LocalDateTime versionDate,
			final String versionRationale, final Long owner,
			final Frequency frequency, final Long maintainer, final String nextSubPhase, final LocalDateTime validTo, final Language language) {

		return new UpdateProcessDocumentationCommand(jwt, id, name, description, localId, version, versionDate,
				versionRationale, owner, frequency, maintainer, nextSubPhase, validTo,
				language);

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

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(final Long owner) {
		this.owner = owner;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(final Frequency frequency) {
		this.frequency = frequency;
	}

	public Long getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(final Long maintainer) {
		this.maintainer = maintainer;
	}

	public String getNextSubPhase() {
		return nextSubPhase;
	}

	public void setNextSubPhase(final String nextSubPhase) {
		this.nextSubPhase = nextSubPhase;
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

	public LocalDateTime getValidTo() {
		return validTo;
	}

	public void setValidTo(final LocalDateTime validTo) {
		this.validTo = validTo;
	}
}
