package com.nbs.iais.ms.meta.referential.db.domains;
 
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.LawType;

@Data
@Entity(name="LawType")
@Table(name="law_type",schema = "iais_meta")
public class LawTypeEntity implements LawType {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_law_type")
	private Long idLawType;

	@Column(name="type_en")
	private String typeEn;

	@Column(name="type_ro")
	private String typeRo;

	@Column(name="type_ru")
	private String typeRu;

	//bi-directional many-to-one association to Law
	@OneToMany(mappedBy="lawType")
	private List<LawEntity> laws;

	 
}