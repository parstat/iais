package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.document.UpdateProcessDocumentEvent;

public class UpdateProcessDocumentCommand extends AbstractCommand<UpdateProcessDocumentEvent> {

	private static final long serialVersionUID = 2610L;

	/**
	 * ProcessDocument id
	 */
	private Long id;
	/**
	 * ProcessDocument name in selected language
	 */
	private String name;

	/**
	 * Description of the ProcessDocument in the selected language
	 */
	private String description;

	/**
	 * The localId
	 */
	private String localId;

	/**
	 * MediaType type
	 * 
	 * APPLICATION_PDF, APPLICATION_ZIP, APPLICATION_JSON, APPLICATION_DOC,
	 * APPLICATION_DOCX, APPLICATION_PPT, APPLICATION_PPTX, APPLICATION_ODT,
	 * APPLICATION_ODP, APPLICATION_ODS, IMAGE_PNG, IMAGE_JPEG, IMAGE_SVG,
	 * TEXT_PLAIN, TEXT_HTML, TEXT_CSV
	 * 
	 */
	private MediaType type;

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

	private UpdateProcessDocumentCommand() {
		super(new UpdateProcessDocumentEvent());
	}

	private UpdateProcessDocumentCommand(final String jwt, final Long id, final String name, final String description,
			final MediaType type, final String localId, final String version, final LocalDateTime versionDate,
			final String versionRationale, final Language language) {
		super(new UpdateProcessDocumentEvent());
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

	public static UpdateProcessDocumentCommand create(final String jwt, final Long id, final String name,
			final String description, final MediaType type, final String localId, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {

		return new UpdateProcessDocumentCommand(jwt, id, name, description, type, localId, version, versionDate,
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

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}
}
