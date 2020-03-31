package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatProcInput;

@Data
@Entity(name="StatProcInput")
@Table(name="stat_proc_input")
public class StatProcInputEntity implements StatProcInput {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="order_code")
	private Short orderCode;

	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to GsbpmStatProc
	@ManyToOne
	@JoinColumn(name="id_gsbpm_stat_proc")
	private GsbpmStatProcEntity gsbpmStatProc;

	//bi-directional many-to-one association to ProcInput
	@ManyToOne
	@JoinColumn(name="id_input")
	private ProcInputEntity input;

}