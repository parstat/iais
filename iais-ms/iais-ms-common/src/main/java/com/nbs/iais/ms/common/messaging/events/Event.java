package com.nbs.iais.ms.common.messaging.events;

import com.nbs.iais.ms.common.ResponseMessage;
import com.nbs.iais.ms.common.dto.DTO;

public interface Event<D extends DTO> extends ResponseMessage<D> {

}
