package com.nbs.iais.ms.common.messaging.commands.abstracts;

import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.enums.AccountRole;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.Command;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

import java.util.UUID;

public abstract class AbstractCommand<E extends AbstractEvent<? extends DTO>> implements Command<E> {

    private E event;
    private Language language;
    private UUID accountId;
    private AccountRole accountRole;

    protected AbstractCommand() {
    }

    protected AbstractCommand(final E event) {
        this.event = event;
    }

    @Override
    public E getEvent() {
        return event;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(final Language language) {
        this.language = language;
    }

    @Override
    public UUID getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    @Override
    public E getReply() {
        return getEvent();
    }

    @Override
    public AccountRole getAccountRole() {
        return accountRole;
    }

    @Override
    public void setAccountRole(final AccountRole accountRole) {
        this.accountRole = accountRole;
    }
}
