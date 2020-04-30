package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.enums.AdministrativeStatus;
import com.nbs.iais.ms.common.enums.LifeCycleStatus;

import java.time.LocalDateTime;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdministativeDetailsDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;
 
    @JsonProperty
    @JsonView(Views.Basic.class)
    private String alias;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private AdministrativeStatus administrativeStatus;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private LocalDateTime lastUpdated;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private LifeCycleStatus lifeCycleStatus;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private LocalDateTime releaseDate;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private LocalDateTime validFrom;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private LocalDateTime validTo;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private List<String> documentations;

    @JsonProperty
    @JsonView(Views.Basic.class)
    private List<String> urls;

    public AdministativeDetailsDTO() {
        super();
    }

    public AdministativeDetailsDTO(final Long id) {
        super(id);
    }

  
}
