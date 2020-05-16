package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.output.specification;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.output.specification.DeleteOutputSpecificationEvent;

public class DeleteOutputSpecificationCommand extends AbstractCommand<DeleteOutputSpecificationEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteOutputSpecificationCommand() {
        super(new DeleteOutputSpecificationEvent());
    }

    private DeleteOutputSpecificationCommand(final String jwt, final Long id) {
        super(new DeleteOutputSpecificationEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteOutputSpecificationCommand create(final String jwt, final Long id) {
        return new DeleteOutputSpecificationCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
