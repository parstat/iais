package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.LegislativeReference;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticalProgram extends IdentifiableArtefact {

    MultilingualText getAcronym();

    void setAcronym(MultilingualText acronym);

    String getAcronym(Language language);

    void setAcronym(String acronym, Language language);

    double getBudget();

    void setBudget(double budget);

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateInitiated);

    LocalDateTime getDateEnded();

    void setDateEnded(LocalDateTime dateEnded);

    //List<String> getLegalFrameworks();

    //void setLegalFrameworks(List<String> legalFrameworks);

    List<LegislativeReference> getLegislativeReference();

    void setLegislativeReference(List<LegislativeReference> legislativeReference);

    String getSourceOfFounding();

    void setSourceOfFounding(String sourceOfFounding);

    ProgramStatus getProgramStatus();

    void setProgramStatus(ProgramStatus programStatus);

    //List<StatisticalProgramCycle> getStatisticalProgramCycles();

    //void setStatisticalProgramCycles(List<StatisticalProgramCycle> statisticalProgramCycles);

    //List<StatisticalProgram> getRelates();

    //setRelates(List<StatisticalProgram> statisticalProgram);

    //StatisticalProgramDesign getStatisticalProgramDesign();

    //void setStatisticalProgramDesign(StatisticalProgramDesign statisticalProgramDesign);

    //does not exist in GSIM, added
    List<ProcessDocumentation> getProcessDocumentations();

    void setProcessDocumentations(List<ProcessDocumentation> processDocumentations);

}
