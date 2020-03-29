package com.nbs.iais.ms.common.api.messaging.gateway;

import com.nbs.iais.ms.common.RequestMessage;
import com.nbs.iais.ms.common.ResponseMessage;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.messaging.channels.Channels;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway()
public interface IAISGateway {
    /**
     * A gateway to send the message in the service using serviceInput channel
     * This method waits for a response from the service in an anonymous channel
     * @param requestMessage the message to be send to the service
     * @param source the source of the message (can be later used to gateway)
     * @return request message (query or command) that's includes the response (read or event)
     */
    @Gateway(requestChannel = Channels.SERVICE_INPUT)
    <D extends DTO, RES extends ResponseMessage<D>, REQ extends RequestMessage<RES>> REQ send(@Payload REQ requestMessage,
                                                                                              @Header(value = "source") String source);

    /**
     * A gateway to send the message in the service using serviceInput channel
     * This method does not wait for a reply from the service
     * @param request the requested command or query
     * @param source this puts a header into the message with the name source (can be used later for gateway)
     */
    @Gateway(requestChannel = Channels.SERVICE_INPUT)
    <D extends DTO, RES extends ResponseMessage<D>, REQ extends RequestMessage<RES>> void asyncSend(@Payload REQ request,
                                                                                                    @Header(value = "source") String source);

}
