package com.nbs.iais.ms.security.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.security.common.messageing.commands.ChangePasswordCommand;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountQuery;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/closed/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityClosed extends AbstractController {

    private static Logger LOG = LoggerFactory.getLogger(ApiSecurityClosed.class);
    /**
     * Method to get account by their status
     * @param jwt on the header of the request
     * @param status get users by status
     * @param name or users by name
     * @param language to use
     * @return List of AccountDTO
     */
    @JsonView(value = Views.Secure.class)
    @GetMapping(value = "/accounts")
    public DTOList<AccountDTO> getAccounts(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @RequestParam(name = "status", required = false) final AccountStatus status,
            @RequestParam(name = "name", required = false) final String name,
            @RequestParam(name = "language") final Language language) {

        final GetAccountsQuery getAccountsQuery = GetAccountsQuery.create(jwt, status, name);
        getAccountsQuery.setLanguage(language);
        getAccountsQuery.setClosed(true);

        LOG.debug("Sending GetAccountsQuery: {}", getAccountsQuery.toString());
        return sendQuery(getAccountsQuery, "security").getRead().getData();
    }

    /**
     * API method to get account by Id
     * @param jwt should be in the header of the request
     * @param id of the requested user
     * @param language to use
     * @return requested accountDTO
     */
    @JsonView(value = Views.Secure.class)
    @GetMapping(value = "/accounts/{id}")
    public AccountDTO getAccountById(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "id") final Long id,
            @RequestParam(name = "language") final String language) {

        final GetAccountQuery getAccountQuery = GetAccountQuery.create(id);
        getAccountQuery.setLanguage(Language.getLanguage(language));
        getAccountQuery.setJwt(jwt);
        getAccountQuery.setClosed(true);

        return sendQuery(getAccountQuery, "security").getRead().getData();
    }

    /**
     * API method to get account by username or email
     * @param jwt should be in the header of the request
     * @param username of the requested user
     * @param language to use
     * @return requested accountDTO
     */
    @JsonView(value = Views.Secure.class)
    @GetMapping(value = "/accounts/username/{username}")
    public AccountDTO getAccountByUsername(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "username") final String username,
            @RequestParam(name = "language") final Language language) {

        final GetAccountQuery getAccountQuery = GetAccountQuery.create(username);
        getAccountQuery.setLanguage(language);
        getAccountQuery.setJwt(jwt);
        getAccountQuery.setClosed(true);

        return sendQuery(getAccountQuery, "security").getRead().getData();
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

        final ChangePasswordCommand command = ChangePasswordCommand.create(oldPassword, newPassword);

        command.setLanguage(language);
        command.setJwt(jwt);
        command.setClosed(true);

        return sendCommand(command, "security").getEvent().getData();
    }
}
