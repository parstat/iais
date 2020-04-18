package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractMultiLanguageText;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "MultiLanguageText")
@Table(name = "multilanguage_text")
public class MultiLanguageTextEntity extends AbstractMultiLanguageText {

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "language", insertable = false, updatable = false, length = 2)
    @CollectionTable(name = "multilanguage_texts", joinColumns = @JoinColumn(name = "text_id"))
    @Column(name = "text", columnDefinition = "text")
    private Map<String, String> map = new HashMap<>();

    public MultiLanguageTextEntity() {
        super();
    }

    public MultiLanguageTextEntity(final String language, final String text) {
        addText(language, text);
    }

    public Map<String, String> getMap() {
        return map;
    }
}
