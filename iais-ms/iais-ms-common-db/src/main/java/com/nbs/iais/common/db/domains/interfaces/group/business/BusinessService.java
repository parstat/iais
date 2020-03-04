package com.nbs.iais.common.db.domains.interfaces.group.business;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface BusinessService extends IdentifiableArtefact {

    List<String> getServiceInterfaces();

    void setServiceInterfaces(List<String> serviceInterfaces);

}
