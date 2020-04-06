package com.nbs.iais.ms.security.api.controllers;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.security.common.messageing.commands.ChangePasswordCommand;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/closed/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityClosed extends AbstractController {

    @JsonView(value = Views.Secure.class)
    @GetMapping(value = "/accounts/status/{status}")
    public DTOList<AccountDTO> getAccounts(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "status") final AccountStatus status,
            @RequestParam(name = "language") final Language language) {

        final Long accountId = JWT.decode(jwt).getClaim("user").asLong();
        final AccountRole accountRole = AccountRole.valueOf(JWT.decode(jwt).getClaim("role").asString());
        final GetAccountsQuery getAccountsQuery = GetAccountsQuery.create(accountId, status);
        getAccountsQuery.setLanguage(language);
        getAccountsQuery.setAccountRole(accountRole);

        return sendQuery(getAccountsQuery, "security").getRead().getData();
    }

    @JsonView(value = Views.Secure.class)
    @GetMapping(value = "/accounts/name/{name}")
    public DTOList<AccountDTO> getAccountsByName(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "name") final String name,
            @RequestParam(name = "language") final Language language) {

        final Long accountId = JWT.decode(jwt).getClaim("user").asLong();
        final AccountRole accountRole = AccountRole.valueOf(JWT.decode(jwt).getClaim("role").asString());
        final GetAccountsQuery getAccountsQuery = GetAccountsQuery.create(accountId, AccountStatus.ACTIVE, name);
        getAccountsQuery.setLanguage(language);
        getAccountsQuery.setAccountRole(accountRole);

        return sendQuery(getAccountsQuery, "security").getRead().getData();
    }

    /**
     * API method to change the password when the user has not lost it yet
     * @param jwt the jwt in the header of the request (user should be logged in order to change the password)
     * @param oldPassword (old password of the user)
     * @param newPassword (new password the user wants to use)
     * @param language language to use
     * @return DTOBoolean true if the password is changed
     */
    @JsonView(value = Views.Personal.class)
    @PostMapping(value = "/password/change")
    public DTOBoolean changePassword(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "oldPassword") final String oldPassword,
            @RequestParam(name = "newPassword") final String newPassword,
            @RequestParam(name = "language") final Language language) {

        final Long accountId = JWT.decode(jwt).getClaim("user").asLong();
        final AccountRole accountRole = AccountRole.valueOf(JWT.decode(jwt).getClaim("role").asString());
        final ChangePasswordCommand command = ChangePasswordCommand.create(oldPassword, newPassword);

        command.setLanguage(language);
        command.setAccountId(accountId);
        command.setAccountRole(accountRole);

        return sendCommand(command, "security").getEvent().getData();
    }
}
