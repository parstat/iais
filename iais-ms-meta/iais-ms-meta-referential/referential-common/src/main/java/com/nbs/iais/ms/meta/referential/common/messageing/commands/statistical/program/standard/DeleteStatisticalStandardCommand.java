package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.standard;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.standard.DeleteStatisticalStandardEvent;

public class DeleteStatisticalStandardCommand extends AbstractCommand<DeleteStatisticalStandardEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteStatisticalStandardCommand() {
        super(new DeleteStatisticalStandardEvent());
    }

    private DeleteStatisticalStandardCommand(final String jwt, final Long id) {
        super(new DeleteStatisticalStandardEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteStatisticalStandardCommand create(final String jwt, final Long id) {
        return new DeleteStatisticalStandardCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
