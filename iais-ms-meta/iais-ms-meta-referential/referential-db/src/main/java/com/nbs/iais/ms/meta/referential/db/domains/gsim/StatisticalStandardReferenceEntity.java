package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;

import javax.persistence.*;

@Entity(name = "StatisticalStandardReference")
@Table(name = "statistical_standard_reference")
public class StatisticalStandardReferenceEntity extends AbstractIdentifiableArtefact implements StatisticalStandardReference {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_link")
    private MultilingualText link;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private StatisticalStandardType type;

    public StatisticalStandardReferenceEntity() {
        super();
    }

    @Override
    public void setName(final String name, final Language language) {
        name().addText(language.getShortName(), name);
    }

    @Override
    public String getName(final Language language) {
        return name().getText(language.getShortName());
    }

    private MultilingualText name() {
        if(getName() == null) {
            setName(new MultiLanguageTextEntity());
        }
        return getName();
    }

    @Override
    public void setDescription(final String description, final Language language) {
        description().addText(language.getShortName(), description);
    }

    @Override
    public String getDescription(final Language language) {
        return description().getText(language.getShortName());
    }

    private MultilingualText description() {
        if(getDescription() == null) {
            setDescription(new MultiLanguageTextEntity());
        }
        return getDescription();
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
    public MultilingualText getLink() {
        return link;
    }

    @Override
    public void setLink(final MultilingualText link) {
        this.link = link;
    }

    @Override
    public MultilingualText getName() {
        return name;
    }

    @Override
    public void setName(final MultilingualText name) {
        this.name = name;
    }

    @Override
    public MultilingualText getDescription() {
        return description;
    }

    @Override
    public void setDescription(final MultilingualText description) {
        this.description = description;
    }

    @Override
    public AdministrativeDetails getAdministrativeDetails() {
        return null;
    }

    @Override
    public void setAdministrativeDetails(AdministrativeDetails administrativeDetails) {

    }
}
