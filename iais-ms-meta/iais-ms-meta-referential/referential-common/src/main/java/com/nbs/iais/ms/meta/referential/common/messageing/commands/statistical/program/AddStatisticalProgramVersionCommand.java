package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.AddStatisticalProgramVersionEvent;

import java.time.LocalDateTime;

public class AddStatisticalProgramVersionCommand extends AbstractCommand<AddStatisticalProgramVersionEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * Survey name in selected language
     */
    private String name;

    /**
     * Description of the survey in the selected language
     */
    private String description;

    /**
     * Acronym of the survey in the selected language
     */
    private String acronym;

    /**
     * The internal id of survey
     * If the user does not provide one the client should generate a guid
     */
    private String localId;

    /**
     * The previous version
     */
    private String previousVersion;

    /**
     * new version
     */
    private String version;

    /**
     * Version description
     */
    private String versionRationale;

    /**
     * Date of the version
     * Default current time
     */
    private LocalDateTime versionDate = LocalDateTime.now();

    /**
     * date the survey has initiated
     */
    private LocalDateTime dateInitiated;

    /**
     * date the survey has ended
     */
    private LocalDateTime dateEnded;

    /**
     * budget of the survey
     */
    private double budget;

    /**
     * source of fonding for the survey
     */
    private String sourceOfFunding;

    /**
     * status of the survey, default CURRENT
     * NEW_PROPOSAL,
     * NEW_UNDER_DEVELOPMENT,
     * CURRENT,
     * COMPLETED,
     * CANCELLED,
     * TRANSFERRED_TO_ANOTHER_ORGANIZATION
     */
    private ProgramStatus status = ProgramStatus.CURRENT;

    /**
     * agentId that is owner of the survey
     * normally the statistical office
     */
    private Long owner;

    /** agentId that is the maintainer of the survey
     * normally the division responsible for the survey
     */
    private Long maintainer;

    /**
     * agentId that is a contact person of the survey
     * normally the individual inside a division that has responsibility to provide info about the survey
     */
    private Long contact;

    private AddStatisticalProgramVersionCommand() {
        super();
    }

    private AddStatisticalProgramVersionCommand(final String jwt, final String name,
                                                final String description, final String acronym, final String localId,
                                                final LocalDateTime dateInitiated,
                                                final LocalDateTime dateEnded, final double budget,
                                                final String sourceOfFunding, final ProgramStatus status,
                                                final Long owner, final Long maintainer, final Long contact,
                                                final Language language) {
        super(new AddStatisticalProgramVersionEvent());
        setJwt(jwt);
        this.name = name;
        this.description = description;
        this.acronym = acronym;
        this.localId = localId;
        this.dateInitiated = dateInitiated;
        this.dateEnded = dateEnded;
        this.budget = budget;
        this.sourceOfFunding = sourceOfFunding;
        this.status = status;
        this.owner = owner;
        this.maintainer = maintainer;
        this.contact = contact;
        setLanguage(language);
        setClosed(true);
    }

    private AddStatisticalProgramVersionCommand(final String jwt, final String name,
                                                final String description, final String acronym, final String localId,
                                                final String previousVersion, final String version,
                                                final LocalDateTime versionDate, final String versionRationale,
                                                final LocalDateTime dateInitiated, final LocalDateTime dateEnded,
                                                final double budget, final String sourceOfFunding,
                                                final ProgramStatus status, final Long owner, final Long maintainer,
                                                final Long contact, final Language language) {
        super(new AddStatisticalProgramVersionEvent());
        setJwt(jwt);
        this.name = name;
        this.description = description;
        this.acronym = acronym;
        this.localId = localId;
        this.previousVersion = previousVersion;
        this.version = version;
        this.versionDate = versionDate;
        this.versionRationale = versionRationale;
        this.dateInitiated = dateInitiated;
        this.dateEnded = dateEnded;
        this.budget = budget;
        this.sourceOfFunding = sourceOfFunding;
        this.status = status;
        this.owner = owner;
        this.maintainer = maintainer;
        this.contact = contact;
        setLanguage(language);
        setClosed(true);
    }


    public static AddStatisticalProgramVersionCommand create(final String jwt, final String name,
                                                         final String description, final String acronym, final String localId,
                                                         final LocalDateTime dateInitiated, final LocalDateTime dateEnded,
                                                         final double budget, final String sourceOfFonding, final ProgramStatus status,
                                                         final Long owner, final Long maintainer, final Long contact,
                                                         final Language language) {

        return new AddStatisticalProgramVersionCommand(jwt, name, description, acronym, localId, dateInitiated,
                dateEnded, budget, sourceOfFonding, status, owner, maintainer, contact, language);

    }

    public static AddStatisticalProgramVersionCommand create(final String jwt, final String name,
                                                             final String description, final String acronym, final String localId,
                                                             final String previousVersion, final String version,
                                                             final LocalDateTime versionDate, final String versionRationale,
                                                             final LocalDateTime dateInitiated, final LocalDateTime dateEnded,
                                                             final double budget, final String sourceOfFunding,
                                                             final ProgramStatus status, final Long owner, final Long maintainer,
                                                             final Long contact, final Language language) {
        return new AddStatisticalProgramVersionCommand(jwt, name, description, acronym, localId, previousVersion,
                version, versionDate, versionRationale, dateInitiated, dateEnded, budget, sourceOfFunding, status,
                owner, maintainer, contact, language);
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(final String acronym) {
        this.acronym = acronym;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }

    public String getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(final String previousVersion) {
        this.previousVersion = previousVersion;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(final LocalDateTime versionDate) {
        this.versionDate = versionDate;
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


    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(final ProgramStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateInitiated() {
        return dateInitiated;
    }

    public void setDateInitiated(final LocalDateTime dateInitiated) {
        this.dateInitiated = dateInitiated;
    }

    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(final LocalDateTime dateEnded) {
        this.dateEnded = dateEnded;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(final double budget) {
        this.budget = budget;
    }

    public String getSourceOfFunding() {
        return sourceOfFunding;
    }

    public void setSourceOfFunding(final String sourceOfFunding) {
        this.sourceOfFunding = sourceOfFunding;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(final Long owner) {
        this.owner = owner;
    }

    public Long getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(final Long maintainer) {
        this.maintainer = maintainer;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(final Long contact) {
        this.contact = contact;
    }

}
