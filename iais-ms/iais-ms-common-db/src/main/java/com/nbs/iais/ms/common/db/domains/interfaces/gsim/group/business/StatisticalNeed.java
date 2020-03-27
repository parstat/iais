package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.StatisticalNeedType;


public interface StatisticalNeed extends IdentifiableArtefact {

    Boolean isMet();

    void setMet(Boolean isMet);

    StatisticalNeedType getType();

    void setType(StatisticalNeedType type);
}
