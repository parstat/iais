package com.nbs.iais.ms.meta.referential.db.domains;


import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Standard;

@Data
@Entity(name="Standard") 
@Table(name="quality_control",schema = "iais_meta") 
public class StandardEntity implements Standard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_standard")
	private Long idStandard;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcStandard
	@OneToMany(mappedBy="standard")
	private List<StatProcStandardEntity> statProcStandards;


}