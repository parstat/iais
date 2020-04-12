package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessProcess;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgramCycle;
import com.nbs.iais.ms.common.enums.Frequency;

import java.util.List;


public interface BusinessProcessDocumentation extends DomainObject {

    MultilingualText getName();

    void setName(MultilingualText name);

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

    MultilingualText getNotes();

    void setNotes(MultilingualText notes);

    StatisticalProgramCycle getStatisticalProgramCycle();

    void setStatisticalProgramCycle(StatisticalProgramCycle statisticalPrograms);

    BusinessProcess getBusinessProcess();

    void setBusinessProcesses(BusinessProcess businessProcess);

    AgentInRole getOwnerDivision();

    void setOwnerDivision(AgentInRole division);

    Frequency getFrequency();

    void setFrequency(Frequency frequency);

    BusinessProcess getNextProcess();

    void setNextProcess(BusinessProcess businessProcess);

    List<StatisticalStandardReference> getStandards();

    void setStandards(List<StatisticalStandardReference> statisticalStandardReferences);

    List<Software> getSoftwareUsed();

    void setSoftwareUsed(List<Software> softwareUsed);

    List<StatisticalMethod> getMethodsUsed();

    void setMethodsUsed(List<StatisticalMethod> methodsUsed);

    List<ProcessQualityIndicator> getQualityIndicatorsUsed();

    void setQualityIndicatorsUsed(List<ProcessQualityIndicator> processQualityIndicatorsUsed);

    List<ProcessDocument> getAdditionalDocuments();

    void setAdditionalDocuments(List<ProcessDocument> additionalProcessDocuments);

    List<InputDocumentation> getInputs();

    void setInputs(List<InputDocumentation> inputs);

    List<OutputDocumentation> getOutputs();

    void setOutputs(List<OutputDocumentation> outputs);
}
