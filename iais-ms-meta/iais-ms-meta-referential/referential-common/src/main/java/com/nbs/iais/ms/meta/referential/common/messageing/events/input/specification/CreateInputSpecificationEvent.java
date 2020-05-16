package com.nbs.iais.ms.meta.referential.common.messageing.events.input.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessInputSpecificationDTO;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class CreateInputSpecificationEvent extends AbstractEvent<ProcessInputSpecificationDTO> {

	private static final long serialVersionUID = 272200L;

	public CreateInputSpecificationEvent() {
		super();
	}

}
