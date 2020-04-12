package com.nbs.iais.ms.common.enums;

public enum RoleType {

    //role of the user that's creates the artefact
    CREATOR,

    //often an organization is the owner of the artefact
    OWNER,

    //often an individual working in an organization is the contact person
    CONTACT,

    //often an organization that maintains the artefact
    MAINTAINER,

    INFORMATION_CONSUMER,

    INFORMATION_PROVIDER
}
