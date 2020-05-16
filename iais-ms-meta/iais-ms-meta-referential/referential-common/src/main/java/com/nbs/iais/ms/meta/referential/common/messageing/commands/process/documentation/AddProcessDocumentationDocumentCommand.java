package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationDocumentEvent;

public class AddProcessDocumentationDocumentCommand extends AbstractCommand<AddProcessDocumentationDocumentEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;
    
    private Long processDocument;

    private AddProcessDocumentationDocumentCommand() {
        super(new AddProcessDocumentationDocumentEvent());
    }

    private AddProcessDocumentationDocumentCommand(final String jwt, final Long processDocumentation,
                                                 final Long processDocument, final Language language) {

        super(new AddProcessDocumentationDocumentEvent());
        this.setProcessDocumentation(processDocumentation);
        this.processDocument = processDocument;
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationDocumentCommand create(final String jwt, final Long processDocumentation,
                                                       final Long processDocument, final Language language) {

        return new AddProcessDocumentationDocumentCommand(jwt, processDocumentation, processDocument, language);
    }

   
    public Long getProcessDocument() {
        return processDocument;
    }

    public void setProcessDocument(final Long processDocument) {
        this.processDocument = processDocument;
    }

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}
}
