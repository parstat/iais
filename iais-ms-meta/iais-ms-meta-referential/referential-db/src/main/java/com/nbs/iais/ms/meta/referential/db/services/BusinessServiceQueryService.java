package com.nbs.iais.ms.meta.referential.db.services;

import com.nbs.iais.ms.meta.referential.db.repositories.BusinessServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceQueryService {

    @Autowired
    private BusinessServiceRepository businessServiceRepository;


}
