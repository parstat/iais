package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.document.RemoveProcessDocumentationDocumentEvent;

public class RemoveProcessDocumentationDocumentCommand extends AbstractCommand<RemoveProcessDocumentationDocumentEvent> {

    private static final long serialVersionUID = 200L;

    private Long documentation;

    private Long document;

    private RemoveProcessDocumentationDocumentCommand() {
        super(new RemoveProcessDocumentationDocumentEvent());
    }

    private RemoveProcessDocumentationDocumentCommand(final String jwt, final Long documentation, final Long document,
                                                      final Language language) {
        super(new RemoveProcessDocumentationDocumentEvent());
        setJwt(jwt);
        setLanguage(language);
        setClosed(true);
        this.document = document;
    }

    public static RemoveProcessDocumentationDocumentCommand create(final String jwt, final Long documentation,
                                                                   final Long document, final Language language) {
        return new RemoveProcessDocumentationDocumentCommand(jwt, documentation, document, language);
    }

    public Long getDocumentation() {
        return documentation;
    }

    public void setDocumentation(final Long documentation) {
        this.documentation = documentation;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(final Long document) {
        this.document = document;
    }
}
