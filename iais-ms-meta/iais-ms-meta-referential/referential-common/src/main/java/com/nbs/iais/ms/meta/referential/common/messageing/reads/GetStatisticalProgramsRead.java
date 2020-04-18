package com.nbs.iais.ms.meta.referential.common.messageing.reads;

import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetStatisticalProgramsRead extends AbstractRead<DTOList<StatisticalProgramDTO>> {

    private static final long serialVersionUID = 200L;

    public GetStatisticalProgramsRead() {
        super();
    }

}
