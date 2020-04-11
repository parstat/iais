package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.ProcessInputType;

import java.util.List;

public interface ProcessInputSpecifications extends IdentifiableArtefact {

    List<ProcessInputType> getProcessInputTypes();

    void setProcessInputTypes(List<ProcessInputType> processInputTypes);

    List<ProcessDesign> getProcessDesigns();

    void setProcessDesigns(List<ProcessDesign> processDesigns);
}
