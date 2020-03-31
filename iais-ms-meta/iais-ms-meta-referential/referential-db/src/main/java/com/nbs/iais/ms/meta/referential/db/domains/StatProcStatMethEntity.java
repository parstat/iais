package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatProcStatMeth;

@Data
@Entity(name="StatProcStatMeth")
@Table(name="stat_proc_stat_meth")
public class StatProcStatMethEntity implements StatProcStatMeth {
    
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

	//bi-directional many-to-one association to StatMethod
	@ManyToOne
	@JoinColumn(name="id_methods")
	private StatMethodEntity statMethod;



}