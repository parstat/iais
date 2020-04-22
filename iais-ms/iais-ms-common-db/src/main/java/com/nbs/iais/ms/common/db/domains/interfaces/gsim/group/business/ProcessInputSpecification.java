package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.enums.ProcessInputType;

import java.util.List;

public interface ProcessInputSpecification extends IdentifiableArtefact {

    List<ProcessInputType> getProcessInputTypes();

    void setProcessInputTypes(List<ProcessInputType> processInputTypes);

    //ProcessDesign getProcessDesign();

    //void setProcessDesign(ProcessDesign processDesign);

    ProcessDocumentation getProcessDocumentation();

    void setProcessDocumentation(ProcessDocumentation processDocumentation);
}
