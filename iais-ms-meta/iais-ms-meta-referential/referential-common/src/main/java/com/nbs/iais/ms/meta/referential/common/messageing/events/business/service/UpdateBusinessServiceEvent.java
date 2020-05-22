package com.nbs.iais.ms.meta.referential.common.messageing.events.business.service;

import com.nbs.iais.ms.common.dto.impl.BusinessServiceDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class UpdateBusinessServiceEvent extends AbstractEvent<BusinessServiceDTO> {

    private static final long serialVersionUID = 200L;

    public UpdateBusinessServiceEvent() {
        super();
    }
}
