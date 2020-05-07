package com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation;

import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class DeleteProcessDocumentationEvent extends AbstractEvent<DTOBoolean> {

    private static final long serialVersionUID = 212200L;

    public DeleteProcessDocumentationEvent() {
        super();
    }
}
