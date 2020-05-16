package com.nbs.iais.ms.meta.referential.common.messageing.events.output.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessOutputSpecificationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class RemoveOutputSpecificationTypeEvent extends AbstractEvent<ProcessOutputSpecificationDTO> {

    private static final long serialVersionUID = 2011220L;

    public RemoveOutputSpecificationTypeEvent() {
        super();
    }

}
