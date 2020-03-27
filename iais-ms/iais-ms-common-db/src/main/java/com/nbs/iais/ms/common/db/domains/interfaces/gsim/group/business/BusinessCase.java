package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.BusinessCaseType;

import java.time.LocalDateTime;
import java.util.List;

public interface BusinessCase extends IdentifiableArtefact {

    LocalDateTime getDateApproved();

    void setDateApproved(LocalDateTime dateApproved);

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateInitiated);

    List<String> getObjectives();

    void setObjectives(List<String> objectives);

    List<String> getDeliveries();
    
    void setDeliveries(List<String> deliveries);

    List<BusinessCaseType> getBusinessCaseTypes();

    void setBusinessCaseTypes(List<BusinessCaseType> businessCaseTypes);
}
