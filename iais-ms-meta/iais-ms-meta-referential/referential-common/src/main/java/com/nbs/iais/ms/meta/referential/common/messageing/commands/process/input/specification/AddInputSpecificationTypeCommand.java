package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessInputType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.input.specification.AddInputSpecificationTypeEvent;

public class AddInputSpecificationTypeCommand extends AbstractCommand<AddInputSpecificationTypeEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;
    
    private ProcessInputType type;

    private AddInputSpecificationTypeCommand() {
        super(new AddInputSpecificationTypeEvent());
    }

    private AddInputSpecificationTypeCommand(final String jwt, final Long id,final ProcessInputType type, final Language language) {
        super(new AddInputSpecificationTypeEvent());
        setJwt(jwt);
        this.id = id;
        this.type=type;
        setLanguage(language);
    }

    public static AddInputSpecificationTypeCommand create(final String jwt, final Long id,final ProcessInputType type, final Language language) {
        return new AddInputSpecificationTypeCommand(jwt, id,type,language);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public ProcessInputType getType() {
		return type;
	}

	public void setType(ProcessInputType type) {
		this.type = type;
	}
}
