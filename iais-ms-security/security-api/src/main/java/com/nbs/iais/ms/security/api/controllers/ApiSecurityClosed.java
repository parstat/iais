package com.nbs.iais.ms.security.api.controllers;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/closed/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityClosed extends AbstractController {

    @JsonView(value = Views.Secure.class)
    @GetMapping(value = "/accounts")
    public DTOList<AccountDTO> getAccounts(
            @RequestHeader(name = "jwt-auth") final String jwt,
            @PathVariable(name = "status") final AccountStatus status,
            @PathVariable(name = "language") final Language language) {

        final UUID accountId = UUID.fromString(JWT.decode(jwt).getClaim("user").asString());
        final AccountRole accountRole = AccountRole.valueOf(JWT.decode(jwt).getClaim("role").asString());
        final GetAccountsQuery getAccountsQuery = GetAccountsQuery.create(accountId, status);
        getAccountsQuery.setLanguage(language);
        getAccountsQuery.setAccountRole(accountRole);

        return send(getAccountsQuery, "security").getRead().getData();
    }
}
