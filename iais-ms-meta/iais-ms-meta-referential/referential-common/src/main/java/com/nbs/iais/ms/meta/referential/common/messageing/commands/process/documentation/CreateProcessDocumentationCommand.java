package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.CreateProcessDocumentationEvent;

public class CreateProcessDocumentationCommand extends AbstractCommand<CreateProcessDocumentationEvent> {

	private static final long serialVersionUID = 2300L;

	/**
	 * The process documentation name in selected language
	 */
	private String name;

	/**
	 * Description of the process documentation in the selected language
	 */
	private String description;

	/**
	 * The process documentation localId
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

	private Long businessFunction;

	private Long statisticalProgram;

	private Long owner;

	private Frequency frequency;

	private Long maintainer;

	private String nextSubPhase;

	private CreateProcessDocumentationCommand() {
		super(new CreateProcessDocumentationEvent());
	}

	private CreateProcessDocumentationCommand(final String jwt, final String name, final String description,
			final String localId, final String version, final LocalDateTime versionDate, final String versionRationale,
			final Long businessFunction, final Long statisticalProgram, final Long owner, final Frequency frequency,
			final Long maintainer, final String nextSubPhase, final Language language) {
		super(new CreateProcessDocumentationEvent());
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
		this.businessFunction = businessFunction;
		this.statisticalProgram = statisticalProgram;
		this. owner =  owner;
		this.frequency = frequency;
		this.maintainer = maintainer;
		this.nextSubPhase = nextSubPhase;
		setLanguage(language);
		setClosed(true);
	}

	public static CreateProcessDocumentationCommand create(final String jwt, final String name,
			final String description, final String localId, final String version, final LocalDateTime versionDate,
			final String versionRationale, final Long businessFunction, final Long statisticalProgram, final Long owner,
			final Frequency frequency, final Long maintainer, final String nextSubPhase, final Language language) {

		return new CreateProcessDocumentationCommand(jwt, name, description, localId, version, versionDate,
				versionRationale, businessFunction, statisticalProgram, owner, frequency, maintainer, nextSubPhase,
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

	public Long getBusinessFunction() {
		return businessFunction;
	}

	public void setBusinessFunction(Long businessFunction) {
		this.businessFunction = businessFunction;
	}

	public Long getStatisticalProgram() {
		return statisticalProgram;
	}

	public void setStatisticalProgram(Long statisticalProgram) {
		this.statisticalProgram = statisticalProgram;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Long getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(Long maintainer) {
		this.maintainer = maintainer;
	}

	public String getNextSubPhase() {
		return nextSubPhase;
	}

	public void setNextSubPhase(String nextSubPhase) {
		this.nextSubPhase = nextSubPhase;
	}

}
