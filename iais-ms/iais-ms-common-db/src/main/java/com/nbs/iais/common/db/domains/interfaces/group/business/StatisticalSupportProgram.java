package com.nbs.iais.common.db.domains.interfaces.group.business;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticalSupportProgram extends IdentifiableArtefact {

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateInitiated);

    LocalDateTime getDateEnded();

    void setDateEnded(LocalDateTime dateEnded);

    List<String> getSignificantEvents();

    void setSignificantEvents(List<String> significantEvents);

    ProgramStatus getStatus();

    void setStatus(ProgramStatus status);

    List<BusinessProcess> getBusinessProcesses();

    void setBusinessProcesses(List<BusinessProcess> businessProcesses);

    List<ChangeDefinition> getChangeDefinitions();

    void setChangeDefinitions(List<ChangeDefinition> changeDefinitions);

    List<StatisticalNeed> getStatisticalNeeds();

    void setStatisticalNeeds(List<StatisticalNeed> statisticalNeeds);

    List<BusinessCase> getBusinessCases();

    void setBusinessCases(List<BusinessCase> businessCases);

    List<StatisticalProgramDesign> getStatisticalProgramDesigns();

    void setStatisticalProgramDesigns(List<StatisticalProgramDesign> statisticalProgramDesigns);


}
