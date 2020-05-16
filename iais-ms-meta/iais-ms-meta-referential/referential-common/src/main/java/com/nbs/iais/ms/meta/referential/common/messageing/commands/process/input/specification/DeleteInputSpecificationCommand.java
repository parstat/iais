package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.input.specification;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.input.specification.DeleteInputSpecificationEvent;

public class DeleteInputSpecificationCommand extends AbstractCommand<DeleteInputSpecificationEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteInputSpecificationCommand() {
        super(new DeleteInputSpecificationEvent());
    }

    private DeleteInputSpecificationCommand(final String jwt, final Long id) {
        super(new DeleteInputSpecificationEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteInputSpecificationCommand create(final String jwt, final Long id) {
        return new DeleteInputSpecificationCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
