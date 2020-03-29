package com.nbs.iais.ms.security.api.controllers;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityOpen extends AbstractController {

    @PostMapping(value = "/signin")
    public AccountDTO signin(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "password") final String password) {
        //TODO decide if the communication with db will be with messages or direct
        //currently is direct
        //using internal messages can help dividing it later into micro services

        final SigninCommand signinCommand = new SigninCommand();
        signinCommand.setUsername(username);
        signinCommand.setPassword(password);

        return send(signinCommand, "security").getEvent().getData();
    }

    @PostMapping
    public AccountDTO signup(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "password") final String password,
            @RequestParam(name = "email") final String email,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "role", required = false) final AccountRole role) {

        final SignupCommand signupCommand = new SignupCommand();
        signupCommand.setAccountId(UUID.randomUUID());
        signupCommand.setUsername(username);
        signupCommand.setPassword(password);
        signupCommand.setEmail(email);
        signupCommand.setName(name);
        signupCommand.setRole(role);

        return send(signupCommand, "security").getEvent().getData();
    }
}
