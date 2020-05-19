package com.nbs.iais.ms.meta.referential.db.service;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalProgramRepository;
import com.nbs.iais.ms.meta.referential.db.services.StatisticalProgramQueryService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


public class StatisticalProgramQueryServiceTest extends ServiceTest {

    @Mock
    private StatisticalProgramRepository statisticalProgramRepository;

    @InjectMocks
    private StatisticalProgramQueryService service;

    @Test
    public void getStatisticalProgramByIdTest() {

        //Setup
        final GetStatisticalProgramQuery query = GetStatisticalProgramQuery.create(1L, Language.ENG);

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(1L);
        statisticalProgram.setName("Name", Language.ENG);
        statisticalProgram.setDescription("Description", Language.ENG);

        Mockito.when(statisticalProgramRepository.findById(Mockito.eq(query.getId()))).thenReturn(Optional.of(statisticalProgram));

        //Call
        service.getStatisticalProgram(query);

        //Verify
        verify(statisticalProgramRepository).findById(eq(query.getId()));
    }

    @Test
    public void getAllStatisticalProgramsTest() {

        //Setup
        final GetStatisticalProgramsQuery query = GetStatisticalProgramsQuery.create(Language.ENG);

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(1L);
        statisticalProgram.setName("Name", Language.ENG);
        statisticalProgram.setDescription("Description", Language.ENG);

        Mockito.when(statisticalProgramRepository.findAll()).thenReturn(Collections.singletonList(statisticalProgram));

        //Call
        service.getStatisticalPrograms(query);

        //Verify
        verify(statisticalProgramRepository).findAll();
    }
}
