package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import java.time.LocalDateTime;

public interface InformationRequest extends StatisticalNeed {

    String getCoverageOfInformationRequired();

    void setCoverageOfInformationRequired(String coverageOfInformationRequired);

    LocalDateTime getDateInformationRequired();

    void setDateInformationRequired(LocalDateTime dateInformationRequired);

}
