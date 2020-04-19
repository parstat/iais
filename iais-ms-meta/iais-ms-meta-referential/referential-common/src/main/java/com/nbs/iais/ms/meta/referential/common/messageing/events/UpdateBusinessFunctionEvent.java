package com.nbs.iais.ms.meta.referential.common.messageing.events;

import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class UpdateBusinessFunctionEvent extends AbstractEvent<BusinessFunctionDTO> {

    private static final long serialVersionUID = 200L;

    public UpdateBusinessFunctionEvent() {
        super();
    }

}
