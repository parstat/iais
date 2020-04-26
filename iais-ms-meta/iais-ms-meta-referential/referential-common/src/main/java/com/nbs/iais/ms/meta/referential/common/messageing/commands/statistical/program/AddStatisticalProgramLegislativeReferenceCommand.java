package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.AddStatisticalProgramLegislativeReferenceEvent;

public class AddStatisticalProgramLegislativeReferenceCommand extends AbstractCommand<AddStatisticalProgramLegislativeReferenceEvent> {

    private static final long serialVersionUID = 200L;

    private Long statisticalProgram;
    private Long legislativeReference;

    private AddStatisticalProgramLegislativeReferenceCommand() {
        super(new AddStatisticalProgramLegislativeReferenceEvent());
    }

    private AddStatisticalProgramLegislativeReferenceCommand(final String jwt, final Long statisticalProgram,
                                                             final Long legislativeReference, final Language language) {

        super(new AddStatisticalProgramLegislativeReferenceEvent());
        this.legislativeReference = legislativeReference;
        this.statisticalProgram = statisticalProgram;
        setLanguage(language);
        setJwt(jwt);
    }

    public static AddStatisticalProgramLegislativeReferenceCommand create(final String jwt, final Long statisticalProgram,
                                                                          final Long legalReference, final Language language) {
        return new AddStatisticalProgramLegislativeReferenceCommand(jwt, statisticalProgram, legalReference,
                language);
    }

    public Long getStatisticalProgram() {
        return statisticalProgram;
    }

    public void setStatisticalProgram(final Long statisticalProgram) {
        this.statisticalProgram = statisticalProgram;
    }

    public Long getLegislativeReference() {
        return legislativeReference;
    }

    public void setLegislativeReference(final Long legislativeReference) {
        this.legislativeReference = legislativeReference;
    }
}
