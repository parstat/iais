package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.enums.LegislativeType;

import java.time.LocalDateTime;

public interface LegislativeReference extends MultiLanguageLinkableDomain{

    LegislativeType getLegislativeType();
    
    void setLegislativeType(LegislativeType type);
    
    Integer geNumber();
    
    void setNumber(Integer number);

    LocalDateTime getApprovalDate();
    
    void setApprovalDate(LocalDateTime approvalDate);
}
