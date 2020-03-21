package com.nbs.iais.ms.meta.referential.db.domains;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.group.business.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "BusinessProcess")
@Table(name = "business_process")
public class BusinessProcessEntity extends AbstractIdentifiableArtefact implements BusinessProcess {

    @Column(name = "date_initiated")
    private LocalDateTime dateInitiated;

    @Column(name = "date_ended")
    private LocalDateTime dateEnded;

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
    public List<ProcessStep> getProcessSteps() {
        return null;
    }

    @Override
    public void setProcessSteps(List<ProcessStep> processSteps) {

    }

    @Override
    public List<StatisticalProgramCycle> getStatisticalProgramCycles() {
        return null;
    }

    @Override
    public void setStatisticalProgramCycles(List<StatisticalProgramCycle> statisticalProgramCycles) {

    }

    @Override
    public List<BusinessFunction> getPerforms() {
        return null;
    }

    @Override
    public void setPerforms(List<BusinessFunction> performs) {

    }

    @Override
    public List<BusinessService> getBusinessServices() {
        return null;
    }

    @Override
    public void setBusinessServices(List<BusinessService> businessServices) {

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
