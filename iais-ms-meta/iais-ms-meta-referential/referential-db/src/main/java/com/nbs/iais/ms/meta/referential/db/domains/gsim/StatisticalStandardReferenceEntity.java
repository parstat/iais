package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;

import javax.persistence.*;

@Entity(name = "StatisticalStandardReference")
@Table(name = "statistical_standard_reference")
public class StatisticalStandardReferenceEntity extends AbstractDomainObject implements StatisticalStandardReference {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_title")
    private MultilingualText title;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_link")
    private MultilingualText link;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private StatisticalStandardType type;

    public void setTitle(final String title, final Language language) {
        title().addText(language.getShortName(), title);
    }

    public String getTitle(final Language language) {
        return title().getText(language.getShortName());
    }

    private MultilingualText title() {
        if(getTitle() == null) {
            setTitle(new MultiLanguageTextEntity());
        }
        return getTitle();
    }

    private MultilingualText link() {
        if(getLink() == null) {
            setLink(new MultiLanguageTextEntity());
        }
        return getLink();
    }

    public String getLink(final Language language) {
        return link().getText(language.getShortName());
    }

    public void setLink(final String link, final Language language) {
        link().addText(language.getShortName(), link);
    }


    @Override
    public StatisticalStandardType getType() {
        return type;
    }

    @Override
    public void setType(final StatisticalStandardType type) {
        this.type = type;
    }

    @Override
    public MultilingualText getTitle() {
        return title;
    }

    @Override
    public void setTitle(final MultilingualText title) {
        this.title = title;
    }

    @Override
    public MultilingualText getLink() {
        return link;
    }

    @Override
    public void setLink(final MultilingualText link) {
        this.link = link;
    }
}
