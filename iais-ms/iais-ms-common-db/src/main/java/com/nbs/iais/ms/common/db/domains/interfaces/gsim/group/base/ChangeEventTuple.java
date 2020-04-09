package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base;


public interface ChangeEventTuple extends IdentifiableArtefact {

   ChangeEvent getChangeEvent();

   void setChangeEvent(ChangeEvent changeEvent);
}
