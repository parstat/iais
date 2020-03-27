package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.RuleType;

import java.util.List;

public interface Rule extends IdentifiableArtefact {

    String getAlgorithm();

    void setAlgorithm(String algorithm);

    List<String> getCommandCodes();

    void setCommandCodes(List<String> commandCodes);

    String getExpression();

    void setExpression(String expression);

    boolean isSystemExecutable();

    void setSystemExecutable(boolean systemExecutable);

    RuleType getRuleType();

    void setRuleType(RuleType ruleType);

    List<ProcessMethod> getProcessMethods();

    void setProcessMethods(List<ProcessMethod> processMethods);

    List<ProcessControlDesign> getProcessControlDesigns();

    void setProcessControlDesigns(List<ProcessControlDesign> processControlDesigns);

}
