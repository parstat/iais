package com.nbs.iais.ms.meta.referential.db.utils;

import org.modelmapper.ModelMapper;

import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.meta.referential.db.domains.StatisticalProcessEntity;

public class Translator {

	public static StatisticalProcessDTO translate(final StatisticalProcessEntity statisticalProcess) {

		final ModelMapper modelMapper = new ModelMapper();
		StatisticalProcessDTO statisticalProcessDTO = modelMapper.map(statisticalProcess, StatisticalProcessDTO.class);

		return statisticalProcessDTO;

	}

	public static DTOList<StatisticalProcessDTO> translate(final Iterable<StatisticalProcessEntity> statisticalProcesses) {

		final DTOList<StatisticalProcessDTO> statisticalProcessesDTOs = DTOList.empty(StatisticalProcessDTO.class);
		statisticalProcesses
				.forEach(statisticalProcessEntity -> statisticalProcessesDTOs.add(translate(statisticalProcessEntity)));

		return statisticalProcessesDTOs;
	}
}
