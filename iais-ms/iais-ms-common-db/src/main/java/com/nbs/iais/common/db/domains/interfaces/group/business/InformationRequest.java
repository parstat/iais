package com.nbs.iais.common.db.domains.interfaces.group.business;

import java.time.LocalDateTime;

public interface InformationRequest extends StatisticalNeed {

    String getCoverageOfInformationRequired();

    void setCoverageOfInformationRequired(String coverageOfInformationRequired);

    LocalDateTime getDateInformationRequired();

    void setDateInformationRequired(LocalDateTime dateInformationRequired);

}
