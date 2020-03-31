package com.nbs.iais.ms.meta.referential.db.domains;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatPocDocument;

@Data
@Entity(name="StatPocDocument")
@Table(name="stat_rpoc_document")
public class StatPocDocumentEntity implements StatPocDocument {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="order_code")
	private Short orderCode;

	
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to ProcDocument
	@ManyToOne
	@JoinColumn(name="id_document")
	private ProcDocumentEntity document;

	//bi-directional many-to-one association to GsbpmStatProc
	@ManyToOne
	@JoinColumn(name="id_gsbpm_stat_proc")
	private GsbpmStatProcEntity gsbpmStatProc;

}