package com.nbs.iais.ms.meta.referential.common.messageing.events.agent;

import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class DeleteAgentEvent extends AbstractEvent<DTOBoolean> {

    private static final long serialVersionUID = 200L;

    public DeleteAgentEvent() {
        super();
    }
}
