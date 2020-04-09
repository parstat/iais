package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.Agent;
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

    Agent getDivision();

    void setDivision(Agent division);

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

    List<QualityIndicator> getQualityIndicatorsUsed();

    void setQualityIndicatorsUsed(List<QualityIndicator> qualityIndicatorsUsed);

    List<Document> getAdditionalDocuments();

    void setAdditionalDocuments(List<Document> additionalDocuments);

    List<InputDocumentation> getInputs();

    void setInputs(List<InputDocumentation> inputs);

    List<OutputDocumentation> getOutputs();

    void setOutputs(List<OutputDocumentation> outputs);
}
