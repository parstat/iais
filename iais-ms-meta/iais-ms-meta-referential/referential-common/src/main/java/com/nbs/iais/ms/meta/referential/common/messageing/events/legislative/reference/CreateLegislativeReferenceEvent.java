package com.nbs.iais.ms.meta.referential.common.messageing.events.legislative.reference;

import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class CreateLegislativeReferenceEvent extends AbstractEvent<LegislativeReferenceDTO> {

    private static final long serialVersionUID = 20110L;

    public CreateLegislativeReferenceEvent() {
        super();
    }

}
