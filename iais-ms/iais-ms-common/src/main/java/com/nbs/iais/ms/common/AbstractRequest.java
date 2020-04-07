package com.nbs.iais.ms.common;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;

public abstract class AbstractRequest<RES extends ResponseMessage<? extends DTO>> implements RequestMessage<RES> {

    private Language language;
    private Long accountId;
    private AccountRole accountRole;
    private boolean closed = false;

    public AbstractRequest() {
        super();
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(final Language language) {
        this.language = language;
    }

    @Override
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public AccountRole getAccountRole() {
        return accountRole;
    }

    @Override
    public void setAccountRole(final AccountRole accountRole) {
        this.accountRole = accountRole;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void setClosed(final boolean closed) {
        this.closed = closed;
    }
}
