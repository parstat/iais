package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.QualityType;

public interface ProcessQuality extends IdentifiableArtefact {

    ProcessDocumentation getProcessDocumentation();

    void setProcessDocumentation(ProcessDocumentation processDocumentation);

    QualityType getQualityType();

    void setQualityType(QualityType type);

}
