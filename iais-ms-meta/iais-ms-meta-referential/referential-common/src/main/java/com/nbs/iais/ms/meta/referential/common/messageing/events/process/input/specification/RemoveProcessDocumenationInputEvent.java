package com.nbs.iais.ms.meta.referential.common.messageing.events.process.input.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class RemoveProcessDocumenationInputEvent extends AbstractEvent<ProcessDocumentationDTO> {

    private static final long serialVersionUID = 22200L;

    public RemoveProcessDocumenationInputEvent() {
        super();
    }
}
