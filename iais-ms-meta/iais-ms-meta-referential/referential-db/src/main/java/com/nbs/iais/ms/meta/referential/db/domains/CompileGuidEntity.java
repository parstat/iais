package com.nbs.iais.ms.meta.referential.db.domains;


import javax.persistence.*;

import com.nbs.iais.ms.common.db.domains.interfaces.meta.CompileGuid;

import lombok.Data;

@Data
@Entity(name="CompileGuid")
@Table(name="compile_guid")
public class CompileGuidEntity implements CompileGuid {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_compile_guide")
	private Long idCompileGuide;

	@Column(name="example_en")
	private String exampleEn;

	@Column(name="example_ro")
	private String exampleRo;

	@Column(name="example_ru")
	private String exampleRu;

	@Column(name="guide_en")
	private String guideEn;

	@Column(name="guide_ro")
	private String guideRo;

	@Column(name="guide_ru")
	private String guideRu;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

}