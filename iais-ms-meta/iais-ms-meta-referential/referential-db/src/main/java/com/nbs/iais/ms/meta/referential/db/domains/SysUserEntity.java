package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.SysUser;


@Data
@Entity(name="SysUser")
@Table(name="sys_user")
public class SysUserEntity implements SysUser {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String username;

	@Column(name="app_lang")
	private String appLang;

	@Column(name="e_mail")
	private String eMail;

	@Column(name="end_date")
	private Date endDate;

	private String name;

	@Column(name="pass_change")
	private Integer passChange;

	@Column(name="pass_hash")
	private String passHash;

	private String patronimic;

	private String phone;

	@Column(name="start_date")
	private Date startDate;

	private String surname;

	@Column(name="sys_admin")
	private Integer sysAdmin;

	@Column(name="sys_date")
	private Date sysDate;

	//bi-directional many-to-one association to GsbpmStatProc
	@OneToMany(mappedBy="sysUserBean")
	private List<GsbpmStatProcEntity> gsbpmStatProcs;

	//bi-directional many-to-one association to StatisticalProcess
	@OneToMany(mappedBy="sysUserBean")
	private List<StatisticalProcessEntity> statisticalProcesses;

	//bi-directional many-to-one association to SysRole
	@ManyToOne
	@JoinColumn(name="role_code")
	private SysRoleEntity sysRole;

	
}