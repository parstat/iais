package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.concept;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

public interface Node extends IdentifiableArtefact {

    Node getParent();

    void setParent(Node parent);

    Category getCategory();

    void setCategory(Category category);

    String getAggregationType();

    void setAggregationType(String aggregationType);
}
