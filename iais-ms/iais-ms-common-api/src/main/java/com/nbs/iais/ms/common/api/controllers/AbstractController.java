package com.nbs.iais.ms.common.api.controllers;

import com.nbs.iais.ms.common.RequestMessage;
import com.nbs.iais.ms.common.ResponseMessage;
import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController implements IAISController {

    @Autowired
    private IAISGateway iaisGateway;

    @Override
    public <RES extends ResponseMessage<D>, REQ extends RequestMessage<RES>, D extends DTO> REQ send(REQ request, String source) {
        return iaisGateway.send(request, source);
    }
}
