package com.nbs.iais.ms.meta.referential.db.service.business.function;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.services.BusinessFunctionQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

public class BusinessFunctionQueryServiceTest extends ServiceTest {

    @Mock
    private BusinessFunctionRepository businessFunctionRepository;

    @InjectMocks
    private BusinessFunctionQueryService service;

    @Test
    public void getBusinessFunctionById() {

        //setup
        final GetBusinessFunctionQuery query = GetBusinessFunctionQuery.create(1L, Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.findById(Mockito.eq(query.getId()))).thenReturn(Optional.of(businessFunction));

        //call
        service.getBusinessFunction(query);

        //verify
        Mockito.verify(businessFunctionRepository).findById(Mockito.eq(query.getId()));

    }

    @Test
    public void getBusinessFunctionByLocalIdAndVersion() {

        //setup
        final GetBusinessFunctionQuery query = GetBusinessFunctionQuery.create("1.1", "5.1", Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.findByLocalIdAndVersion(Mockito.eq(query.getLocalId()), Mockito.eq(query.getVersion()))).thenReturn(Optional.of(businessFunction));

        //call
        service.getBusinessFunction(query);

        //verify
        Mockito.verify(businessFunctionRepository).findByLocalIdAndVersion(Mockito.eq(query.getLocalId()), Mockito.eq(query.getVersion()));

    }

    @Test
    public void getAllBusinessFunctions() {

        //Setup
        final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create(null, 0, null, Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.findAll()).thenReturn(Collections.singletonList(businessFunction));

        //call
        service.getBusinessFunctions(query);

        //verify
        Mockito.verify(businessFunctionRepository).findAll();

    }


    @Test
    public void getAllBusinessFunctionsByName() {

        //Setup
        final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create("Name", 0, null, Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.findAllByNameInLanguageContaining(Mockito.eq(query.getLanguage().getShortName()),
                Mockito.eq(query.getName()))).thenReturn(Collections.singletonList(businessFunction));

        //call
        service.getBusinessFunctions(query);

        //verify
        Mockito.verify(businessFunctionRepository).findAllByNameInLanguageContaining(Mockito.eq(query.getLanguage().getShortName()),
                Mockito.eq(query.getName()));
    }

    @Test
    public void getAllBusinessFunctionsByPhase() {

        //Setup
        final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create(null, 1, null, Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.findAllByLocalIdStartingWith(Mockito.eq(String.valueOf(query.getPhase()))))
                .thenReturn(Collections.singletonList(businessFunction));

        //call
        service.getBusinessFunctions(query);

        //verify
        Mockito.verify(businessFunctionRepository).findAllByLocalIdStartingWith(Mockito.eq(String.valueOf(query.getPhase())));

    }

}
