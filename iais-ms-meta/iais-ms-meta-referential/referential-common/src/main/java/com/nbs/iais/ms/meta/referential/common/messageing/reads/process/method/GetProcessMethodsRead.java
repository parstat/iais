package com.nbs.iais.ms.meta.referential.common.messageing.reads.process.method;

import com.nbs.iais.ms.common.dto.impl.ProcessMethodDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetProcessMethodsRead extends AbstractRead<DTOList<ProcessMethodDTO>> {

    private static final long serialVersionUID = 2004L;

    public GetProcessMethodsRead() {
        super();
    }

}
