package com.nbs.iais.ms.common.db.domains.interfaces.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface BusinessService extends IdentifiableArtefact {

    List<String> getServiceInterfaces();

    void setServiceInterfaces(List<String> serviceInterfaces);

    List<BusinessFunction> getDelivers();

    void setDelivers(List<BusinessFunction> delivers);

    List<StatisticalProgramDesign> getStatisticalProgramDesigns();

    void setStatisticalProgramDesigns(List<StatisticalProgramDesign> statisticalProgramDesigns);

    List<BusinessProcess> getBusinessProcesses();

    void setBusinessProcesses(List<BusinessProcess> businessProcesses);

    List<ProcessDesign> getProcessDesignImplements();

    void setProcessDesignImplements(List<ProcessDesign> processDesignImplements);

    List<ProcessDesign> getProcessDesignUses();

    void setProcessDesignUses(List<ProcessDesign> processDesignUses);

    List<ProcessStep> getProcessStepsPerforms();

    void setProcessStepsPerforms(List<ProcessStep> processStepsPerforms);
}
