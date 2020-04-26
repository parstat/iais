package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.AddStatisticalProgramStandardEvent;

public class AddStatisticalProgramStandardCommand extends AbstractCommand<AddStatisticalProgramStandardEvent> {

    private static final long serialVersionUID = 200L;

    private Long statisticalProgram;
    private Long statisticalStandardReference;

    private AddStatisticalProgramStandardCommand() {
        super(new AddStatisticalProgramStandardEvent());
    }

    private AddStatisticalProgramStandardCommand(final String jwt, final Long statisticalProgram,
                                                 final Long statisticalStandardReference, final Language language) {

        super(new AddStatisticalProgramStandardEvent());
        this.statisticalProgram = statisticalProgram;
        this.statisticalStandardReference = statisticalStandardReference;
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddStatisticalProgramStandardCommand create(final String jwt, final Long statisticalProgram,
                                                       final Long statisticalStandardReference, final Language language) {

        return new AddStatisticalProgramStandardCommand(jwt, statisticalProgram, statisticalStandardReference, language);
    }

    public Long getStatisticalProgram() {
        return statisticalProgram;
    }

    public void setStatisticalProgram(final Long statisticalProgram) {
        this.statisticalProgram = statisticalProgram;
    }

    public Long getStatisticalStandardReference() {
        return statisticalStandardReference;
    }

    public void setStatisticalStandardReference(final Long statisticalStandardReference) {
        this.statisticalStandardReference = statisticalStandardReference;
    }
}
