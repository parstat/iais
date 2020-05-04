package com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference;

import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.standard.DeleteStatisticalStandardEvent;

public class DeleteLegislativeReferenceCommand extends AbstractCommand<DeleteStatisticalStandardEvent> {

    private static final long serialVersionUID = 200L;

    private Long id;

    private DeleteLegislativeReferenceCommand() {
        super(new DeleteStatisticalStandardEvent());
    }

    private DeleteLegislativeReferenceCommand(final String jwt, final Long id) {
        super(new DeleteStatisticalStandardEvent());
        setJwt(jwt);
        this.id = id;
    }

    public static DeleteLegislativeReferenceCommand create(final String jwt, final Long id) {
        return new DeleteLegislativeReferenceCommand(jwt, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
