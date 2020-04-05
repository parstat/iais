package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.ConfirmEmailEvent;

public class ConfirmEmailCommand extends AbstractCommand<ConfirmEmailEvent> {

    private static final long serialVersionUID = 200L;

    private String confirmation;

    private ConfirmEmailCommand() {
        super(new ConfirmEmailEvent());
    }

    private ConfirmEmailCommand(final String confirmation) {
        super(new ConfirmEmailEvent());
        this.confirmation = confirmation;
    }

    public static ConfirmEmailCommand create(final String confirmation) {
        return new ConfirmEmailCommand(confirmation);
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(final String confirmation) {
        this.confirmation = confirmation;
    }
}
