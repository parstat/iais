package com.nbs.iais.ms.meta.referential.common.messageing.events.process.document;

import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class DeleteProcessDocumentEvent extends AbstractEvent<DTOBoolean> {

    private static final long serialVersionUID = 222200L;

    public DeleteProcessDocumentEvent() {
        super();
    }
}
