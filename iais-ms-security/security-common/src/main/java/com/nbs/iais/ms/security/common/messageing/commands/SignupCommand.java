package com.nbs.iais.ms.security.common.messageing.commands;

import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.common.messageing.events.SignupEvent;

import java.util.UUID;

public class SignupCommand extends AbstractCommand<SignupEvent> {

    private static final long serialVersionUID = 200L;

    private String username;
    private String password;
    private String email;
    private String name;
    private AccountRole role;

    private SignupCommand() {
        super(new SignupEvent());
    }

    private SignupCommand(final String username, final String password, final String email, final String name,
                          final AccountRole role) {
        super(new SignupEvent());
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        setAccountId(UUID.randomUUID());
    }

    public static SignupCommand create(final String username, final String password, final String email,
                                       final String name, final AccountRole role) {
        return new SignupCommand(username, password, email, name, role);
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
