package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StandardTask;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StandardTask")
@Table(name = "standard_tasks")
public class StandardTaskEntity extends AbstractDomainObject implements StandardTask {

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ro")
	private String nameRo;

	@Column(name = "name_ru")
	private String nameRu;

	// bi-directional many-to-one association to StatProcStandTask
	@OneToMany(mappedBy = "standardTask")
	private List<StatProcStandTaskEntity> statProcStandTasks;

}