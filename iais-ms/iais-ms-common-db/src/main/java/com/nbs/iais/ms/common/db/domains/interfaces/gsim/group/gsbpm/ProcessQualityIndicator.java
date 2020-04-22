package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

public interface ProcessQualityIndicator extends IdentifiableArtefact {

    ProcessDocumentation getProcessDocumentation();

    void setProcessDocumentation(ProcessDocumentation processDocumentation);
}
