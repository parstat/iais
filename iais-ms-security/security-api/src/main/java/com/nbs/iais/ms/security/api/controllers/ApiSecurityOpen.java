package com.nbs.iais.ms.security.api.controllers;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.security.common.messageing.commands.SigninCommand;
import com.nbs.iais.ms.security.common.messageing.commands.SignupCommand;
import com.nbs.iais.ms.security.db.services.CommandSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityOpen extends AbstractController {

    @Autowired
    private CommandSecurityService commandSecurityService;

    /**
     * Post method to do signin
     * @param username of the user
     * @param password of the user
     * @return AccountDTO
     */
    @PostMapping(value = "/signin")
    public AccountDTO signin(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "password") final String password) {
        //TODO decide if the communication with db will be with messages or direct
        //currently is direct
        //using internal messages can help dividing it later into micro services

        final SigninCommand signinCommand = SigninCommand.create(username, password);
        return sendCommand(signinCommand, "security").getEvent().getData();
    }

    /**
     * Post method to do a signup
     * @param username of the user
     * @param password chosen by the user
     * @param email chosen by the user
     * @param name full name of the user
     * @param role the role of the user (to be assigned by an admin)
     * @return AccountDTO
     */
    @PostMapping(value = "/signup")
    public AccountDTO signup(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "password") final String password,
            @RequestParam(name = "email") final String email,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "role", required = false) final AccountRole role,
            @RequestParam(name = "language") final Language language) {

        final SignupCommand signupCommand = SignupCommand.create(username, password, email, name, role);
        signupCommand.setLanguage(language);
        return sendCommand(signupCommand, "security").getEvent().getData();
    }

}
