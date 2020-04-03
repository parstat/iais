package com.nbs.iais.ms.referential.common.messageing.reads;

import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetStatisticalProcessesRead extends AbstractRead<DTOList<StatisticalProcessDTO>> {

    private static final long serialVersionUID = 200L;

    public GetStatisticalProcessesRead() {
        super();
    }

}
