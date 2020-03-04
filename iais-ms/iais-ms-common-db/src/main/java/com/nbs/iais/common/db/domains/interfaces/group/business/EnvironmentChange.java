package com.nbs.iais.common.db.domains.interfaces.group.business;

import java.util.List;

public interface EnvironmentChange extends StatisticalNeed {

    String getChangeOrigin();

    void setChangeOrigin(String changeOrigin);

    List<String> getLegalChanges();

    void setLegalChanges(List<String> legalChanges);

    List<String> getMethodChanges();

    void setMethodChanges(List<String> methodChanges);

    List<String> getOtherChanges();

    void setOtherChanges(List<String> otherChanges);

    List<String> getSoftwareChanges();

    void setSoftwareChanges(List<String> softwareChanges);

}
