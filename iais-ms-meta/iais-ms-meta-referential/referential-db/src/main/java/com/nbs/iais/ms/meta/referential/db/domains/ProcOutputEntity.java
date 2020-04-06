package com.nbs.iais.ms.meta.referential.db.domains;
 
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.ProcOutput;

@Data
@Entity(name = "ProcOutput")
@EqualsAndHashCode(callSuper=true)
@Table(name = "proc_output",schema = "iais_meta")
public class ProcOutputEntity extends AbstractDomainObject implements ProcOutput {

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