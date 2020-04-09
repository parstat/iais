package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "LegislativeReference")
@Table(name = "legislative_reference")
public class LegislativeReferenceEntity extends AbstractDomainObject implements LegislativeReference {

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
    private LegislativeType type;

    @Column(name = "law_no")
    private int number;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

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
    public LegislativeType getLegislativeType() {
        return type;
    }

    @Override
    public void setLegislativeType(final LegislativeType type) {
        this.type = type;
    }

    @Override
    public int geNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    @Override
    public void setApprovalDate(final LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
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
