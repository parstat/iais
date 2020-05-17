package com.nbs.iais.ms.meta.referential.common.messageing.reads.process.document;

import com.nbs.iais.ms.common.dto.impl.ProcessDocumentDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.messaging.reads.abstracts.AbstractRead;

public class GetProcessDocumentsRead extends AbstractRead<DTOList<ProcessDocumentDTO>> {

	private static final long serialVersionUID = 211200L;

	public GetProcessDocumentsRead() {
		super();
	}

}
