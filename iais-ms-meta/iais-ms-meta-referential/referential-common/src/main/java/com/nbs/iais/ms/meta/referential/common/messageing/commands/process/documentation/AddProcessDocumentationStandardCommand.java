package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationStandardEvent;

public class AddProcessDocumentationStandardCommand extends AbstractCommand<AddProcessDocumentationStandardEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;
    private Long statisticalStandardReference;

    private AddProcessDocumentationStandardCommand() {
        super(new AddProcessDocumentationStandardEvent());
    }

    private AddProcessDocumentationStandardCommand(final String jwt, final Long processDocumentation,
                                                 final Long statisticalStandardReference, final Language language) {

        super(new AddProcessDocumentationStandardEvent());
        this.processDocumentation = processDocumentation;
        this.statisticalStandardReference = statisticalStandardReference;
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationStandardCommand create(final String jwt, final Long processDocumentation,
                                                       final Long statisticalStandardReference, final Language language) {

        return new AddProcessDocumentationStandardCommand(jwt, processDocumentation, statisticalStandardReference, language);
    }

    public Long getStatisticalProgram() {
        return processDocumentation;
    }

    public void setStatisticalProgram(final Long processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    public Long getStatisticalStandardReference() {
        return statisticalStandardReference;
    }

    public void setStatisticalStandardReference(final Long statisticalStandardReference) {
        this.statisticalStandardReference = statisticalStandardReference;
    }
}
