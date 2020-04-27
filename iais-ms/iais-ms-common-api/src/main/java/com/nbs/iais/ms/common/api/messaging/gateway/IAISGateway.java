package com.nbs.iais.ms.common.api.messaging.gateway;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.messaging.channels.Channels;
import com.nbs.iais.ms.common.messaging.commands.Command;
import com.nbs.iais.ms.common.messaging.events.Event;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.Read;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface IAISGateway {
 
    @Gateway(requestChannel = Channels.COMMAND_INPUT)
    <C extends Command<? extends Event<? extends DTO>>> C sendCommand(@Payload C command,
                                                                      @Header(value = "source") String source);

    @Gateway(requestChannel = Channels.QUERY_INPUT)
    <Q extends Query<? extends Read<? extends DTO>>> Q sendQuery(@Payload Q query,
                                                                 @Header(value = "source") String source);
}
