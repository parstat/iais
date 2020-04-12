package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;

import java.util.List;

public interface ProcessQualityIndicator extends IdentifiableArtefact {

    List<ProcessDesign> getProcessDesigns();

    void setProcessDesigns(List<ProcessDesign> processDesigns);

}
