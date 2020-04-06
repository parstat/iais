package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.QualityControl;

@Data
@Entity(name="QualityControl")
@EqualsAndHashCode(callSuper=true)
@Table(name="quality_control",schema = "iais_meta") 
public class QualityControlEntity extends AbstractDomainObject implements QualityControl {


	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcQualContr
	@OneToMany(mappedBy="qualityControl")
	private List<StatProcQualContrEntity> statProcQualContrs;



}