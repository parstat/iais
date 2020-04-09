package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

public interface Agent extends IdentifiableArtefact {

    Agent getParent();

    void setParent(Agent agent);


}
