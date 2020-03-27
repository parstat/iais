package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticalProgramCycle extends IdentifiableArtefact {

    LocalDateTime getReferencePeriodStart();

    void setReferencePeriodStart(LocalDateTime referencePeriodStart);

    LocalDateTime getReferencePeriodEnd();

    void setReferencePeriodEnd(LocalDateTime referencePeriodEnd);

    List<BusinessProcess> getBusinessProcesses();

    void setBusinessProcesses(List<BusinessProcess> businessProcesses);

    StatisticalProgram getStatisticalProgram();

    void setStatisticalProgram(StatisticalProgram statisticalProgram);

}
