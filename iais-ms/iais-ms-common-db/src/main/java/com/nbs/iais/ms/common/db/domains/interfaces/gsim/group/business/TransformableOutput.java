package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.util.List;

public interface TransformableOutput<I extends IdentifiableArtefact> extends ProcessOutput {

    I getRefersTo();

    void setRefersTo(I refersTo);

    List<ProcessControl> getReviews();

    void setReviews(List<ProcessControl> reviews);
}
