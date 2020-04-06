package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.ProcInput;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity(name = "ProcInput")
@Table(name = "proc_input",schema = "iais_meta")
 public class ProcInputEntity extends AbstractDomainObject implements ProcInput {

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