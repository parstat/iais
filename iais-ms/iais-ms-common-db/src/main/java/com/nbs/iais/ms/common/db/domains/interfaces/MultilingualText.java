package com.nbs.iais.ms.common.db.domains.interfaces;

import java.util.Map;

public interface MultilingualText extends DomainObject {

    void addText(String lang, String text);

    String getText(String lang);

    Map<String, String> getMap();

}
