package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatProcSoftware;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StatProcSoftware")
@Table(name = "stat_proc_software")
public class StatProcSoftwareEntity extends AbstractDomainObject implements StatProcSoftware {

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

	// bi-directional many-to-one association to Software
	@ManyToOne
	@JoinColumn(name = "id_soft")
	private SoftwareEntity software;

}