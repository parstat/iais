package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.document.DeleteProcessDocumentEvent;

public class DeleteProcessDocumentCommand extends AbstractCommand<DeleteProcessDocumentEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteProcessDocumentCommand() {
        super(new DeleteProcessDocumentEvent());
    }

    private DeleteProcessDocumentCommand(final String jwt, final Long id) {
        super(new DeleteProcessDocumentEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteProcessDocumentCommand create(final String jwt, final Long id) {
        return new DeleteProcessDocumentCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
