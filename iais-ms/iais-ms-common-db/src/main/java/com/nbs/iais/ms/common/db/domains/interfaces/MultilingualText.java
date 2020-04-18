package com.nbs.iais.ms.common.db.domains.interfaces;

public interface MultilingualText extends DomainObject {

    void addText(String lang, String text);

    String getText(String lang);

}
