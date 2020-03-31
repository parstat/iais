package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.ProcInput;

@Data
@Entity(name = "ProcInput")
@Table(name = "proc_input",schema = "iais_meta")
 public class ProcInputEntity implements ProcInput {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_input")
	private Long idInput;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcInput
	@OneToMany(mappedBy="input")
	private List<StatProcInputEntity> statProcInputs;

}