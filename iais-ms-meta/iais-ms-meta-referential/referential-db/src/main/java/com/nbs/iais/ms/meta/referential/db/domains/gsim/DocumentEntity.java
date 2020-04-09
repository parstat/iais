package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.Document;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;

import javax.persistence.*;

@Entity(name = "Document")
@Table(name = "document")
public class DocumentEntity extends AbstractDomainObject implements Document {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_title")
    private MultilingualText title;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_link")
    private MultilingualText link;


    @Column(name = "media_type")
    private MediaType mediaType;

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
    public MediaType getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(final MediaType mediaType) {
        this.mediaType = mediaType;
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
