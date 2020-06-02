package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.output.specification.UpdateOutputSpecificationEvent;

public class UpdateOutputSpecificationCommand extends AbstractCommand<UpdateOutputSpecificationEvent> {

	private static final long serialVersionUID = 26110L;

	/**
	 * output specification id
	 */
	private Long id;
	/**
	 * output specification name in selected language
	 */
	private String name;

	/**
	 * Description of the output specification in the selected language
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
     * Date of the version
     * Default current time
     */
    private LocalDateTime versionDate = LocalDateTime.now();


	private UpdateOutputSpecificationCommand() {
		super(new UpdateOutputSpecificationEvent());
	}

	private UpdateOutputSpecificationCommand(final String jwt, final Long id, final String name,
			final String description,   final String localId,final String version,
            final LocalDateTime versionDate, final String versionRationale,
			final Language language) {
		super(new UpdateOutputSpecificationEvent());
		this.setId(id);
		this.name = name;
		this.description = description;
	 
		this.localId = localId;
		this.version=version;
		if(versionDate!=null) this.versionDate=versionDate;
		this.versionRationale=versionRationale;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static UpdateOutputSpecificationCommand create(final String jwt, final Long id, final String name,
			final String description, final String localId,final String version,
            final LocalDateTime versionDate, final String versionRationale,
			final Language language) {

		return new UpdateOutputSpecificationCommand(jwt, id, name, description, localId,version,versionDate,versionRationale, language);

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
}
