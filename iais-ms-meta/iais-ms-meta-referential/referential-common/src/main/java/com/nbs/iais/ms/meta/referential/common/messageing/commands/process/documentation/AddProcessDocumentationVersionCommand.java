package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationVersionEvent;

public class AddProcessDocumentationVersionCommand extends AbstractCommand<AddProcessDocumentationVersionEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;
    private Long processVersion;

    private AddProcessDocumentationVersionCommand() {
        super(new AddProcessDocumentationVersionEvent());
    }

    private AddProcessDocumentationVersionCommand(final String jwt, final Long processDocumentation,
                                                 final Long processVersion, final Language language) {

        super(new AddProcessDocumentationVersionEvent());
        this.setProcessDocumentation(processDocumentation);
        this.setProcessVersion(processVersion);
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationVersionCommand create(final String jwt, final Long processDocumentation,
                                                       final Long processVersion, final Language language) {

        return new AddProcessDocumentationVersionCommand(jwt, processDocumentation, processVersion, language);
    }

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

	public Long getProcessVersion() {
		return processVersion;
	}

	public void setProcessVersion(Long processVersion) {
		this.processVersion = processVersion;
	}

    
}
