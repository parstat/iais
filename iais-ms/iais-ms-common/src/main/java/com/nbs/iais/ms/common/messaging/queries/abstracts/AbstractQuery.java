package com.nbs.iais.ms.common.messaging.queries.abstracts;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public abstract class AbstractQuery<R extends AbstractRead<? extends DTO>> implements Query<R> {

    private R read;
    private Language language;
    private Long accountId;
    private AccountRole accountRole;

    protected AbstractQuery() {

    }

    protected AbstractQuery(final R read) {
        this.read = read;
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
    public void setAccountId(final Long accountId) {
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
    public R getRead() {
        return read;
    }

    @Override
    public R getReply() {
        return getRead();
    }



}
