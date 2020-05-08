package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.AddProcesDocumentationInputEvent;

public class AddProcessDocumentationInputCommand extends AbstractCommand<AddProcesDocumentationInputEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;
    private Long inputSpecification;

    private AddProcessDocumentationInputCommand() {
        super(new AddProcesDocumentationInputEvent());
    }

    private AddProcessDocumentationInputCommand(final String jwt, final Long processDocumentation,
                                                 final Long inputSpecification, final Language language) {

        super(new AddProcesDocumentationInputEvent());
        this.setProcessDocumentation(processDocumentation);
        this.setInputSpecification(inputSpecification);
        setJwt(jwt);
        setLanguage(language);

    }

    public static AddProcessDocumentationInputCommand create(final String jwt, final Long processDocumentation,
                                                       final Long inputSpecification, final Language language) {

        return new AddProcessDocumentationInputCommand(jwt, processDocumentation, inputSpecification, language);
    }

	public Long getProcessDocumentation() {
		return processDocumentation;
	}

	public void setProcessDocumentation(Long processDocumentation) {
		this.processDocumentation = processDocumentation;
	}

	public Long getInputSpecification() {
		return inputSpecification;
	}

	public void setInputSpecification(Long inputSpecification) {
		this.inputSpecification = inputSpecification;
	}

   
    
}
