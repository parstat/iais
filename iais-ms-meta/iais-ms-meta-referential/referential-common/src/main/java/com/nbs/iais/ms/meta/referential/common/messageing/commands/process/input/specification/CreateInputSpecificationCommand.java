package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.input.specification.CreateInputSpecificationEvent;

public class CreateInputSpecificationCommand extends AbstractCommand<CreateInputSpecificationEvent> {

	private static final long serialVersionUID = 2300L;

	/**
	 * The process documentationation id
	 */
	private Long processDocumentation;
	/**
	 * The input specification name in selected language
	 */
	private String name;

	/**
	 * Description of the input specification in the selected language
	 */
	private String description;

	/**
	 * The input specification localId
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
 
	

	private CreateInputSpecificationCommand() {
		super(new CreateInputSpecificationEvent());
	}

	private CreateInputSpecificationCommand(final String jwt, final Long processDocumentation,final String name, final String description,
			final String localId,  final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {
		super(new CreateInputSpecificationEvent());
		setJwt(jwt);
		this.processDocumentation = processDocumentation;
		this.name = name;
		this.description = description;
		this.localId = localId;
		if (version != null)
			this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		if (versionRationale != null)
			this.versionRationale = versionRationale;
		 
		setLanguage(language);
		setClosed(true);
	}

	 
	public static CreateInputSpecificationCommand create(final String jwt,final Long processDocumentation, final String name, final String description,
			final String localId,  final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {

		return new CreateInputSpecificationCommand(jwt, processDocumentation,name, description, localId, version, versionDate,
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

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

}
