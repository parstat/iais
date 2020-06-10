package com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program;

import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.messaging.events.abstracts.AbstractEvent;

public class RemoveStatisticalProgramAdministratorEvent extends AbstractEvent<StatisticalProgramDTO> {

    private static final long serialVersionUID = 200L;

    public RemoveStatisticalProgramAdministratorEvent() {
        super();
    }

}
