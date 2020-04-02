package com.nbs.iais.ms.common.api.controllers;

import com.nbs.iais.ms.common.RequestMessage;
import com.nbs.iais.ms.common.ResponseMessage;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.messaging.commands.Command;
import com.nbs.iais.ms.common.messaging.events.Event;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.Read;

public interface IAISController {

    <REQ extends RequestMessage<? extends ResponseMessage<? extends DTO>>> REQ send(REQ request, String source);

    <CMD extends Command<? extends Event<? extends DTO>>> CMD sendCommand(CMD command, String source);

    <QRY extends Query<? extends Read<? extends DTO>>> QRY sendQuery(QRY query, String source);
}
