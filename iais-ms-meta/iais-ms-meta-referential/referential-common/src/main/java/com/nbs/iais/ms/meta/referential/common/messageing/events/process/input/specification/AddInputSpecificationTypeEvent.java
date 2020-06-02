package com.nbs.iais.ms.meta.referential.common.messageing.events.process.input.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessInputSpecificationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class AddInputSpecificationTypeEvent extends AbstractEvent<ProcessInputSpecificationDTO> {

    private static final long serialVersionUID = 202112520L;

    public AddInputSpecificationTypeEvent() {
        super();
    }

}
