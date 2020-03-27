package com.nbs.iais.ms.meta.referential.db.domains;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgramCycle;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "StatisticalProgram")
@Table(name = "statistical_program")
public class StatisticalProgramEntity extends AbstractIdentifiableArtefact implements StatisticalProgram  {

    @Column(name = "date_initiated")
    private LocalDateTime dateInitiated;

    @Column(name = "date_ended")
    private LocalDateTime dateEnded;

    @Column(name = "program_status")
    private ProgramStatus programStatus;

    @Column(name = "budged")
    private double budget;

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
        return null;
    }

    @Override
    public void setLegalFrameworks() {

    }

    @Override
    public List<String> getLegislativeReference() {
        return null;
    }

    @Override
    public void setLegislativeReference(List<String> legislativeReference) {

    }

    @Override
    public String getSourceOfFounding() {
        return null;
    }

    @Override
    public void setSourceOfFounding(String sourceOfFounding) {

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
        return null;
    }

    @Override
    public void setStatisticalProgramCycles(List<StatisticalProgramCycle> statisticalProgramCycles) {

    }

    @Override
    public List<StatisticalProgram> getRelates() {
        return null;
    }

    @Override
    public void setRelates(StatisticalProgram statisticalProgram) {

    }

    @Override
    public ChangeEvent getChangeEvent() {
        return null;
    }

    @Override
    public void setChangeEvent(ChangeEvent changeEvent) {

    }

    @Override
    public List<AgentInRole> getAdministrators() {
        return null;
    }

    @Override
    public void setAdministrators(List<AgentInRole> administrators) {

    }
}
