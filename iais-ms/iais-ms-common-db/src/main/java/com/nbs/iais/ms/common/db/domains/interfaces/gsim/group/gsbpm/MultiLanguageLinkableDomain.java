package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;

public interface MultiLanguageLinkableDomain extends DomainObject {

    MultilingualText getTitle();

    void setTitle(MultilingualText title);

    MultilingualText getLink();

    void setLink(MultilingualText link);

}
