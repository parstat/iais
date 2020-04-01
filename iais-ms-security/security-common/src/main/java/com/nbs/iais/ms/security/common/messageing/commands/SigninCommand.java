package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.SigninEvent;

public class SigninCommand extends AbstractCommand<SigninEvent> {

    private static final long serialVersionUID = 200L;

    private String username;
    private String password;

    private SigninCommand() {
        super(new SigninEvent());
    }

    private SigninCommand(final String username, final String password) {
        super(new SigninEvent());
        this.username = username;
        this.password = password;
    }

    public static SigninCommand create(final String username, final String password) {
        return new SigninCommand(username, password);
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
