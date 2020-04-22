package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessQualityIndicator;

import java.util.List;

public interface BusinessFunction extends IdentifiableArtefact {

    //List<BusinessProcess> getDelivers();

    //void setDelivers(List<BusinessProcess> delivers);

    //List<BusinessProcess> getPerforms();

    //void setPerforms(List<BusinessProcess> performs);

    //List<StatisticalProgramDesign> getUses();

    //void setUses(List<StatisticalProgramDesign> uses);

    //List<ProcessDesign> getProcessDesigns();

    //void setProcessDesigns(List<ProcessDesign> processDesigns);

    List<ProcessDocumentation> getProcessDocumentations();

    void setProcessDocumentations(List<ProcessDocumentation> processDocumentations);

}
