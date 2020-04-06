package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatProcStandTask;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StatProcStandTask")
@Table(name = "stat_proc_stand_tasks")
public class StatProcStandTaskEntity extends AbstractDomainObject implements StatProcStandTask {

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "order_code")
	private Short orderCode;

	@Column(name = "start_date")
	private Date startDate;

	// bi-directional many-to-one association to GsbpmStatProc
	@ManyToOne
	@JoinColumn(name = "id_gsbpm_stat_proc")
	private GsbpmStatProcEntity gsbpmStatProc;

	// bi-directional many-to-one association to StandardTask
	@ManyToOne
	@JoinColumn(name = "id_standard_task")
	private StandardTaskEntity standardTask;

}