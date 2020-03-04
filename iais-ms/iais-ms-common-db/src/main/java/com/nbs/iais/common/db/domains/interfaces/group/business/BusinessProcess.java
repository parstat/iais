package com.nbs.iais.common.db.domains.interfaces.group.business;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.time.LocalDateTime;

public interface BusinessProcess extends IdentifiableArtefact {

    LocalDateTime getDateInitiated();

    void setDateInitiated(LocalDateTime dateInitiated);

    LocalDateTime getDateEnded();

    void setDateEnded(LocalDateTime dateEnded);

}
