package com.nbs.iais.ms.meta.referential.common.messageing.events.process.output.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.impl.ProcessOutputSpecificationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class UpdateOutputSpecificationEvent extends AbstractEvent<ProcessDocumentationDTO> {

    private static final long serialVersionUID = 2011220L;

    public UpdateOutputSpecificationEvent() {
        super();
    }

}
