package com.nbs.iais.common.db.domains.interfaces.group.business;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface TransformableOutput<I extends IdentifiableArtefact> extends ProcessOutput {

    I getRefersTo();

    void setRefersTo(I refersTo);

    List<ProcessControl> getReviews();

    void setReviews(List<ProcessControl> reviews);
}
