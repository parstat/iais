package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.enums.EntityType;
import com.nbs.iais.ms.common.enums.UserRole;

public interface Right extends DomainObject {

    Long getAccount();

    void setAccount(Long account);

    UserRole getRole();

    void setRole(UserRole role);

    Long getEntityId();

    void setEntityId(Long id);

    EntityType getEntityType();

    void setEntityType(EntityType entityType);

}
