package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.enums.MediaType;

public interface Document extends MultiLanguageLinkableDomain  {

    MediaType getMediaType();

    void setMediaType(MediaType mediaType);
}
