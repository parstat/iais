package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.input.specification.UpdateProcessDocumentationInputEvent;

public class UpdateProcessDocumentationInputCommand extends AbstractCommand<UpdateProcessDocumentationInputEvent> {

	private static final long serialVersionUID = 2610L;

	/**
	 * Process documentation id
	 */
	private Long documentation;

	/**
	 * Process input id
	 */
	private Long input;
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

	private UpdateProcessDocumentationInputCommand() {
		super(new UpdateProcessDocumentationInputEvent());
	}

	private UpdateProcessDocumentationInputCommand(final String jwt, final Long documentation, final Long input, final String name,
												   final String description, final String localId, final String version, final LocalDateTime versionDate,
												   final String versionRationale, final Language language) {
		super(new UpdateProcessDocumentationInputEvent());
		this.documentation = documentation;
		this.input = input;
		this.name = name;
		this.description = description;

		this.localId = localId;
		this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		this.versionRationale = versionRationale;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static UpdateProcessDocumentationInputCommand create(final String jwt, final Long documentation, final Long input, final String name,
																final String description, final String localId, final String version, final LocalDateTime versionDate,
																final String versionRationale, final Language language) {

		return new UpdateProcessDocumentationInputCommand(jwt, documentation, input, name, description, localId, version, versionDate,
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

	public Long getInput() {
		return input;
	}

	public void setInput(final Long input) {
		this.input = input;
	}

	public Long getDocumentation() {
		return documentation;
	}

	public void setDocumentation(final Long documentation) {
		this.documentation = documentation;
	}
}
