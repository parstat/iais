package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Software;

@Data
@Entity(name = "Software")
@Table(name = "software",schema = "iais_meta")
public class SoftwareEntity implements Software {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_soft")
	private Long idSoft;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcSoftware
	@OneToMany(mappedBy="software")
	private List<StatProcSoftwareEntity> statProcSoftwares;

	
}