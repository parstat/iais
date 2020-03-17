package com.nbs.iais.ms.common.db.domains.interfaces;

import com.nbs.iais.ms.common.enums.Language;

import java.util.UUID;

public interface MultilingualText extends DomainObject {

    String getWord();

    void setWord(String word);

    Language getLanguage();

    void setLanguage(Language language);

    UUID getEntityId();

    void setEntityId(UUID entityId);

}
