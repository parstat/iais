package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.enums.LegislativeType;

import java.time.LocalDateTime;

public interface LegislativeReference extends MultiLanguageLinkableDomain{

    LegislativeType getLegislativeType();
    
    void setLegislativeType(LegislativeType type);

    LocalDateTime getApprovalDate();
    
    void setApprovalDate(LocalDateTime approvalDate);
}
