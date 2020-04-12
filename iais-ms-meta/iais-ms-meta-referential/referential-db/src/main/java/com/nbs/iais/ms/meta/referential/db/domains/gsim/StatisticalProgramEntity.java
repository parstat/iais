package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgramCycle;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgramDesign;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "StatisticalProgram")
@Table(name = "statistical_program")
public class StatisticalProgramEntity extends AbstractIdentifiableArtefact implements StatisticalProgram  {

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_name")
    private MultilingualText name;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL,
            targetEntity = MultiLanguageTextEntity.class)
    @JoinColumn(name = "key_description")
    private MultilingualText description;

    @Column(name = "date_initiated")
    private LocalDateTime dateInitiated;

    @Column(name = "date_ended")
    private LocalDateTime dateEnded;

    @Enumerated(EnumType.STRING)
    @Column(name = "program_status")
    private ProgramStatus programStatus;

    @Column(name = "budged")
    private double budget;

    @Column(name = "source_of_funding")
    private String sourceOfFunding;

    @ManyToMany(targetEntity = AgentInRoleEntity.class)
    @JoinTable(name = "sp_agent_in_role",
                joinColumns = @JoinColumn(name = "sp_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
    private List<AgentInRole> administrators;

    @ManyToMany(targetEntity = StatisticalProgramEntity.class)
    @JoinTable(name = "related_statistical_programs",
            joinColumns = @JoinColumn(name = "statistical_program_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "relates_with", referencedColumnName = "id"))
    private List<StatisticalProgram> relates = new ArrayList<>();

    @OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
    private AdministrativeDetails administrativeDetails;

    @ElementCollection(targetClass = String.class)
    private List<String> legalFrameworks;

    @ManyToMany(targetEntity = LegislativeReferenceEntity.class)
    @JoinTable(name = "legislative_reference_used",
            joinColumns = @JoinColumn(name = "statistical_program_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "legislative_reference_id", referencedColumnName = "id"))
    private List<LegislativeReference> legislativeReferences;

    @OneToMany(targetEntity = StatisticalProgramCycleEntity.class, mappedBy = "statisticalProgram")
    private List<StatisticalProgramCycle> statisticalProgramCycles;

    @ManyToOne(targetEntity = StatisticalProgramDesignEntity.class)
    @JoinColumn(name = "statistical_program_design_id", referencedColumnName = "id")
    private StatisticalProgramDesign statisticalProgramDesign;

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
    public double getBudget() {
        return budget;
    }

    @Override
    public void setBudget(final double budget) {
        this.budget = budget;
    }

    @Override
    public LocalDateTime getDateInitiated() {
        return dateInitiated;
    }

    @Override
    public void setDateInitiated(final LocalDateTime dateInitiated) {
        this.dateInitiated = dateInitiated;
    }

    @Override
    public LocalDateTime getDateEnded() {
        return dateEnded;
    }

    @Override
    public void setDateEnded(final LocalDateTime dateEnded) {
        this.dateEnded = dateEnded;
    }

    @Override
    public List<String> getLegalFrameworks() {
        return legalFrameworks;
    }

    @Override
    public void setLegalFrameworks(final List<String> legalFrameworks) {
        this.legalFrameworks = legalFrameworks;
    }

    @Override
    public List<LegislativeReference> getLegislativeReference() {
        return legislativeReferences;
    }

    @Override
    public void setLegislativeReference(final List<LegislativeReference> legislativeReferences) {
        this.legislativeReferences = legislativeReferences;
    }

    @Override
    public String getSourceOfFounding() {
        return sourceOfFunding;
    }

    @Override
    public void setSourceOfFounding(final String sourceOfFounding) {
        this.sourceOfFunding = sourceOfFounding;
    }

    @Override
    public ProgramStatus getProgramStatus() {
        return programStatus;
    }

    @Override
    public void setProgramStatus(final ProgramStatus programStatus) {
        this.programStatus = programStatus;
    }

    @Override
    public List<StatisticalProgramCycle> getStatisticalProgramCycles() {
        return statisticalProgramCycles;
    }

    @Override
    public void setStatisticalProgramCycles(final List<StatisticalProgramCycle> statisticalProgramCycles) {
        this.statisticalProgramCycles = statisticalProgramCycles;
    }

    @Override
    public List<StatisticalProgram> getRelates() {
        return relates;
    }

    @Override
    public void setRelates(List<StatisticalProgram> relates) {
        this.relates = relates;
    }

    @Override
    public StatisticalProgramDesign getStatisticalProgramDesign() {
        return statisticalProgramDesign;
    }

    @Override
    public void setStatisticalProgramDesign(final StatisticalProgramDesign statisticalProgramDesign) {
        this.statisticalProgramDesign = statisticalProgramDesign;
    }

    public List<AgentInRole> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(final List<AgentInRole> administrators) {
        this.administrators = administrators;
    }

    @Override
    public AdministrativeDetails getAdministrativeDetails() {
        return administrativeDetails    ;
    }

    @Override
    public void setAdministrativeDetails(final AdministrativeDetails administrativeDetails) {
        this.administrativeDetails = administrativeDetails;
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
    public MultilingualText getName() {
        return name;
    }

    @Override
    public void setName(final MultilingualText name) {
        this.name = name;
    }
}
