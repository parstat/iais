package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.input.specification.RemoveProcessDocumenationInputEvent;

public class RemoveProcessDocumentationInputCommand extends AbstractCommand<RemoveProcessDocumenationInputEvent> {

    private static final long serialVersionUID = 200L;

    private Long documentation;

    private Long input;

    private RemoveProcessDocumentationInputCommand() {
        super(new RemoveProcessDocumenationInputEvent());
    }

    private RemoveProcessDocumentationInputCommand(final String jwt, final Long documentation, final Long input, final Language language) {
        super(new RemoveProcessDocumenationInputEvent());
        setJwt(jwt);
        this.input = input;
        this.documentation = documentation;
        setLanguage(language);
        setClosed(true);
    }

    public static RemoveProcessDocumentationInputCommand create(final String jwt, final Long documentation,
                                                                final Long input, final Language language) {
        return new RemoveProcessDocumentationInputCommand(jwt, documentation, input, language);
    }

    public Long getDocumentation() {
        return documentation;
    }

    public void setDocumentation(final Long documentation) {
        this.documentation = documentation;
    }

    public Long getInput() {
        return input;
    }

    public void setInput(final Long input) {
        this.input = input;
    }
}
