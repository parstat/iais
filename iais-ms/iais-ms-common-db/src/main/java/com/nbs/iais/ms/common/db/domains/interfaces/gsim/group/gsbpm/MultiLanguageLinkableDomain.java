package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

public interface MultiLanguageLinkableDomain extends IdentifiableArtefact {


    MultilingualText getLink();

    void setLink(MultilingualText link);

}
