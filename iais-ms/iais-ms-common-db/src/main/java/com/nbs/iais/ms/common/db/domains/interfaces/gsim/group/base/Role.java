package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.enums.RoleType;

public interface Role extends IdentifiableArtefact {

    RoleType getType();

    void setType(RoleType type);

}
