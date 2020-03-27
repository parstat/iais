package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.util.List;

public interface TransformableInput<I extends IdentifiableArtefact> extends ProcessInput {

    I getRefersTo();

    void setRefersTo(I refersTo);

    List<ProcessControl> getProcessControls();

    void setProcessControls(List<ProcessControl> processControls);
    
}
