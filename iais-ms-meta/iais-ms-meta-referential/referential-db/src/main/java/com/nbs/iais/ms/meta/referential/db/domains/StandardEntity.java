package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Standard;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Standard")
@Table(name = "quality_control", schema = "iais_meta")
public class StandardEntity extends AbstractDomainObject implements Standard {

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ro")
	private String nameRo;

	@Column(name = "name_ru")
	private String nameRu;

	// bi-directional many-to-one association to StatProcStandard
	@OneToMany(mappedBy = "standard")
	private List<StatProcStandardEntity> statProcStandards;

}