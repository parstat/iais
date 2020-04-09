package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;

public interface OutputDocumentation extends DomainObject {

    MultilingualText getName();

    void setName(MultilingualText name);

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

}
