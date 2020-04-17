package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocument;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.enums.ProcessOutputType;

import java.util.List;

public interface ProcessOutputSpecification extends IdentifiableArtefact {

    List<ProcessOutputType> getProcessOutputTypes();

    void setProcessOutputTypes(List<ProcessOutputType> processOutputTypes);

    //ProcessDesign getProcessDesign();

    //void setProcessDesign(ProcessDesign processDesign);

    ProcessDocumentation getProcessDocumentation();

    void setProcessDocumentation(ProcessDocumentation processDocumentation);
}
