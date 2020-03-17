package com.nbs.iais.common.db.domains.interfaces.group.concept;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;

public interface Node extends IdentifiableArtefact {

    Node getParent();

    void setParent(Node parent);

    Category getCategory();

    void setCategory(Category category);

    String getAggregationType();

    void setAggregationType(String aggregationType);
}
