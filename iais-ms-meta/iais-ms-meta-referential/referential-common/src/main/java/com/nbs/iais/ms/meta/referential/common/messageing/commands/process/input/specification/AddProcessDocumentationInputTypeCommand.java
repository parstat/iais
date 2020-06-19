package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessInputType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.input.specification.AddProcessDocumentationInputTypeEvent;

public class AddProcessDocumentationInputTypeCommand extends AbstractCommand<AddProcessDocumentationInputTypeEvent> {

    private static final long serialVersionUID = 200L;

    private Long documentation;

    private Long input;
    
    private ProcessInputType type;

    private AddProcessDocumentationInputTypeCommand() {
        super(new AddProcessDocumentationInputTypeEvent());
    }

    private AddProcessDocumentationInputTypeCommand(final String jwt, final Long documentation, final Long input,
                                                    final ProcessInputType type, final Language language) {
        super(new AddProcessDocumentationInputTypeEvent());
        setJwt(jwt);
        this.input = input;
        this.type=type;
        this.documentation = documentation;
        setLanguage(language);
        setClosed(true);
    }

    public static AddProcessDocumentationInputTypeCommand create(final String jwt, final Long documentation,
                                                                 final Long input, final ProcessInputType type,
                                                                 final Language language) {
        return new AddProcessDocumentationInputTypeCommand(jwt, documentation, input,type,language);
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

    public ProcessInputType getType() {
		return type;
	}

	public void setType(final ProcessInputType type) {
		this.type = type;
	}
}
