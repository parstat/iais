package com.nbs.iais.ms.security.api.controllers;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AccountStatus;
import com.nbs.iais.ms.security.common.messageing.queries.GetAccountsQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/closed/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityClosed extends AbstractController {

    @GetMapping(value = "/accounts")
    public DTOList<AccountDTO> getAccounts(
            @PathVariable(name = "status") final AccountStatus status) {

        final GetAccountsQuery getAccountsQuery = GetAccountsQuery.create(status);

        return send(getAccountsQuery, "security").getRead().getData();
    }
}
