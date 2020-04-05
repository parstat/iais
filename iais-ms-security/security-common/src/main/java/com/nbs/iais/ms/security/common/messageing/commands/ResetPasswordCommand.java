package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.ResetPasswordEvent;

public class ResetPasswordCommand extends AbstractCommand<ResetPasswordEvent> {

    private static final long serialVersionUID = 200L;

    private String confirmation;

    private String newPassword;

    private ResetPasswordCommand() {
        super(new ResetPasswordEvent());
    }

    private ResetPasswordCommand(final String confirmation, final String newPassword) {
        super(new ResetPasswordEvent());
        this.confirmation = confirmation;
        this.newPassword = newPassword;
    }

    public static ResetPasswordCommand create(final String confirmation, final String newPassword) {
        return new ResetPasswordCommand(confirmation, newPassword);
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(final String confirmation) {
        this.confirmation = confirmation;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
