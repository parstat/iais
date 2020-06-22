package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessQuality;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.QualityType;

import javax.persistence.*;

@Entity(name = "ProcessQuality")
@Table(name = "process_quality")
public class ProcessQualityEntity extends AbstractIdentifiableArtefact implements ProcessQuality {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @OneToOne(targetEntity = AdministrativeDetailsEntity.class, orphanRemoval = true)
    @JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
    private AdministrativeDetails administrativeDetails;

    @ManyToOne(targetEntity = ProcessDocumentationEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "process_documentation_id", referencedColumnName = "id")
    private ProcessDocumentation processDocumentation;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private QualityType type;

    public ProcessQualityEntity() {
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

    private MultilingualText description() {
        if(getDescription() == null) {
            setDescription(new MultiLanguageTextEntity());
        }
        return getDescription();
    }

    @Override
    public String getDescription(final Language language) {
        return description().getText(language.getShortName());
    }

    @Override
    public void setDescription(final String description, final Language language) {
        description().addText(language.getShortName(), description);
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
        return administrativeDetails;
    }

    @Override
    public void setAdministrativeDetails(final AdministrativeDetails administrativeDetails) {
        this.administrativeDetails = administrativeDetails;
    }

    @Override
    public ProcessDocumentation getProcessDocumentation() {
        return processDocumentation;
    }

    @Override
    public void setProcessDocumentation(final ProcessDocumentation processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    @Override
    public QualityType getQualityType() {
        return type;
    }

    @Override
    public void setQualityType(final QualityType type) {
        this.type = type;
    }
}
