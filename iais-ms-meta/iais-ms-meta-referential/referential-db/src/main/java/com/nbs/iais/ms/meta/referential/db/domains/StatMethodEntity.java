package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StatMethod;

@Data
@Entity(name="StatMethod")
@Table(name="stat_methods")
public class StatMethodEntity implements StatMethod {
 
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_methods")
	private Long idMethods;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcStatMeth
	@OneToMany(mappedBy="statMethod")
	private List<StatProcStatMethEntity> statProcStatMeths;

	public StatMethodEntity() {
	}



}