package com.nbs.iais.ms.security.common.messageing.events;

import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class SendResetLinkEvent extends AbstractEvent<DTOBoolean> {

    private static final long serialVersionUID = 200L;

    public SendResetLinkEvent() {
        super();
    }

}
