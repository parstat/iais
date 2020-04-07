package com.nbs.iais.ms.common.dto.abstracts;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;

public class LinkableEntityDTO extends BaseEntityDTO {

    @JsonProperty
    @JsonView(Views.Basic.class)
    private String link;

    public LinkableEntityDTO() {
        super();
    }

    public LinkableEntityDTO(final Long id) {
        super(id);
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }
}
