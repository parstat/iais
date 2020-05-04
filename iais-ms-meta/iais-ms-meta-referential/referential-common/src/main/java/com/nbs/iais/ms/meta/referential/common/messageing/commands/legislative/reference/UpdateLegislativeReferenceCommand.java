package com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference;

import java.time.LocalDateTime;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.legislative.reference.UpdateLegislativeReferenceEvent;

public class UpdateLegislativeReferenceCommand extends AbstractCommand<UpdateLegislativeReferenceEvent> {

	private static final long serialVersionUID = 2610L;

	private Integer number;

	private LocalDateTime approvalDate;

	/**
	 * LegislativeReference id
	 */
	private Long id;
	/**
	 * LegislativeReference name in selected language
	 */
	private String name;

	/**
	 * Description of the LegislativeReference in the selected language
	 */
	private String description;

	/**
	 * The localId
	 */
	private String localId;

	/**
	 * LegislativeReference type
	 * 
	 * REGULATION, LAW, CODE, GOVERNMENTAL_DECISION, AMENDMENT,
	 * 
	 * 
	 */
	private LegislativeType type;

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

	private UpdateLegislativeReferenceCommand() {
		super(new UpdateLegislativeReferenceEvent());
	}

	private UpdateLegislativeReferenceCommand(final String jwt, final Long id, final String name,
			final String description, final Integer number, final LocalDateTime approvalDate,final LegislativeType type, final String localId, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {
		super(new UpdateLegislativeReferenceEvent());
		this.setId(id);
		this.name = name;
		this.description = description;
		this.number = number;
		this.approvalDate = approvalDate;
		this.type = type;
		this.localId = localId;
		this.version = version;
		if (versionDate != null)
			this.versionDate = versionDate;
		this.versionRationale = versionRationale;
		setLanguage(language);
		setClosed(true);
		setJwt(jwt);
	}

	public static UpdateLegislativeReferenceCommand create(final String jwt, final Long id, final String name,
			final String description,final Integer number, final LocalDateTime approvalDate, final LegislativeType type, final String localId, final String version,
			final LocalDateTime versionDate, final String versionRationale, final Language language) {

		return new UpdateLegislativeReferenceCommand(jwt, id, name, description,number,approvalDate, type, localId, version, versionDate,
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

	public LegislativeType getType() {
		return type;
	}

	public void setType(final LegislativeType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
