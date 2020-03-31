package com.nbs.iais.ms.meta.referential.db.domains;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.DivisionStatus;


@Data
@Entity(name="DivisionStatus")
@Table(name="division_status")
public class DivisionStatusEntity implements DivisionStatus {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	//bi-directional one-to-one association to Division
	@OneToOne(mappedBy="divisionStatus")
	private DivisionEntity division;


}