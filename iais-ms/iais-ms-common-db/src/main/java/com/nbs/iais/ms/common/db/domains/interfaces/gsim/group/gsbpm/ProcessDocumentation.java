package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.enums.Frequency;

import java.time.LocalDateTime;
import java.util.List;


public interface ProcessDocumentation extends IdentifiableArtefact {

    LocalDateTime getValidTo();

    void setValidTo(LocalDateTime validTo);

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

    BusinessFunction getNextBusinessFunction();

    void setNextBusinessFunction(BusinessFunction businessFunction);

    StatisticalProgram getStatisticalProgram();

    void setStatisticalProgram(StatisticalProgram statisticalProgram);

    Frequency getFrequency();

    void setFrequency(Frequency frequency);

    List<StatisticalStandardReference> getStandardsUsed();

    void setStandardsUsed(List<StatisticalStandardReference> standardsUsed);

    List<AgentInRole> getAdministrators();

    void setAdministrators(List<AgentInRole> administrators);
}
