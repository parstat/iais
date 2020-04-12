package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessOutputSpecification;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProcessOutputType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ProcessOutputSpecification")
@Table(name = "process_output_specification")
public class ProcessOutputSpecificationEntity extends AbstractIdentifiableArtefact implements ProcessOutputSpecification {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
    private AdministrativeDetails administrativeDetails;

    @ElementCollection(targetClass= ProcessOutputType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="output_types")
    @Column(name="type")
    private List<ProcessOutputType> processOutputTypes;

    @ManyToOne(targetEntity = ProcessDesignEntity.class)
    @Column(name = "process_design_id")
    private ProcessDesign processDesign;

    public void setName(final String name, final Language language) {
        name().addText(language.getShortName(), name);
    }

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

    public String getDescription(final Language language) {
        return description().getText(language.getShortName());
    }

    public void setDescription(final String description, final Language language) {
        description().addText(language.getShortName(), description);
    }

    @Override
    public List<ProcessOutputType> getProcessOutputTypes() {
        return processOutputTypes;
    }

    @Override
    public void setProcessOutputTypes(List<ProcessOutputType> processOutputTypes) {
        this.processOutputTypes = processOutputTypes;
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
