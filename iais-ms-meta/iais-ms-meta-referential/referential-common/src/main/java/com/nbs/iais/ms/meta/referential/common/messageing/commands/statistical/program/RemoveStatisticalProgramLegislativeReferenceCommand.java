package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.RemoveStatisticalProgramLegislativeReferenceEvent;

public class RemoveStatisticalProgramLegislativeReferenceCommand extends AbstractCommand<RemoveStatisticalProgramLegislativeReferenceEvent> {

    private static final long serialVersionUID = 200L;

    private Long statisticalProgramId;

    private Long legislativeReferenceId;

    private RemoveStatisticalProgramLegislativeReferenceCommand() {
        super(new RemoveStatisticalProgramLegislativeReferenceEvent());
    }

    private RemoveStatisticalProgramLegislativeReferenceCommand(final String jwt, final Long statisticalProgramId,
                                                                final Long legislativeReferenceId,
                                                                final Language language) {

        super(new RemoveStatisticalProgramLegislativeReferenceEvent());

        this.statisticalProgramId = statisticalProgramId;
        this.legislativeReferenceId = legislativeReferenceId;

        setLanguage(language);
        setJwt(jwt);
        setClosed(true);

    }

    public static RemoveStatisticalProgramLegislativeReferenceCommand create(final String jwt, final Long statisticalProgramId,
                                                                             final Long legislativeReferenceId,
                                                                             final Language language) {

        return new RemoveStatisticalProgramLegislativeReferenceCommand(jwt, statisticalProgramId,
                legislativeReferenceId, language);

    }

    public Long getStatisticalProgramId() {
        return statisticalProgramId;
    }

    public void setStatisticalProgramId(final Long statisticalProgramId) {
        this.statisticalProgramId = statisticalProgramId;
    }

    public Long getLegislativeReferenceId() {
        return legislativeReferenceId;
    }

    public void setLegislativeReferenceId(final Long legislativeReferenceId) {
        this.legislativeReferenceId = legislativeReferenceId;
    }
}
