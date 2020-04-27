package com.nbs.iais.ms.common.api.controllers;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.messaging.commands.Command;
import com.nbs.iais.ms.common.messaging.events.Event;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.Read;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController implements IAISController {

	@Autowired
	private IAISGateway iaisGateway;

	@Override
	public <CMD extends Command<? extends Event<? extends DTO>>> CMD sendCommand(CMD command, String source) {
		return iaisGateway.sendCommand(command, source);
	}

	@Override
	public <QRY extends Query<? extends Read<? extends DTO>>> QRY sendQuery(QRY query, String source) {
		return iaisGateway.sendQuery(query, source);
	}
}
