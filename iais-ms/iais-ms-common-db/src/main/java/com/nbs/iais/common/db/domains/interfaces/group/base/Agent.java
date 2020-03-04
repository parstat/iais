package com.nbs.iais.common.db.domains.interfaces.group.base;

public interface Agent extends IdentifiableArtefact {

    Agent getParent();

    void setParent(Agent agent);

}
