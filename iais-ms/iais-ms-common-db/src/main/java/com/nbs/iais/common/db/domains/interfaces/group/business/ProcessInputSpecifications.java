package com.nbs.iais.common.db.domains.interfaces.group.business;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.ProcessInputType;

import java.util.List;

public interface ProcessInputSpecifications extends IdentifiableArtefact {

    List<ProcessInputType> getProcessInputTypes();

    void setProcessInputTypes(List<ProcessInputType> processInputTypes);

}
