package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.Gsbpm;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Gsbpm")
@Table(name = "gsbpm", schema = "iais_meta")
public class GsbpmEntity extends AbstractDomainObject implements Gsbpm {

	@Column(name = "id_phase")
	private Long idPhase;

	@Column(name = "id_phase_vers")
	private String idPhaseVers;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ro")
	private String nameRo;

	@Column(name = "name_ru")
	private String nameRu;

	@Column(name = "number_phase")
	private String numberPhase;

	@Column(name = "parent_phase")
	private Integer parentPhase;

	// bi-directional many-to-one association to GsbpmStatProc
	@OneToMany(mappedBy = "gsbpm")
	private List<GsbpmStatProcEntity> gsbpmStatProcs;

}