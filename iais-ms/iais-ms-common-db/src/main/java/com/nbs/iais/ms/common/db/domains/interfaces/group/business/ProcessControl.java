package com.nbs.iais.ms.common.db.domains.interfaces.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface ProcessControl extends IdentifiableArtefact {

    ProcessStep getExecutorProcessStep();

    void setExecutorProcessStep(ProcessStep processStep);

    List<ProcessStep> getInvokes();

    void setInvokes(List<ProcessStep> invokes);

    List<ProcessExecutionLog> getUpdates();

    void setUpdates(List<ProcessExecutionLog> updates);


}
