package com.nbs.iais.ms.referential.common.messageing.commands;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.security.referential.messageing.events.StatisticalProcessQueryEvent;

public class StatisticalProcessQueryCommand extends AbstractCommand<StatisticalProcessQueryEvent> {

    private Long id;

    public StatisticalProcessQueryCommand() {
        super(new StatisticalProcessQueryEvent());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    
}
