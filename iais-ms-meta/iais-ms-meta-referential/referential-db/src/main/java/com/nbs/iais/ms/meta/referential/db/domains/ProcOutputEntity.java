package com.nbs.iais.ms.meta.referential.db.domains;
 
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.ProcOutput;

@Data
@Entity(name = "ProcOutput")
@Table(name = "proc_output",schema = "iais_meta")
public class ProcOutputEntity implements ProcOutput {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_output")
	private Long idOutput;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcOutput
	@OneToMany(mappedBy="output")
	private List<StatProcOutputEntity> statProcOutputs;

	 
}