package com.nbs.iais.ms.meta.referential.common.messageing.events.agent;

import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class CreateAgentEvent extends AbstractEvent<AgentDTO> {

    private static final long serialVersionUID = 200L;

    public CreateAgentEvent() {
        super();
    }

}
