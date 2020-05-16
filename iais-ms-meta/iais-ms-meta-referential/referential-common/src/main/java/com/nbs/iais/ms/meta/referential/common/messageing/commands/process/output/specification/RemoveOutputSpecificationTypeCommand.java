package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessOutputType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.output.specification.RemoveOutputSpecificationTypeEvent;

public class RemoveOutputSpecificationTypeCommand extends AbstractCommand<RemoveOutputSpecificationTypeEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;
    
    private ProcessOutputType type;

    private RemoveOutputSpecificationTypeCommand() {
        super(new RemoveOutputSpecificationTypeEvent());
    }

    private RemoveOutputSpecificationTypeCommand(final String jwt, final Long id,final ProcessOutputType type,final Language language) {
        super(new RemoveOutputSpecificationTypeEvent());
        setJwt(jwt);
        this.id = id;
        this.type=type;
        setLanguage(language);
    }

    public static RemoveOutputSpecificationTypeCommand create(final String jwt, final Long id,final ProcessOutputType type,final Language language) {
        return new RemoveOutputSpecificationTypeCommand(jwt, id,type,language);
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
