package com.nbs.iais.ms.common.dto.impl;

import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.ProcessDocumentationMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;

public class ProcessMethodDTO extends IdentifiableArtefactDTO {

    DTOList<ProcessDocumentationMiniDTO> processDocumentations;
}
