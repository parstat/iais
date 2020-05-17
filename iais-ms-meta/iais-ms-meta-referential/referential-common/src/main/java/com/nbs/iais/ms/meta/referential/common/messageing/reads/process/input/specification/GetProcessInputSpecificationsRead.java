package com.nbs.iais.ms.meta.referential.common.messageing.reads.process.input.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessInputSpecificationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetProcessInputSpecificationsRead extends AbstractRead<DTOList<ProcessInputSpecificationDTO>> {

	private static final long serialVersionUID = 2112100L;

	public GetProcessInputSpecificationsRead() {
		super();
	}

}
