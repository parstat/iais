package com.nbs.iais.ms.meta.referential.common.messageing.reads.process.documentation;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetProcessDocumentationsRead extends AbstractRead<DTOList<ProcessDocumentationDTO>> {

    private static final long serialVersionUID = 211010L;

    public GetProcessDocumentationsRead() {
        super();
    }

}
