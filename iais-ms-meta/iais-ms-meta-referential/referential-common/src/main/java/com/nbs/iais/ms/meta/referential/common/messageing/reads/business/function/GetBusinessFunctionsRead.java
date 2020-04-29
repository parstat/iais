package com.nbs.iais.ms.meta.referential.common.messageing.reads.business.function;

import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetBusinessFunctionsRead extends AbstractRead<DTOList<BusinessFunctionDTO>> {

    private static final long serialVersionUID = 200L;

    public GetBusinessFunctionsRead() {
        super();
    }

}
