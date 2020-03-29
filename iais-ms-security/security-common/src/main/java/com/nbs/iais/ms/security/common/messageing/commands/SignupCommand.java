package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.SignupEvent;

public class SignupCommand extends AbstractCommand<SignupEvent> {

    private String username;
    private String password;
    private String email;
    private String name;
    private AccountRole role;

    public SignupCommand() {
        super(new SignupEvent());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(final AccountRole role) {
        this.role = role;
    }
}
