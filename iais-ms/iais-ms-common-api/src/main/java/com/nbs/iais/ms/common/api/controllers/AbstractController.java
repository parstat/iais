package com.nbs.iais.ms.common.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.ExceptionDTO;
import com.nbs.iais.ms.common.exceptions.*;
import com.nbs.iais.ms.common.messaging.commands.Command;
import com.nbs.iais.ms.common.messaging.events.Event;
import com.nbs.iais.ms.common.messaging.queries.Query;
import com.nbs.iais.ms.common.messaging.reads.Read;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController implements IAISController {

	@Autowired
	private IAISGateway iaisGateway;

	private final static Logger LOG = LoggerFactory.getLogger(AbstractController.class);

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	public ExceptionDTO handleException(final AuthorizationException ex) {
		LOG.debug("Got an AuthorizationException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(AccountNotFoundException.class)
	public ExceptionDTO handleException(final AccountNotFoundException ex) {
		LOG.debug("Got an AccountNotFoundException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(ChangePasswordException.class)
	public ExceptionDTO handleException(final ChangePasswordException ex) {
		LOG.debug("Got an ChangePasswordException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	@ExceptionHandler(ConfirmationException.class)
	public ExceptionDTO handleException(final ConfirmationException ex) {
		LOG.debug("Got an ConfirmationException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(EntityException.class)
	public ExceptionDTO handleException(final EntityException ex) {
		LOG.debug("Got an EntityException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(SigninException.class)
	public ExceptionDTO handleException(final SigninException ex) {
		LOG.debug("Got an SigninException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(SignupException.class)
	public ExceptionDTO handleException(final SignupException ex) {
		LOG.debug("Got an SignupException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}

	@JsonView(Views.Minimal.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@ExceptionHandler(SystemSetupException.class)
	public ExceptionDTO handleException(final SystemSetupException ex) {
		LOG.debug("Got an SystemSetupException ", ex);
		return ExceptionDTO.populate(ex.getType(), ex.getMessage());
	}


	@Override
	public <CMD extends Command<? extends Event<? extends DTO>>> CMD sendCommand(CMD command, String source) {
		return iaisGateway.sendCommand(command, source);
	}

	@Override
	public <QRY extends Query<? extends Read<? extends DTO>>> QRY sendQuery(QRY query, String source) {
		return iaisGateway.sendQuery(query, source);
	}
}
