package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;
import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatProcOutput;

@Data
@Entity(name="StatProcOutput")
@Table(name="stat_proc_output")
public class StatProcOutputEntity implements StatProcOutput {
	
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

	//bi-directional many-to-one association to ProcOutput
	@ManyToOne
	@JoinColumn(name="id_output")
	private ProcOutputEntity output;


}