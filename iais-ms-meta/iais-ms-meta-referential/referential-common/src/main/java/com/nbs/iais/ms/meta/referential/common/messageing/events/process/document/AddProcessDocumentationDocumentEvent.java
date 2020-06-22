package com.nbs.iais.ms.meta.referential.common.messageing.events.process.document;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentDTO;
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class AddProcessDocumentationDocumentEvent extends AbstractEvent<ProcessDocumentationDTO> {

	private static final long serialVersionUID = 2742200L;

	public AddProcessDocumentationDocumentEvent() {
		super();
	}

}
