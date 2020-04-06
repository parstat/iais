package com.nbs.iais.ms.meta.referential.db.domains;

import javax.persistence.*;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Division;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Division")
@Table(name = "division", schema = "iais_meta")
public class DivisionEntity extends AbstractDomainObject implements Division {

	@Column(name = "acronym_en")
	private String acronymEn;

	@Column(name = "acronym_ro")
	private String acronymRo;

	@Column(name = "acronym_ru")
	private String acronymRu;

	@Column(name = "description_en")
	private String descriptionEn;

	@Column(name = "description_ro")
	private String descriptionRo;

	@Column(name = "description_ru")
	private String descriptionRu;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ro")
	private String nameRo;

	@Column(name = "name_ru")
	private String nameRu;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "sys_date")
	private Date sysDate;

	@Column(name = "sys_user")
	private String sysUser;

	// bi-directional one-to-one association to DivisionStatus
	@OneToOne
	@JoinColumn(name = "id")
	private DivisionStatusEntity divisionStatus;

	// bi-directional many-to-one association to StatisticalProcess
	@OneToMany(mappedBy = "division")
	private List<StatisticalProcessEntity> statisticalProcesses;

}