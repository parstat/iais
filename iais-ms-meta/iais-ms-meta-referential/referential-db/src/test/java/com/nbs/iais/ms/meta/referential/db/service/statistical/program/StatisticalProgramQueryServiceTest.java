package com.nbs.iais.ms.meta.referential.db.service.statistical.program;

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
    public void getStatisticalProgramByLocalIdAndVersionTest() {

        //Setup
        final GetStatisticalProgramQuery query = GetStatisticalProgramQuery.create("local_id", "1.0", Language.ENG);

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(1L);
        statisticalProgram.setName("Name", Language.ENG);
        statisticalProgram.setDescription("Description", Language.ENG);

        Mockito.when(statisticalProgramRepository.findByLocalIdAndVersion(eq(query.getLocalId()), eq(query.getVersion()))).thenReturn(Optional.of(statisticalProgram));

        //Call
        service.getStatisticalProgram(query);

        //Verify
        verify(statisticalProgramRepository).findByLocalIdAndVersion(eq(query.getLocalId()), eq(query.getVersion()));
    }

    @Test
    public void getLatestStatisticalProgramVersionTest() {

        //Setup
        final GetStatisticalProgramQuery query = GetStatisticalProgramQuery.create("local_id", Language.ENG);

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(1L);
        statisticalProgram.setName("Name", Language.ENG);
        statisticalProgram.setDescription("Description", Language.ENG);

        Mockito.when(statisticalProgramRepository.findAllTopByLocalIdOrderByVersionDateDesc(eq(query.getLocalId()))).thenReturn(Optional.of(statisticalProgram));

        //Call
        service.getStatisticalProgram(query);

        //Verify
        verify(statisticalProgramRepository).findAllTopByLocalIdOrderByVersionDateDesc(eq(query.getLocalId()));
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

    @Test
    public void getAllVersionOfStatisticalProgramsTest() {

        //Setup
        final GetStatisticalProgramsQuery query = GetStatisticalProgramsQuery.create(Language.ENG);
        query.setLocalId("lfs");
        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(1L);
        statisticalProgram.setName("Name", Language.ENG);
        statisticalProgram.setDescription("Description", Language.ENG);

        Mockito.when(statisticalProgramRepository.findAllByLocalId(query.getLocalId()))
                .thenReturn(Collections.singletonList(statisticalProgram));

        //Call
        service.getStatisticalPrograms(query);

        //Verify
        verify(statisticalProgramRepository).findAllByLocalId(query.getLocalId());
    }

    @Test
    public void getAllStatisticalProgramsByNameTest() {

        //Setup
        final GetStatisticalProgramsQuery query = GetStatisticalProgramsQuery.create(Language.ENG);
        query.setName("Labor");
        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(1L);
        statisticalProgram.setName("Name", Language.ENG);
        statisticalProgram.setDescription("Description", Language.ENG);

        Mockito.when(statisticalProgramRepository.findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()), eq(query.getName())))
                .thenReturn(Collections.singletonList(statisticalProgram));

        //Call
        service.getStatisticalPrograms(query);

        //Verify
        verify(statisticalProgramRepository).findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()), eq(query.getName()));
    }
}
