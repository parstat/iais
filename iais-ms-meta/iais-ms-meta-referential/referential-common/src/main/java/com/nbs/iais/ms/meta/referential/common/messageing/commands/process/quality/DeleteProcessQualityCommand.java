package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.quaity.DeleteProcessQualityEvent;

public class DeleteProcessQualityCommand extends AbstractCommand<DeleteProcessQualityEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteProcessQualityCommand() {
        super(new DeleteProcessQualityEvent());
    }

    private DeleteProcessQualityCommand(final String jwt, final Long id) {
        super(new DeleteProcessQualityEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteProcessQualityCommand create(final String jwt, final Long id) {
        return new DeleteProcessQualityCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
