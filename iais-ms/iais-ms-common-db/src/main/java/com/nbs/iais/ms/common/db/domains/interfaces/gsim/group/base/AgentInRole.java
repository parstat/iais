package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;

import com.nbs.iais.ms.common.db.domains.interfaces.DomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;

import java.util.List;


public interface AgentInRole extends DomainObject {

    MultilingualText getName();

    void setName(MultilingualText name);

    MultilingualText getDescription();

    void setDescription(MultilingualText description);

    ChangeEvent getChangeEvent();

    void setChangeEvent(ChangeEvent changeEvent);

    Agent getAgent();

    void setAgent(Agent agent);

    Role getRole();

    void setRole(Role role);

    List<StatisticalProgram> getStatisticalPrograms();

    void setStatisticalPrograms(List<StatisticalProgram> statisticalPrograms);

    List<ProcessDocumentation> getProcessDocumentations();

    void setProcessDocumentations(List<ProcessDocumentation> processDocumentations);
}
