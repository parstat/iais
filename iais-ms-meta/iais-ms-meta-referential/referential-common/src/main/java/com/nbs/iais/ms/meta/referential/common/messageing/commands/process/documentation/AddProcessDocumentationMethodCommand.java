package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationMethodEvent;

public class AddProcessDocumentationMethodCommand extends AbstractCommand<AddProcessDocumentationMethodEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;
    private Long processMethod;

    private AddProcessDocumentationMethodCommand() {
        super(new AddProcessDocumentationMethodEvent());
    }

    private AddProcessDocumentationMethodCommand(final String jwt, final Long processDocumentation,
                                                 final Long processMethod, final Language language) {

        super(new AddProcessDocumentationMethodEvent());
        this.setProcessDocumentation(processDocumentation);
        this.setProcessMethod(processMethod);
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationMethodCommand create(final String jwt, final Long processDocumentation,
                                                       final Long processMethod, final Language language) {

        return new AddProcessDocumentationMethodCommand(jwt, processDocumentation, processMethod, language);
    }

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

	public Long getProcessMethod() {
		return processMethod;
	}

	public void setProcessMethod(Long processMethod) {
		this.processMethod = processMethod;
	}

   
}
