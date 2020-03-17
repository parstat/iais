package com.nbs.iais.ms.meta.referential.db.domains;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.group.business.StatisticalProgramCycle;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "StatisticalProgram")
@Table(name = "statistical_program")
public class StatisticalProgramEntity extends AbstractIdentifiableArtefact implements StatisticalProgram  {

    @Override
    public double getBudget() {
        return 0;
    }

    @Override
    public void setBudget(double budget) {

    }

    @Override
    public LocalDateTime getDateInitiated() {
        return null;
    }

    @Override
    public void setDateInitiated(LocalDateTime dateInitiated) {

    }

    @Override
    public LocalDateTime getDateEnded() {
        return null;
    }

    @Override
    public void setDateEnded(LocalDateTime dateEnded) {

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
        return null;
    }

    @Override
    public void setProgramStatus(ProgramStatus programStatus) {

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
