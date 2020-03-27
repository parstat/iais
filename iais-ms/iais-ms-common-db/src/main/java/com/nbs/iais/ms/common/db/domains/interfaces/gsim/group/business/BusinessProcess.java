package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.time.LocalDateTime;
import java.util.List;

public interface BusinessProcess extends IdentifiableArtefact {

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateInitiated);

    LocalDateTime getDateEnded();

    void setDateEnded(LocalDateTime dateEnded);

    List<ProcessStep> getProcessSteps();

    void setProcessSteps(List<ProcessStep> processSteps);

    List<StatisticalProgramCycle> getStatisticalProgramCycles();

    void setStatisticalProgramCycles(List<StatisticalProgramCycle> statisticalProgramCycles);

    List<BusinessFunction> getPerforms();

    void setPerforms(List<BusinessFunction> performs);

    List<BusinessService> getBusinessServices();

    void setBusinessServices(List<BusinessService> businessServices);


}
