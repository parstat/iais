package com.nbs.iais.ms.meta.referential.common.messageing.events.process.quaity;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.impl.ProcessQualityDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class UpdateProcessQualityEvent extends AbstractEvent<ProcessDocumentationDTO> {

    private static final long serialVersionUID = 201212520L;

    public UpdateProcessQualityEvent() {
        super();
    }

}
