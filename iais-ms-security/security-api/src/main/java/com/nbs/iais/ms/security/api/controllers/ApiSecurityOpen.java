package com.nbs.iais.ms.security.api.controllers;

import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.security.db.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping(value = "/api/v1/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityOpen {

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/signin")
    public AccountDTO signin(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "password") final String password) {
        //TODO decide if the communication with db will be with messages or direct
        //currently is direct
        //using internal messages can help dividing it later into micro services
        return securityService.signin(username, password);
    }

    @PostMapping
    public AccountDTO signup(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "password") final String password,
            @RequestParam(name = "email") final String email,
            @RequestParam(name = "name") final String name,
            @RequestParam(name = "role", required = false) final AccountRole role) {

        return securityService.signup(UUID.randomUUID(), username, password, name, email, role);
    }
}
