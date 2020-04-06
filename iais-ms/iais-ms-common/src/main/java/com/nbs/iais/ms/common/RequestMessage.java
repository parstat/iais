package com.nbs.iais.ms.common;

import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;


public interface RequestMessage<R extends ResponseMessage<?>> extends Message {

    Language getLanguage();

    void setLanguage(Language language);

    Long getAccountId();

    void setAccountId(Long accountId);

    AccountRole getAccountRole();

    void setAccountRole(final AccountRole accountRole);

    R getReply();
}
