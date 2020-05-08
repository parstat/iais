package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcessDocumentationOutputEvent;

public class AddProcessDocumentationOutputCommand extends AbstractCommand<AddProcessDocumentationOutputEvent> {

    private static final long serialVersionUID = 222600L;

    private Long processDocumentation;
    private Long outputSpecification;

    private AddProcessDocumentationOutputCommand() {
        super(new AddProcessDocumentationOutputEvent());
    }

    private AddProcessDocumentationOutputCommand(final String jwt, final Long processDocumentation,
                                                 final Long outputSpecification, final Language language) {

        super(new AddProcessDocumentationOutputEvent());
        this.processDocumentation = processDocumentation;
        this.outputSpecification = outputSpecification;
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationOutputCommand create(final String jwt, final Long processDocumentation,
                                                       final Long outputSpecification, final Language language) {

        return new AddProcessDocumentationOutputCommand(jwt, processDocumentation, outputSpecification, language);
    }

    public Long getStatisticalProgram() {
        return processDocumentation;
    }

    public void setStatisticalProgram(final Long processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    public Long getOutputSpecification() {
        return outputSpecification;
    }

    public void setOutputSpecification(final Long outputSpecification) {
        this.outputSpecification = outputSpecification;
    }
}
