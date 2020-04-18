package com.nbs.iais.ms.common.dto.abstracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;

import java.time.LocalDateTime;

public class LinkableIdentifiableArtefactDTO extends LinkableEntityDTO{

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String localId;

    @JsonProperty
    @JsonView(Views.Minimal.class)
    private String name;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private String version;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private String description;

    @JsonProperty
    @JsonView(Views.Secure.class)
    private LocalDateTime versionDate;

    @JsonProperty
    @JsonView(Views.Secure.class)
    private String versionRationale;

    public LinkableIdentifiableArtefactDTO() {
        super();
    }

    public LinkableIdentifiableArtefactDTO(final Long id) {
        super(id);
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(final LocalDateTime versionDate) {
        this.versionDate = versionDate;
    }

    public String getVersionRationale() {
        return versionRationale;
    }

    public void setVersionRationale(final String versionRationale) {
        this.versionRationale = versionRationale;
    }
}
