package com.nbs.iais.ms.common.db.domains.interfaces.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticalProgramDesign extends IdentifiableArtefact {

    List<String> getConceptualFrameworks();

    void setConceptualFrameworks(List<String> conceptualFrameworks);

    LocalDateTime getDateEnded();

    void setDateEnded(LocalDateTime dateEnded);

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateStarted);

    ProgramStatus getProgramDesignStatus();

    void setProgramDesignStatus(ProgramStatus programDesignStatus);

    List<StatisticalProgram> getStatisticalPrograms();

    void setStatisticalPrograms(List<StatisticalProgram> statisticalPrograms);

    List<BusinessService> getBusinessServices();

    void setBusinessServices(List<BusinessService> businessServices);

    List<ProcessDesign> getProcessDesigns();

    void setProcessDesigns(List<ProcessDesign> processDesigns);

    List<BusinessCase> getBasedBusinessCases();

    void setBasedBusinessCases(List<BusinessCase> basedBusinessCases);

}
