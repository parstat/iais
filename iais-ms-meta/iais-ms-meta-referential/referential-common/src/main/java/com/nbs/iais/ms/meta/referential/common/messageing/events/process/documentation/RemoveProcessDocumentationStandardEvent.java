package com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class RemoveProcessDocumentationStandardEvent extends AbstractEvent<ProcessDocumentationDTO> {

    private static final long serialVersionUID = 200L;

    public RemoveProcessDocumentationStandardEvent() {
        super();
    }

}

