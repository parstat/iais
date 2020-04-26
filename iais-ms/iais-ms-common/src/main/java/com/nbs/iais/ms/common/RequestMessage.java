package com.nbs.iais.ms.common;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;


public interface RequestMessage<R extends ResponseMessage<? extends DTO>> extends Message {

    Language getLanguage();

    void setLanguage(Language language);

    String getJwt();

    void setJwt(String jwt);

    boolean isClosed();

    void setClosed(boolean closed);

    R getReply();
}
