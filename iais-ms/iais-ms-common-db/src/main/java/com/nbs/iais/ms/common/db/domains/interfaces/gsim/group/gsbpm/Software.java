package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;

public interface Software extends DomainObject {

    String getName();

    void setName(String name);

    String getDeveloper();

    void setDeveloper(String developer);

    String getWebsite();

    void setWebsite(String website);
}
