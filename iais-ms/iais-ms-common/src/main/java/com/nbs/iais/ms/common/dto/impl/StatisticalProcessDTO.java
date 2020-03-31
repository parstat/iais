package com.nbs.iais.ms.common.dto.impl;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.abstracts.BaseEntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticalProcessDTO extends BaseEntityDTO implements Serializable {

	private static final long serialVersionUID = 2864464354903875090L;
	@JsonProperty
	@JsonView(Views.Basic.class)
	private Long idStatProc;
	@JsonProperty
	@JsonView(Views.Basic.class)
	private String acronymEn;
	@JsonProperty
	@JsonView(Views.Basic.class)
	private String acronymRo;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String acronymRu;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String nameEn;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String nameRo;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String nameRu;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String respFullName;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String respMail;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private String respPhone;

	@JsonProperty
	@JsonView(Views.Basic.class)
	private Date sysDate;

	public StatisticalProcessDTO(Long idStatProc) {
		super();
		this.idStatProc = idStatProc;
	}

	public StatisticalProcessDTO() {

	}
}
