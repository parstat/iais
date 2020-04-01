package com.nbs.iais.ms.security.common.messageing.reads;

import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetAccountsRead extends AbstractRead<DTOList<AccountDTO>> {

    private static final long serialVersionUID = 200L;

    public GetAccountsRead() {
        super();
    }

}
