package com.nbs.iais.ms.common.db.domains.abstracts;

import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;

import javax.persistence.MappedSuperclass;
import java.util.Map;

@MappedSuperclass
public abstract class AbstractMultiLanguageText extends AbstractDomainObject implements MultilingualText {

    public AbstractMultiLanguageText() {
    }

    public AbstractMultiLanguageText(String lang, String text) {
    }

    @Override
    public void addText(String lang, String text) {
        getMap().put(lang, text);
    }

    @Override
    public String getText(final String lang) {
        return getMap().get(lang);
    }
}
