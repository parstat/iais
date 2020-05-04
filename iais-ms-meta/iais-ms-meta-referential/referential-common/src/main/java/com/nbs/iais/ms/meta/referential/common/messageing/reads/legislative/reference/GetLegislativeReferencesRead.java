package com.nbs.iais.ms.meta.referential.common.messageing.reads.legislative.reference;

import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetLegislativeReferencesRead extends AbstractRead<DTOList<LegislativeReferenceDTO>> {

    private static final long serialVersionUID = 421100L;

    public GetLegislativeReferencesRead() {
        super();
    }

}
