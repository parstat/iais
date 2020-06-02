package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.method.DeleteProcessMethodEvent;

public class DeleteProcessMethodCommand extends AbstractCommand<DeleteProcessMethodEvent> {

    private static final long serialVersionUID = 2300L;

    /**
     * Id of Process Method to delete
     */
    private Long id;

    private DeleteProcessMethodCommand() {
        super(new DeleteProcessMethodEvent());
    }

    private DeleteProcessMethodCommand(final String jwt, final Long id) {

        super(new DeleteProcessMethodEvent());
        this.id = id;
        setJwt(jwt);
        setClosed(true);
    }

    public static DeleteProcessMethodCommand create(final String jwt, final Long id) {
        return new DeleteProcessMethodCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
