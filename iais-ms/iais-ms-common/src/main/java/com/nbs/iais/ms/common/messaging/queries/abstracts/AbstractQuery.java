package com.nbs.iais.ms.common.messaging.queries.abstracts;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

import java.util.UUID;

public abstract class AbstractQuery<R extends AbstractRead<? extends DTO>> implements Query<R> {

    private R read;
    private Language language;
    private UUID accountId;

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
    public UUID getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(final UUID accountId) {
        this.accountId = accountId;
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
