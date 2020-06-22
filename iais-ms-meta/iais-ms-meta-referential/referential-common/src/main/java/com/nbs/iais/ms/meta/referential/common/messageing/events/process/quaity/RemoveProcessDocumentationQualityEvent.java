package com.nbs.iais.ms.meta.referential.common.messageing.events.process.quaity;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class RemoveProcessDocumentationQualityEvent extends AbstractEvent<ProcessDocumentationDTO> {

    private static final long serialVersionUID = 222200L;

    public RemoveProcessDocumentationQualityEvent() {
        super();
    }
}
