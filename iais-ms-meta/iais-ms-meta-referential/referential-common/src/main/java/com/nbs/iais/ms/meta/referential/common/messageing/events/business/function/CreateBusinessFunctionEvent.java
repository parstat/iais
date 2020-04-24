package com.nbs.iais.ms.meta.referential.common.messageing.events.business.function;

import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class CreateBusinessFunctionEvent extends AbstractEvent<BusinessFunctionDTO> {

    private static final long serialVersionUID = 200L;

    public CreateBusinessFunctionEvent() {
        super();

    }
}
