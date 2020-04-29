package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.DeleteStatisticalProgramEvent;

public class DeleteStatisticalProgramCommand extends AbstractCommand<DeleteStatisticalProgramEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteStatisticalProgramCommand() {
        super(new DeleteStatisticalProgramEvent());
    }

    private DeleteStatisticalProgramCommand(final String jwt, final Long id) {
        super(new DeleteStatisticalProgramEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteStatisticalProgramCommand create(final String jwt, final Long id) {
        return new DeleteStatisticalProgramCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
