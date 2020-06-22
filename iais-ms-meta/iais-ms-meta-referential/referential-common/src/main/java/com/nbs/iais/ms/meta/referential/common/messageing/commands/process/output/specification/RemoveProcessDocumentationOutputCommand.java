package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.output.specification.RemoveProcessDocumentationOutputEvent;

public class RemoveProcessDocumentationOutputCommand extends AbstractCommand<RemoveProcessDocumentationOutputEvent> {

    private static final long serialVersionUID = 200L;

    private Long documentation;

    private Long output;

    private RemoveProcessDocumentationOutputCommand() {
        super(new RemoveProcessDocumentationOutputEvent());
    }

    private RemoveProcessDocumentationOutputCommand(final String jwt, final Long documentation, final Long output,
                                                    final Language language) {
        super(new RemoveProcessDocumentationOutputEvent());
        setJwt(jwt);
        setLanguage(language);
        setClosed(true);
        this.output = output;
        this.documentation = documentation;
    }

    public static RemoveProcessDocumentationOutputCommand create(final String jwt, final Long documentation,
                                                                 final Long output, final Language language) {
        return new RemoveProcessDocumentationOutputCommand(jwt, documentation, output, language);
    }

    public Long getOutput() {
        return output;
    }

    public void setOutput(Long output) {
        this.output = output;
    }

    public Long getDocumentation() {
        return documentation;
    }

    public void setDocumentation(final Long documentation) {
        this.documentation = documentation;
    }
}
