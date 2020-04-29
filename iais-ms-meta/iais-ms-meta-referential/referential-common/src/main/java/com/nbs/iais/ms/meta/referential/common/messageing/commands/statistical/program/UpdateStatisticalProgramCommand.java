package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.UpdateStatisticalProgramEvent;

import java.time.LocalDateTime;

public class UpdateStatisticalProgramCommand extends AbstractCommand<UpdateStatisticalProgramEvent> {

    /**
     * Id of the survey (statistical program) to update
     */
    private Long id;

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
     * Version description
     */
    private String versionRationale;

    /**
     * Date of the version
     */
    private LocalDateTime versionDate;

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
     * default 0.0 update only when > 0
     */
    private double budget = 0.0;

    /**
     * source of fonding for the survey
     * update only if not empty
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

    private UpdateStatisticalProgramCommand() {
        super();
    }

    private UpdateStatisticalProgramCommand(final String jwt, final Long id, final String name, final String description,
                                            final String acronym, final String versionRationale, final LocalDateTime versionDate, final LocalDateTime dateInitiated,
                                            final LocalDateTime dateEnded, final double budget, final String sourceOfFunding,
                                            final ProgramStatus status, final Language language) {
        super(new UpdateStatisticalProgramEvent());
        setJwt(jwt);
        this.id = id;
        this.name = name;
        this.description = description;
        this.acronym = acronym;
        this.versionRationale = versionRationale;
        this.versionDate = versionDate;
        this.dateInitiated = dateInitiated;
        this.dateEnded = dateEnded;
        this.budget = budget;
        this.sourceOfFunding = sourceOfFunding;
        this.status = status;
        setClosed(true);
        setLanguage(language);

    }

    public static UpdateStatisticalProgramCommand create(final String jwt, final Long id, final String name, final String description,
                                                  final String acronym, final String versionRationale, final LocalDateTime versionDate, final LocalDateTime dateInitiated,
                                                  final LocalDateTime dateEnded, final double budget, final String sourceOfFunding,
                                                  final ProgramStatus status, final Language language) {

        return new UpdateStatisticalProgramCommand(jwt, id, name, description, acronym, versionRationale, versionDate,
                dateInitiated, dateEnded, budget, sourceOfFunding, status, language);

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(final ProgramStatus status) {
        this.status = status;
    }
}
