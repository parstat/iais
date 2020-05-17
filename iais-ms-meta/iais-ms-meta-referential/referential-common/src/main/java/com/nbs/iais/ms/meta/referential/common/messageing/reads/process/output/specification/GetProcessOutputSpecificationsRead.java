package com.nbs.iais.ms.meta.referential.common.messageing.reads.process.output.specification;

import com.nbs.iais.ms.common.dto.impl.ProcessOutputSpecificationDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetProcessOutputSpecificationsRead extends AbstractRead<DTOList<ProcessOutputSpecificationDTO>> {

	private static final long serialVersionUID = 21112100L;

	public GetProcessOutputSpecificationsRead() {
		super();
	}

}
