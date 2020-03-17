package com.nbs.iais.ms.common.db.domains.interfaces.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface ProcessMethod extends IdentifiableArtefact {

    List<Rule> getRules();

    void setRules(List<Rule> rules);

    List<ProcessDesign> getProcessDesigns();

    void setProcessDesigns(List<ProcessDesign> processDesigns);
}
