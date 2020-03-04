package com.nbs.iais.common.db.domains.interfaces.group.base;

import java.util.List;

public interface ChangeEventTuple extends IdentifiableArtefact {

    List<? extends IdentifiableArtefact> getSources();

    void setSources(List<? extends IdentifiableArtefact> sources);

    List<? extends IdentifiableArtefact> getTargets();

    void setTargets(List<? extends IdentifiableArtefact> targets);

}
