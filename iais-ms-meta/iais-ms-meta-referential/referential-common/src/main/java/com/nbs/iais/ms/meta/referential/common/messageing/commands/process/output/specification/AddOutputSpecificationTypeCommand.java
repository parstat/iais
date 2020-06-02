package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessOutputType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.output.specification.AddOutputSpecificationTypeEvent;

public class AddOutputSpecificationTypeCommand extends AbstractCommand<AddOutputSpecificationTypeEvent> {

	private static final long serialVersionUID = 200L;

	private Long id;

	private ProcessOutputType type;

	private AddOutputSpecificationTypeCommand() {
		super(new AddOutputSpecificationTypeEvent());
	}

	private AddOutputSpecificationTypeCommand(final String jwt, final Long id, final ProcessOutputType type,
			final Language language) {
		super(new AddOutputSpecificationTypeEvent());
		setJwt(jwt);
		this.id = id;
		this.type = type;
 		setLanguage(language);
	}

	public static AddOutputSpecificationTypeCommand create(final String jwt, final Long id,
			final ProcessOutputType type, final Language language) {
		return new AddOutputSpecificationTypeCommand(jwt, id, type, language);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProcessOutputType getType() {
		return type;
	}

	public void setType(ProcessOutputType type) {
		this.type = type;
	}
}
