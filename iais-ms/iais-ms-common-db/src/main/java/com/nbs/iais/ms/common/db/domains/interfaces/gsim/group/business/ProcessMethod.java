package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;

import java.util.List;

public interface ProcessMethod extends IdentifiableArtefact {

    List<Rule> getRules();

    void setRules(List<Rule> rules);

}
