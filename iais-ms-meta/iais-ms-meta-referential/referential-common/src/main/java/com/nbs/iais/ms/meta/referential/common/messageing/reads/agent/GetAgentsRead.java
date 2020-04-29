package com.nbs.iais.ms.meta.referential.common.messageing.reads.agent;

import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetAgentsRead extends AbstractRead<DTOList<AgentDTO>> {

    private static final long serialVersionUID = 200L;

    public GetAgentsRead() {
        super();
    }

}
