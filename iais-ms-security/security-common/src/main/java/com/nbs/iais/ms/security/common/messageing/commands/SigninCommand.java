package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.SigninEvent;

public class SigninCommand extends AbstractCommand<SigninEvent> {

    private String username;
    private String password;

    public SigninCommand() {
        super(new SigninEvent());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
