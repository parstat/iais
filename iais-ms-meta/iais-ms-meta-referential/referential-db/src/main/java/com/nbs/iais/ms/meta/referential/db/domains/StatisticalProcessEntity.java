package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Division;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.GsbpmStatProc;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Law;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatisticalProcess;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.SysUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StatisticalProcess")
@Table(name = "statistical_process")
public class StatisticalProcessEntity extends AbstractDomainObject implements StatisticalProcess {

	@Column(name = "acronym_en")
	private String acronymEn;

	@Column(name = "acronym_ro")
	private String acronymRo;

	@Column(name = "acronym_ru")
	private String acronymRu;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ro")
	private String nameRo;

	@Column(name = "name_ru")
	private String nameRu;

	@Column(name = "resp_full_name")
	private String respFullName;

	@Column(name = "resp_mail")
	private String respMail;

	@Column(name = "resp_phone")
	private String respPhone;

	@Column(name = "sys_date")
	private Date sysDate;

	// bi-directional many-to-one association to GsbpmStatProc
	@OneToMany(mappedBy = "statisticalProcess", targetEntity = GsbpmStatProcEntity.class)
	private List<GsbpmStatProc> gsbpmStatProcs;

	// bi-directional many-to-one association to Division
	@ManyToOne(targetEntity = DivisionEntity.class)
	@JoinColumn(name = "division_id")
	private Division division;

	// bi-directional many-to-many association to Law
	@ManyToMany(targetEntity = LawEntity.class)
	@JoinTable(name = "stat_proc_law", joinColumns = { @JoinColumn(name = "id_stat_proc") }, inverseJoinColumns = {
			@JoinColumn(name = "id_law") })
	private List<Law> laws;

	// bi-directional many-to-one association to SysUser
	@ManyToOne(targetEntity = SysUserEntity.class)
	@JoinColumn(name = "sys_user")
	private SysUser sysUserBean;

}