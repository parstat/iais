package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.util.List;

public interface ProcessStep extends IdentifiableArtefact {

    boolean isComprehensive();

    void setComprehensive(boolean comprehensive);

    List<ProcessStep> getSubProcessSteps();

    void setSubProcessSteps(List<ProcessStep> processSteps);

    List<BusinessService> getUses();

    void setUses(List<BusinessService> uses);

    List<BusinessService> getPerforms();

    void setPerforms(List<BusinessService> performs);

    BusinessProcess getBusinessProcess();

    void setBusinessProcess(BusinessProcess businessProcess);

    ProcessControl getExecute();

    void setExecute(ProcessControl execute);

    List<ProcessControl> getInvokes();

    void setInvokes(List<ProcessControl> invokes);

    List<ProcessStepInstance> getProcessStepInstances();

    void setProcessStepInstances(List<ProcessStepInstance> processStepInstances);

    ProcessDesign getImplementedBy();

    void setImplementedBy(ProcessDesign processDesign);

    
}
