package com.nbs.iais.common.db.domains.interfaces.group.business;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.ProcessOutputType;

import java.util.List;

public interface ProcessOutputSpecification extends IdentifiableArtefact {

    List<ProcessOutputType> getProcessOutputTypes();

    void setProcessOutputTypes(List<ProcessOutputType> processOutputTypes);

}
