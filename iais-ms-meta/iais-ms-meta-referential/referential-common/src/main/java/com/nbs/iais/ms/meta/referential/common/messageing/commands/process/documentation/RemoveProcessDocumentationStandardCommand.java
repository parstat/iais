package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.RemoveProcessDocumentationStandardEvent;

public class RemoveProcessDocumentationStandardCommand extends AbstractCommand<RemoveProcessDocumentationStandardEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;

    private Long statisticalStandard;

    private RemoveProcessDocumentationStandardCommand() {
        super(new RemoveProcessDocumentationStandardEvent());
    }

    private RemoveProcessDocumentationStandardCommand(final String jwt, final Long processDocumentation,
                                                      final Long statisticalStandard, final Language language) {
        super(new RemoveProcessDocumentationStandardEvent());
        setClosed(true);
        setJwt(jwt);
        setLanguage(language);
        this.processDocumentation = processDocumentation;
        this.statisticalStandard = statisticalStandard;
    }

    public static RemoveProcessDocumentationStandardCommand create(final String jwt, final Long processDocumentation,
                                                                   final Long statisticalStandard,
                                                                   final Language language) {
        return new RemoveProcessDocumentationStandardCommand(jwt, processDocumentation, statisticalStandard, language);
    }

    public Long getProcessDocumentation() {
        return processDocumentation;
    }

    public void setProcessDocumentation(final Long processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    public Long getStatisticalStandard() {
        return statisticalStandard;
    }

    public void setStatisticalStandard(final Long statisticalStandard) {
        this.statisticalStandard = statisticalStandard;
    }
}
