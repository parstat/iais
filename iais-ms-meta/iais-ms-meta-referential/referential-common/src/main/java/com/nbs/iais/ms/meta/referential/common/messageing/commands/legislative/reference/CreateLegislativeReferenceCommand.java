package com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.legislative.reference.CreateLegislativeReferenceEvent;

import java.time.LocalDateTime;


public class CreateLegislativeReferenceCommand extends AbstractCommand<CreateLegislativeReferenceEvent> {

	private static final long serialVersionUID = 2300L;


    private Integer number;


    private LocalDateTime approvalDate;
    
	private String link;
    
	/**
	 * The legislative reference name in selected language
	 */
	private String name;

	/**
	 * Description of the legislative reference in the selected language
	 */
	private String description;

	/**
	 * The legislative reference localId
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
	 * LegislativeType  type
	 **/
	private LegislativeType type;

	private CreateLegislativeReferenceCommand() {
		super(new CreateLegislativeReferenceEvent());
	}

	private CreateLegislativeReferenceCommand(final String jwt, final String name, final String description,
			final String localId,final Integer number, final LocalDateTime approvalDate,final String link, final LegislativeType type, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {
		super(new CreateLegislativeReferenceEvent());
		setJwt(jwt);
		this.name = name;
		this.description = description;
		this.number = number;
		this.approvalDate = approvalDate;
		this.setLink(link);
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
 

	public static CreateLegislativeReferenceCommand create(final String jwt, final String name, final String description,
			final String localId,final Integer number, final LocalDateTime approvalDate, final String link, final LegislativeType type, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {

		return new CreateLegislativeReferenceCommand(jwt, name, description, localId,number,approvalDate,link, type, version, versionDate,
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}

	public void setType(LegislativeType type) {
		this.type = type;
	}

	public LegislativeType getType() {
		return type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
