package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.enums.StatisticalStandardType;

public interface StatisticalStandardReference extends MultiLanguageLinkableDomain {

    StatisticalStandardType getType();

    void setType(StatisticalStandardType type);


}
