package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.SendResetLinkEvent;

public class SendResetLinkCommand extends AbstractCommand<SendResetLinkEvent> {

    private static final long serialVersionUID = 200L;

    private String email;

    private SendResetLinkCommand() {
        super(new SendResetLinkEvent());
    }

    private SendResetLinkCommand(final String email) {
        super(new SendResetLinkEvent());
        this.email = email;
    }

    public static SendResetLinkCommand create(final String email) {
        return new SendResetLinkCommand(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
