package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.enums.MediaType;

public interface ProcessDocument extends MultiLanguageLinkableDomain  {

    MediaType getMediaType();

    void setMediaType(MediaType mediaType);

    ProcessDocumentation getProcessDocumentation();

    void setProcessDocumentation(ProcessDocumentation processDocumentation);
}
