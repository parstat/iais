package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessDesign;
import com.nbs.iais.ms.common.enums.MediaType;

public interface ProcessDocument extends MultiLanguageLinkableDomain  {

    MediaType getMediaType();

    void setMediaType(MediaType mediaType);

    ProcessDesign getProcessDesign();

    void setProcessDesign(ProcessDesign processDesign);
}
