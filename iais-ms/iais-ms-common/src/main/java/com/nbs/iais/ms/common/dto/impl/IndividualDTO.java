package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.DivisionMiniDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DivisionMiniDTO division;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private AccountDTO account;

    public IndividualDTO() {
        super();
    }

    public IndividualDTO(final Long id) {
        super(id);
    }

    public DivisionMiniDTO getDivision() {
        return division;
    }

    public void setDivision(final DivisionMiniDTO division) {
        this.division = division;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(final AccountDTO account) {
        this.account = account;
    }
}
