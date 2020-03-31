package com.nbs.iais.ms.meta.referential.db.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.common.enums.ExceptionCodes;
import com.nbs.iais.ms.common.exceptions.RefentialServiceException;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProcessRepository;
import com.nbs.iais.ms.meta.referential.db.utils.Translator;
import com.nbs.iais.ms.referential.common.messageing.commands.StatisticalProcessQueryCommand;




@Service
public class ReferentialService {

    @Autowired
    StatisticalProcessRepository statisticalProcessRepository;
 
    public StatisticalProcessDTO findStatisticalProcessById(final StatisticalProcessQueryCommand query) {
        return Translator.convertToDto(statisticalProcessRepository.findById(query.getId()).orElseThrow(() ->
        new RefentialServiceException(ExceptionCodes.NOT_FOUND)));
    }
}
