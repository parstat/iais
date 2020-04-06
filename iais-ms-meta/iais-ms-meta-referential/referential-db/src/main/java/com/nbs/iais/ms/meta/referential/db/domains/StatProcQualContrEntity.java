package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatProcQualContr;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StatProcQualContr")
@Table(name = "stat_proc_qual_contr")
public class StatProcQualContrEntity extends AbstractDomainObject implements StatProcQualContr {

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "\"ORDER\"")
	private Integer order;

	@Column(name = "start_date")
	private Date startDate;

	// bi-directional many-to-one association to GsbpmStatProc
	@ManyToOne
	@JoinColumn(name = "id_gsbpm_stat_proc")
	private GsbpmStatProcEntity gsbpmStatProc;

	// bi-directional many-to-one association to QualityControl
	@ManyToOne
	@JoinColumn(name = "id_qual_contr")
	private QualityControlEntity qualityControl;

}