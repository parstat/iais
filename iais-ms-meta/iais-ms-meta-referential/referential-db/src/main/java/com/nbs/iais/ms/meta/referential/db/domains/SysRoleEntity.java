package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.SysRole;

@Data
@Entity(name="SysRole")
@Table(name="sys_role")
public class SysRoleEntity implements SysRole {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_code")
	private String roleCode;

	@Column(name="description_en")
	private String descriptionEn;

	@Column(name="description_ro")
	private String descriptionRo;

	@Column(name="description_ru")
	private String descriptionRu;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="sys_date")
	private Date sysDate;

	@Column(name="sys_user")
	private String sysUser;

	//bi-directional many-to-one association to SysUser
	@OneToMany(mappedBy="sysRole")
	private List<SysUserEntity> sysUsers;


}