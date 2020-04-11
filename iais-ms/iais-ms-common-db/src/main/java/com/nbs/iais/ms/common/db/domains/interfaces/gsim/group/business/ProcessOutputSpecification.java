package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.ProcessOutputType;

import java.util.List;

public interface ProcessOutputSpecification extends IdentifiableArtefact {

    List<ProcessOutputType> getProcessOutputTypes();

    void setProcessOutputTypes(List<ProcessOutputType> processOutputTypes);

    List<ProcessDesign> getProcessDesigns();

    void setProcessDesigns(List<ProcessDesign> processDesigns);

}
