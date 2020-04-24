package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.CreateStatisticalProgramEvent;


public class CreateStatisticalProgramCommand extends AbstractCommand<CreateStatisticalProgramEvent> {

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
     * status of the survey, default CURRENT
     * NEW_PROPOSAL,
     * NEW_UNDER_DEVELOPMENT,
     * CURRENT,
     * COMPLETED,
     * CANCELLED,
     * TRANSFERRED_TO_ANOTHER_ORGANIZATION
     */
    private ProgramStatus status = ProgramStatus.CURRENT;

    private CreateStatisticalProgramCommand() {
        super(new CreateStatisticalProgramEvent());
    }

    private CreateStatisticalProgramCommand(final Long accountId, final AccountRole role, final String name,
                                            final String description, final String acronym, final String localId,
                                            final Language language) {
        super(new CreateStatisticalProgramEvent());
        setAccountId(accountId);
        setAccountRole(role);
        this.name = name;
        this.description = description;
        this.acronym = acronym;
        this.localId = localId;
        setLanguage(language);
    }

    public static CreateStatisticalProgramCommand create(final Long accountId, final AccountRole role, final String name,
                                                         final String description, final String acronym,
                                                         final String localId, final Language language) {

        return new CreateStatisticalProgramCommand(accountId, role, name, description, acronym, localId, language);

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

    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(final ProgramStatus status) {
        this.status = status;
    }

}
