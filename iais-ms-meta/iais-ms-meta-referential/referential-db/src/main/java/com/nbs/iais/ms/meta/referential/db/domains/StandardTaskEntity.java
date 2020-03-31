package com.nbs.iais.ms.meta.referential.db.domains;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import com.nbs.iais.ms.common.db.domains.interfaces.meta.StandardTask;

@Data
@Entity(name="StandardTask")
@Table(name="standard_tasks")
public class StandardTaskEntity implements StandardTask {
 	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_standard_task")
	private Long idStandardTask;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_ro")
	private String nameRo;

	@Column(name="name_ru")
	private String nameRu;

	//bi-directional many-to-one association to StatProcStandTask
	@OneToMany(mappedBy="standardTask")
	private List<StatProcStandTaskEntity> statProcStandTasks;


}