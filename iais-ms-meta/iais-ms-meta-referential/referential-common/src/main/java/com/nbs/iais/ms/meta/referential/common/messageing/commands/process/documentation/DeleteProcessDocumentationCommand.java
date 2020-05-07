package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.DeleteProcessDocumentationEvent;

public class DeleteProcessDocumentationCommand extends AbstractCommand<DeleteProcessDocumentationEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteProcessDocumentationCommand() {
        super(new DeleteProcessDocumentationEvent());
    }

    private DeleteProcessDocumentationCommand(final String jwt, final Long id) {
        super(new DeleteProcessDocumentationEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteProcessDocumentationCommand create(final String jwt, final Long id) {
        return new DeleteProcessDocumentationCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
