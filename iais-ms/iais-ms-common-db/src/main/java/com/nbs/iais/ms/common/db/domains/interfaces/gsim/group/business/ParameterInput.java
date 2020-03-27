package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.enums.ParameterDataType;
import com.nbs.iais.ms.common.enums.ParameterRole;

import java.util.List;

public interface ParameterInput extends ProcessInputSpecifications {

    ParameterDataType getParameterDataType();

    void setParameterDataType(ParameterDataType parameterDataType);

    List<ParameterRole> getParameterRoles();

    void setParameterRoles(List<ParameterRole> parameterRoles);

    String getParameterValue();

    void setParameterValue(String parameterValue);

}
