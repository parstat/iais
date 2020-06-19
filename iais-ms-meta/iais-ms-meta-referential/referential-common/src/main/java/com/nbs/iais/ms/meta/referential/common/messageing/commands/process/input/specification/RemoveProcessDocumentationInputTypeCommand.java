package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessInputType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.input.specification.RemoveProcessDocumentationInputTypeEvent;

public class RemoveProcessDocumentationInputTypeCommand extends AbstractCommand<RemoveProcessDocumentationInputTypeEvent> {

	private static final long serialVersionUID = 200L;

	private Long documentation;

	private Long input;

	private ProcessInputType type;

	private RemoveProcessDocumentationInputTypeCommand() {
		super(new RemoveProcessDocumentationInputTypeEvent());
	}

	private RemoveProcessDocumentationInputTypeCommand(final String jwt, final Long documentation, final Long input,
													   final ProcessInputType type,
													   final Language language) {
		super(new RemoveProcessDocumentationInputTypeEvent());
		setJwt(jwt);
		this.documentation = documentation;
		this.input = input;
		this.type = type;
		setLanguage(language);
	}

	public static RemoveProcessDocumentationInputTypeCommand create(final String jwt, final Long documentation,
																	final Long input, final ProcessInputType type,
																	final Language language) {
		return new RemoveProcessDocumentationInputTypeCommand(jwt, documentation, input, type, language);
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
