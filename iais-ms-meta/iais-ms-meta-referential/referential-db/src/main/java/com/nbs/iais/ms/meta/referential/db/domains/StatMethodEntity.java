package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatMethod;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StatMethod")
@Table(name = "stat_methods")
public class StatMethodEntity extends AbstractDomainObject implements StatMethod {

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ro")
	private String nameRo;

	@Column(name = "name_ru")
	private String nameRu;

	// bi-directional many-to-one association to StatProcStatMeth
	@OneToMany(mappedBy = "statMethod")
	private List<StatProcStatMethEntity> statProcStatMeths;

	public StatMethodEntity() {
	}

}