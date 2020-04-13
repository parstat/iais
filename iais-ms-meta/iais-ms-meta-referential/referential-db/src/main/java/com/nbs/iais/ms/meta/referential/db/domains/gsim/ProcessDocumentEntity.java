package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocument;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;

import javax.persistence.*;

@Entity(name = "Document")
@Table(name = "document")
public class ProcessDocumentEntity extends AbstractIdentifiableArtefact implements ProcessDocument {

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

    @ManyToOne(targetEntity = ProcessDesignEntity.class)
    @JoinColumn(name = "process_design_id", referencedColumnName = "id")
    private ProcessDesign processDesign;

    @Column(name = "media_type")
    private MediaType mediaType;

    public ProcessDocumentEntity() {
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

    @Override
    public String getLink(final Language language) {
        return link().getText(language.getShortName());
    }

    @Override
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
    public ProcessDesign getProcessDesign() {
        return processDesign;
    }

    @Override
    public void setProcessDesign(final ProcessDesign processDesign) {
        this.processDesign = processDesign;
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
