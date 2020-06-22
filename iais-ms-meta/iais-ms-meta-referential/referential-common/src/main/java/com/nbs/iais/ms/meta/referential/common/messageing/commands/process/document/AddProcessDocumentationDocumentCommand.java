package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.document.AddProcessDocumentationDocumentEvent;

public class AddProcessDocumentationDocumentCommand extends AbstractCommand<AddProcessDocumentationDocumentEvent> {

	private static final long serialVersionUID = 2300L;

	/**
	 * The process documentation id
	 */
	private Long documentation;

	/**
	 * The process document name in selected language
	 */
	private String name;
	
	/**
	 * The process document link in selected language
	 */
	private String link;


	/**
	 * Description of the process document in the selected language
	 */
	private String description;

	/**
	 * The process document localId
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
	 * process document type
	 **/
	private MediaType type;

	private AddProcessDocumentationDocumentCommand() {
		super(new AddProcessDocumentationDocumentEvent());
	}

	private AddProcessDocumentationDocumentCommand(final String jwt, final Long documentation, final String name,
												   final String description, final String localId, final String link, final MediaType type, final String version,
												   final LocalDateTime versionDate, final String versionRationale, final Language language) {
		super(new AddProcessDocumentationDocumentEvent());
		setJwt(jwt);
		this.documentation = documentation;
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
		setLink(link);
		setLanguage(language);
		setClosed(true);
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public static AddProcessDocumentationDocumentCommand create(final String jwt, final Long processDocumentation,
																final String name, final String description, final String localId, final String link, final MediaType type,
																final String version, final LocalDateTime versionDate, final String versionRationale,
																final Language language) {

		return new AddProcessDocumentationDocumentCommand(jwt, processDocumentation, name, description, localId,link, type, version,
				versionDate, versionRationale, language);

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

	public Long getDocumentation() {
		return documentation;
	}

	public void setDocumentation(Long documentation) {
		this.documentation = documentation;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
