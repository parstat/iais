package com.nbs.iais.ms.meta.referential.db.domains;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractDomainObject;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.DivisionStatus;


@Data
@EqualsAndHashCode(callSuper=true)
@Entity(name="DivisionStatus")
@Table(name="division_status")
public class DivisionStatusEntity extends AbstractDomainObject implements DivisionStatus {


	private String name;

	//bi-directional one-to-one association to Division
	@OneToOne(mappedBy="divisionStatus")
	private DivisionEntity division;


}