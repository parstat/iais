package com.nbs.iais.ms.meta.referential.db.utils;

import org.modelmapper.ModelMapper;

import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.meta.referential.db.domains.StatisticalProcessEntity;



public class Translator {
 
	public static StatisticalProcessDTO convertToDto(StatisticalProcessEntity statisticalProcess) {
		final ModelMapper modelMapper = new ModelMapper();
		StatisticalProcessDTO statisticalProcessDTO = modelMapper.map(statisticalProcess, StatisticalProcessDTO.class);

		return statisticalProcessDTO;

	}
}
