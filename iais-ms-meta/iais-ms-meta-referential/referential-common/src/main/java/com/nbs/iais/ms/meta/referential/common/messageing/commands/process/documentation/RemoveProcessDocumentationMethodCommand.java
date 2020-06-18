package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.RemoveProcessDocumentationMethodEvent;

public class RemoveProcessDocumentationMethodCommand extends AbstractCommand<RemoveProcessDocumentationMethodEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;

    private Long processMethod;

    private RemoveProcessDocumentationMethodCommand() {
        super(new RemoveProcessDocumentationMethodEvent());
    }

    private RemoveProcessDocumentationMethodCommand(final String jwt, final Long processDocumentation,
                                                    final Long processMethod, final Language language) {
        super(new RemoveProcessDocumentationMethodEvent());
        setJwt(jwt);
        setLanguage(language);
        setClosed(true);
        this.processDocumentation = processDocumentation;
        this.processMethod = processMethod;
    }

    public static RemoveProcessDocumentationMethodCommand create(final String jwt, final Long processDocumentation,
                                                                 final Long statisticalMethod, final Language language) {
        return new RemoveProcessDocumentationMethodCommand(jwt, processDocumentation, statisticalMethod, language);
    }

    public Long getProcessDocumentation() {
        return processDocumentation;
    }

    public void setProcessDocumentation(final Long processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    public Long getProcessMethod() {
        return processMethod;
    }

    public void setProcessMethod(final Long processMethod) {
        this.processMethod = processMethod;
    }
}
