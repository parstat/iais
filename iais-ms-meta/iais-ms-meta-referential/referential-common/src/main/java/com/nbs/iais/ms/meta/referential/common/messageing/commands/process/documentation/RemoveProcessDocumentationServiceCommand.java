package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.RemoveProcessDocumentationServiceEvent;

public class RemoveProcessDocumentationServiceCommand extends AbstractCommand<RemoveProcessDocumentationServiceEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;

    private Long businessService;

    private RemoveProcessDocumentationServiceCommand() {
        super(new RemoveProcessDocumentationServiceEvent());
    }

    private RemoveProcessDocumentationServiceCommand(final String jwt, final Long processDocumentation,
                                                    final Long businessService, final Language language) {
        super(new RemoveProcessDocumentationServiceEvent());
        setJwt(jwt);
        setClosed(true);
        setLanguage(language);
        this.processDocumentation = processDocumentation;
        this.businessService = businessService;
    }

    public static RemoveProcessDocumentationServiceCommand create(final String jwt, final Long processDocumentation,
                                                                   final Long businessService, final Language language) {
        return new RemoveProcessDocumentationServiceCommand(jwt, processDocumentation, businessService, language);
    }

    public Long getProcessDocumentation() {
        return processDocumentation;
    }

    public void setProcessDocumentation(final Long processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    public Long getBusinessService() {
        return businessService;
    }

    public void setBusinessService(final Long businessService) {
        this.businessService = businessService;
    }

}
