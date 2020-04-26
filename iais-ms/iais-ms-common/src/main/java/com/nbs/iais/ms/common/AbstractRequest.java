package com.nbs.iais.ms.common;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;

public abstract class AbstractRequest<RES extends ResponseMessage<? extends DTO>> implements RequestMessage<RES> {

    private Language language;
    private String jwt;
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
    public String getJwt() {
        return jwt;
    }

    @Override
    public void setJwt(final String jwt) {
        this.jwt = jwt;
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
