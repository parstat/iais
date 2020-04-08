package com.nbs.iais.ms.meta.referential.db.domains;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.GsbpmStatProc;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity(name = "GsbpmStatProc")
@Table(name = "gsbpm_stat_proc",schema = "iais_meta")
public class GsbpmStatProcEntity extends AbstractDomainObject implements GsbpmStatProc {

	@Column(name = "description_en")
	private String descriptionEn;

	@Column(name = "description_ro")
	private String descriptionRo;

	@Column(name = "description_ru")
	private String descriptionRu;

	@Column(name = "division_id")
	private Integer divisionId;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "next_step")
	private Integer nextStep;

	@Column(name = "order_code")
	private Short orderCode;

	@Column(name = "previous_step")
	private Integer previousStep;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "creation_date")
	private Date creationDate;

	// bi-directional many-to-one association to Gsbpm
	@ManyToOne
	@JoinColumn(name = "id_gsbpm")
	private GsbpmEntity gsbpm;

	// bi-directional many-to-one association to StatisticalProcess
	@ManyToOne
	@JoinColumn(name = "id_stat_proc")
	private StatisticalProcessEntity statisticalProcess;

	@JoinColumn(name = "contact_user")
	private Long contactUser;
 

	// bi-directional many-to-one association to StatProcInput
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcInputEntity> statProcInputs;

	// bi-directional many-to-one association to StatProcOutput
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcOutputEntity> statProcOutputs;

	// bi-directional many-to-one association to StatProcQualContr
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcQualContrEntity> statProcQualContrs;

	// bi-directional many-to-one association to StatProcSoftware
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcSoftwareEntity> statProcSoftwares;

	// bi-directional many-to-one association to StatProcStandTask
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcStandTaskEntity> statProcStandTasks;

	// bi-directional many-to-one association to StatProcStandard
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcStandardEntity> statProcStandards;

	// bi-directional many-to-one association to StatProcStatMeth
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatProcStatMethEntity> statProcStatMeths;

	// bi-directional many-to-one association to StatPocDocument
	@OneToMany(mappedBy = "gsbpmStatProc")
	private List<StatPocDocumentEntity> statRpocDocuments;

}