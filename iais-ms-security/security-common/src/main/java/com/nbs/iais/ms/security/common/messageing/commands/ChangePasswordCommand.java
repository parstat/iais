package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.ChangePasswordEvent;

public class ChangePasswordCommand extends AbstractCommand<ChangePasswordEvent> {

    private static final long serialVersionUID = 200L;

    private String oldPassword;

    private String newPassword;

    private ChangePasswordCommand() {
        super(new ChangePasswordEvent());
    }

    private ChangePasswordCommand(final String oldPassword, final String newPassword) {
        super(new ChangePasswordEvent());
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public static ChangePasswordCommand create(final String oldPassword, final String newPassword) {
        return new ChangePasswordCommand(oldPassword, newPassword);
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
