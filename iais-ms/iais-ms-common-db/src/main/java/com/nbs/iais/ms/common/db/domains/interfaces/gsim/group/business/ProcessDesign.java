package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.Document;
import com.nbs.iais.ms.common.enums.Frequency;

import java.util.List;

public interface ProcessDesign extends IdentifiableArtefact {

    List<BusinessService> getBusinessServices();

    void setBusinessServices(List<BusinessService> businessServices);

    List<ProcessInputSpecifications> getProcessInputs();

    void setProcessInputs(List<ProcessInputSpecifications> processInputs);

    List<ProcessOutputSpecification> getProcessOutputs();

    void setProcessOutputs(List<ProcessOutputSpecification> processOutputs);

    List<ProcessMethod> getProcessMethods();

    void setProcessMethods(List<ProcessMethod> processMethods);

    BusinessFunction getBusinessFunction();

    void setBusinessFunction(BusinessFunction businessFunction);

    BusinessFunction getNextBusinessFunction();

    void setNextBusinessFunction(BusinessFunction businessFunction);

    StatisticalProgramDesign getStatisticalProgramDesign();

    void setStatisticalProgramDesign(StatisticalProgramDesign statisticalProgramDesign);

    //addes
    Frequency getFrequency();

    void setFrequency(Frequency frequency);
}
