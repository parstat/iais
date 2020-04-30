package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.agent.DeleteAgentEvent;

public class DeleteAgentCommand extends AbstractCommand<DeleteAgentEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteAgentCommand() {
        super(new DeleteAgentEvent());
    }

    private DeleteAgentCommand(final String jwt, final Long id) {
        super(new DeleteAgentEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteAgentCommand create(final String jwt, final Long id) {
        return new DeleteAgentCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
