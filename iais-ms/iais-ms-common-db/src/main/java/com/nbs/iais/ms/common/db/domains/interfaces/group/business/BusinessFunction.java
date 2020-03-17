package com.nbs.iais.ms.common.db.domains.interfaces.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface BusinessFunction extends IdentifiableArtefact {

    List<BusinessProcess> getDelivers();

    void setDelivers(List<BusinessProcess> delivers);

    List<BusinessProcess> getPerforms();

    void setPerforms(List<BusinessProcess> performs);

    List<StatisticalProgramDesign> getUses();

    void setUses(List<StatisticalProgramDesign> uses);

    List<ProcessDesign> getProcessDesigns();

    void setProcessDesigns(List<ProcessDesign> processDesigns);


}
