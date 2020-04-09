package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticalProgram extends IdentifiableArtefact {

    double getBudget();

    void setBudget(double budget);

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateInitiated);

    LocalDateTime getDateEnded();

    void setDateEnded(LocalDateTime dateEnded);

    List<String> getLegalFrameworks();

    void setLegalFrameworks(List<String> legalFrameworks);

    List<LegislativeReference> getLegislativeReference();

    void setLegislativeReference(List<LegislativeReference> legislativeReference);

    String getSourceOfFounding();

    void setSourceOfFounding(String sourceOfFounding);

    ProgramStatus getProgramStatus();

    void setProgramStatus(ProgramStatus programStatus);

    List<StatisticalProgramCycle> getStatisticalProgramCycles();

    void setStatisticalProgramCycles(List<StatisticalProgramCycle> statisticalProgramCycles);

    List<StatisticalProgram> getRelates();

    void setRelates(List<StatisticalProgram> statisticalProgram);

}
