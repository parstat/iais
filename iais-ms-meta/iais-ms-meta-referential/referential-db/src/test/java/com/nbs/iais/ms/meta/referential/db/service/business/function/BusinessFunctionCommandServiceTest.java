package com.nbs.iais.ms.meta.referential.db.service.business.function;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.BusinessFunctionEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.BusinessFunctionRepository;
import com.nbs.iais.ms.meta.referential.db.services.BusinessFunctionCommandService;
import com.nbs.iais.ms.meta.referential.db.services.BusinessFunctionQueryService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class BusinessFunctionCommandServiceTest extends ServiceTest {


    @Mock
    private BusinessFunctionRepository businessFunctionRepository;

    @InjectMocks
    private BusinessFunctionCommandService service;

    @Test
    public void createBusinessFunctionTest() {
        //Setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateBusinessFunctionCommand command = CreateBusinessFunctionCommand.create(jwt, "Name",
                "Description", "1.1", Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.save(Mockito.any(BusinessFunctionEntity.class))).thenReturn(businessFunction);

        //call
        service.createBusinessFunction(command);

        //Verify
        Mockito.verify(businessFunctionRepository).save(Mockito.any(BusinessFunctionEntity.class));
    }

    @Test
    public void updateBusinessFunctionTest() {
        //Setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateBusinessFunctionCommand command = UpdateBusinessFunctionCommand.create(jwt, 1L,
                "Description", "1.1", Language.ENG);

        final BusinessFunctionEntity businessFunction = new BusinessFunctionEntity();
        businessFunction.setId(1L);
        businessFunction.setName("Name", Language.ENG);
        businessFunction.setDescription("Description", Language.ENG);
        businessFunction.setLocalId("1.1");

        Mockito.when(businessFunctionRepository.findById(Mockito.eq(command.getId()))).thenReturn(Optional.of(businessFunction));
        Mockito.when(businessFunctionRepository.save(Mockito.any(BusinessFunctionEntity.class))).thenReturn(businessFunction);

        //call
        service.updateBusinessFunction(command);

        //Verify
        Mockito.verify(businessFunctionRepository).findById(Mockito.eq(command.getId()));
        Mockito.verify(businessFunctionRepository).save(Mockito.any(BusinessFunctionEntity.class));
    }
}
