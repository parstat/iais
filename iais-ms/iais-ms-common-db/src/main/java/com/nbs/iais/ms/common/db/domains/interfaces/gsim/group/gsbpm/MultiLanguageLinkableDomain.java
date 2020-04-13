package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.Language;

public interface MultiLanguageLinkableDomain extends IdentifiableArtefact {


    MultilingualText getLink();

    void setLink(MultilingualText link);

    String getLink(Language language);

    void setLink(String link, Language language);

}
