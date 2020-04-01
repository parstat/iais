package com.nbs.iais.ms.security.common.messageing.events;

import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class SigninEvent extends AbstractEvent<AccountDTO> {

    private static final long serialVersionUID = 200L;

    public SigninEvent() {
        super();
    }

}
