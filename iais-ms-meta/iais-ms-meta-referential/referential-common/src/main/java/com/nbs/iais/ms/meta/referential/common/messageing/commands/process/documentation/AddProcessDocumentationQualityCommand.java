package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationQualityEvent;

public class AddProcessDocumentationQualityCommand extends AbstractCommand<AddProcessDocumentationQualityEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;
    private Long processQuality;

    private AddProcessDocumentationQualityCommand() {
        super(new AddProcessDocumentationQualityEvent());
    }

    private AddProcessDocumentationQualityCommand(final String jwt, final Long processDocumentation,
                                                 final Long processQuality, final Language language) {

        super(new AddProcessDocumentationQualityEvent());
        this.setProcessDocumentation(processDocumentation);
        this.setProcessQuality(processQuality);
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationQualityCommand create(final String jwt, final Long processDocumentation,
                                                       final Long processQuality, final Language language) {

        return new AddProcessDocumentationQualityCommand(jwt, processDocumentation, processQuality, language);
    }

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

	public Long getProcessQuality() {
		return processQuality;
	}

	public void setProcessQuality(Long processQuality) {
		this.processQuality = processQuality;
	}


}
