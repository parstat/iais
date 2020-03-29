package com.nbs.iais.ms.common;

import com.nbs.iais.ms.common.enums.Language;

import java.util.UUID;

public interface RequestMessage<R extends ResponseMessage<?>> extends Message {

    Language getLanguage();

    void setLanguage(Language language);

    UUID getAccountId();

    void setAccountId(UUID accountId);

    R getReply();
}
