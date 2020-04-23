package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocument;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessQuality;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.enums.Frequency;

import java.util.List;

public interface ProcessDesign extends IdentifiableArtefact {

    List<BusinessService> getBusinessServices();

    void setBusinessServices(List<BusinessService> businessServices);

    List<ProcessInputSpecification> getProcessInputs();

    void setProcessInputs(List<ProcessInputSpecification> processInputs);

    List<ProcessOutputSpecification> getProcessOutputs();

    void setProcessOutputs(List<ProcessOutputSpecification> processOutputs);

    List<ProcessMethod> getProcessMethods();

    void setProcessMethods(List<ProcessMethod> processMethods);

    List<ProcessDocument> getProcessDocuments();

    void setProcessDocuments(List<ProcessDocument> processDocuments);

    List<ProcessQuality> getProcessQualityIndicators();

    void setProcessQualityIndicators(List<ProcessQuality> processQualityIndicators);

    BusinessFunction getBusinessFunction();

    void setBusinessFunction(BusinessFunction businessFunction);

    //added
    BusinessFunction getNextBusinessFunction();

    //added
    void setNextBusinessFunction(BusinessFunction businessFunction);

    StatisticalProgramDesign getStatisticalProgramDesign();

    void setStatisticalProgramDesign(StatisticalProgramDesign statisticalProgramDesign);

    //added
    Frequency getFrequency();

    //added
    void setFrequency(Frequency frequency);

    List<StatisticalStandardReference> getStandardsUsed();

    void setStandardsUsed(List<StatisticalStandardReference> standardsUsed);
}
