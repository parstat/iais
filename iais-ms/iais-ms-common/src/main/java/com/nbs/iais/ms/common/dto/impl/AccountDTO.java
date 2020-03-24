package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.BaseEntityDTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.AccountStatus;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO extends BaseEntityDTO {

    @JsonProperty
    @JsonView(Views.Basic.class)
    private String username;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private AccountRole role;

    @JsonProperty
    private AccountStatus status;

    public AccountDTO() {
        super();
    }

    public AccountDTO(final UUID id) {
        super(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(final AccountRole role) {
        this.role = role;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(final AccountStatus status) {
        this.status = status;
    }
}
