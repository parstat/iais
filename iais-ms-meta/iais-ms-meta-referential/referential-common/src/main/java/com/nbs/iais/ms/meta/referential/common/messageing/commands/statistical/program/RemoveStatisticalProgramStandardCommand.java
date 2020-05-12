package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.RemoveStatisticalProgramStandardEvent;

public class RemoveStatisticalProgramStandardCommand extends AbstractCommand<RemoveStatisticalProgramStandardEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * Id of statistical program
     */
    private Long statisticalProgramId;

    /**
     * Id of statistical standard to remove
     */
    private Long statisticalStandardId;

    private RemoveStatisticalProgramStandardCommand() {
        super(new RemoveStatisticalProgramStandardEvent());
    }

    private RemoveStatisticalProgramStandardCommand(final String jwt, final Long statisticalProgramId,
                                                    final Long statisticalStandardId, final Language language) {

        super(new RemoveStatisticalProgramStandardEvent());
        setJwt(jwt);
        setClosed(true);
        setLanguage(language);
        this.statisticalProgramId = statisticalProgramId;
        this.statisticalStandardId = statisticalStandardId;

    }

    public static RemoveStatisticalProgramStandardCommand create(final String jwt, final Long statisticalProgramId,
                                                                 final Long statisticalStandardId, final Language language) {

        return new RemoveStatisticalProgramStandardCommand(jwt, statisticalProgramId, statisticalStandardId, language);

    }

    public Long getStatisticalProgramId() {
        return statisticalProgramId;
    }

    public void setStatisticalProgramId(final Long statisticalProgramId) {
        this.statisticalProgramId = statisticalProgramId;
    }

    public Long getStatisticalStandardId() {
        return statisticalStandardId;
    }

    public void setStatisticalStandardId(final Long statisticalStandardId) {
        this.statisticalStandardId = statisticalStandardId;
    }
}
