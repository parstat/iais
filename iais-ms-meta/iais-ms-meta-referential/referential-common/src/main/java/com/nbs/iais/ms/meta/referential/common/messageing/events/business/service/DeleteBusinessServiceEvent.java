package com.nbs.iais.ms.meta.referential.common.messageing.events.business.service;

import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class DeleteBusinessServiceEvent extends AbstractEvent<DTOBoolean> {

    private static final long serialVersionUID = 200L;

    public DeleteBusinessServiceEvent() {
        super();
    }
}
