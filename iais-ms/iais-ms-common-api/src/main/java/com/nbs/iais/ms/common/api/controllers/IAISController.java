package com.nbs.iais.ms.common.api.controllers;

import com.nbs.iais.ms.common.RequestMessage;
import com.nbs.iais.ms.common.ResponseMessage;
import com.nbs.iais.ms.common.dto.DTO;

public interface IAISController {

    <RES extends ResponseMessage<D>, REQ extends RequestMessage<RES>, D extends DTO> REQ send(REQ request, String source);
}
