package com.nbs.iais.ms.meta.referential.common.messageing.reads.business.service;

import com.nbs.iais.ms.common.dto.impl.BusinessServiceDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetBusinessServicesRead extends AbstractRead<DTOList<BusinessServiceDTO>> {

    private static final long serialVersionUID = 200L;

    public GetBusinessServicesRead() {
        super();
    }

}
