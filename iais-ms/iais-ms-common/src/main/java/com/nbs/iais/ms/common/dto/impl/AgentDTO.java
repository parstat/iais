package com.nbs.iais.ms.common.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.LinkableIdentifiableArtefactDTO;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AgentType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentDTO extends LinkableIdentifiableArtefactDTO {

    private static final long serialVersionUID = 2864464354903875090L;

    @JsonProperty
    @JsonView(Views.Extended.class)
    private DTOList<AgentMiniDTO> children;
    
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private AgentType type;
    
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private Long account;
    
    @JsonProperty
    @JsonView(Views.Minimal.class)
    private AgentMiniDTO parent;

    public Long getAccount() {
		return account;
	}

	public AgentType getType() {
		return type;
	}

	public void setType(AgentType type) {
		this.type = type;
	}

	public AgentMiniDTO getParent() {
		return parent;
	}

	public void setParent(AgentMiniDTO parent) {
		this.parent = parent;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public AgentDTO() {
        super();
    }

    public AgentDTO(final Long id) {
        super(id);
    }

    public DTOList<AgentMiniDTO> getChildren() {
        return children;
    }

    public void setChildren(final DTOList<AgentMiniDTO> children) {
        this.children = children;
    }
}
