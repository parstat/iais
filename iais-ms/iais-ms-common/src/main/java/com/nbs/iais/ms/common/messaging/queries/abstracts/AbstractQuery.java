package com.nbs.iais.ms.common.messaging.queries.abstracts;

import com.nbs.iais.ms.common.AbstractRequest;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public abstract class AbstractQuery<RES extends AbstractRead<? extends DTO>> extends AbstractRequest<RES> implements Query<RES> {

    private RES read;

    protected AbstractQuery() {

    }

    protected AbstractQuery(final RES read) {
        this.read = read;
    }

    @Override
    public RES getRead() {
        return read;
    }

    @Override
    public RES getReply() {
        return getRead();
    }



}
