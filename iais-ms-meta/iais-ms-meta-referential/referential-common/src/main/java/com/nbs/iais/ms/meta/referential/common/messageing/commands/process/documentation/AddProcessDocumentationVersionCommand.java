package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationVersionEvent;

public class AddProcessDocumentationVersionCommand extends AbstractCommand<AddProcessDocumentationVersionEvent> {

	private static final long serialVersionUID = 22200L;

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
	 *  New Version
	 */
	private String version;

	/**
	 * Version description
	 */
	private String versionRationale;

	/**
	 * Date of the version Default current time
	 */
	private LocalDateTime versionDate = LocalDateTime.now();

	/**
	 * Business Function
	 */
	private Long businessFunction;

	/**
	 * Statistical Program
	 */
	private Long statisticalProgram;

	/**
	 * Frequency
	 */
	private Frequency frequency;

	/**
	 * Maintainer
	 */
	private Long maintainer;

	/**
	 * Next sub-phase
	 */
	private String nextSubPhase;

	private AddProcessDocumentationVersionCommand() {
		super(new AddProcessDocumentationVersionEvent());
	}

	private AddProcessDocumentationVersionCommand(final String jwt, final String name, final String description,
			final String localId, final String version, final LocalDateTime versionDate, final String versionRationale,
			final Long businessFunction, final Long statisticalProgram, final Frequency frequency,
			final Long maintainer, final String nextSubPhase, final Language language) {
		super(new AddProcessDocumentationVersionEvent());
		setJwt(jwt);
		this.name = name;
		this.description = description;
		this.localId = localId;
		this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		this.versionRationale = versionRationale;
		this.businessFunction = businessFunction;
		this.statisticalProgram = statisticalProgram;
		this.frequency = frequency;
		this.maintainer = maintainer;
		this.nextSubPhase = nextSubPhase;
		setLanguage(language);
		setClosed(true);
	}

	public static AddProcessDocumentationVersionCommand create(final String jwt, final String name,
			final String description, final String localId, final String version, final LocalDateTime versionDate,
			final String versionRationale, final Long businessFunction, final Long statisticalProgram,
			final Frequency frequency, final Long maintainer, final String nextSubPhase, final Language language) {

		return new AddProcessDocumentationVersionCommand(jwt, name, description, localId, version, versionDate,
				versionRationale, businessFunction, statisticalProgram, frequency, maintainer, nextSubPhase, language);
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
