package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessInputSpecifications;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessInputType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ProcessInputSpecifications")
@Table(name = "process_input_specifications")
public class ProcessInputSpecificationsEntity extends AbstractIdentifiableArtefact implements ProcessInputSpecifications {

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

    @ElementCollection(targetClass=ProcessInputType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="input_types")
    @Column(name="type")
    private List<ProcessInputType> processInputTypes;

    @ManyToOne(targetEntity = ProcessDesignEntity.class)
    @JoinColumn(name = "process_design_id", referencedColumnName = "id")
    private ProcessDesign processDesign;

    public ProcessInputSpecificationsEntity() {
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
    public List<ProcessInputType> getProcessInputTypes() {
        return processInputTypes;
    }

    @Override
    public void setProcessInputTypes(final List<ProcessInputType> processInputTypes) {
        this.processInputTypes = processInputTypes;
    }

    @Override
    public ProcessDesign getProcessDesign() {
        return processDesign;
    }

    @Override
    public void setProcessDesign(final ProcessDesign processDesigns) {
        this.processDesign = processDesigns;
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
}
