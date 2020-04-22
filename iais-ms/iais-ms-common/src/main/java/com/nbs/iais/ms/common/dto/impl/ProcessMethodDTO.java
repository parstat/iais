package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nbs.iais.ms.common.dto.abstracts.IdentifiableArtefactDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessMethodDTO extends IdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    public ProcessMethodDTO() {
        super();
    }

    public ProcessMethodDTO(final Long id) {
        super(id);
    }


}
