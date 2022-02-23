package com.nbs.iais.ms.meta.referential.db.service.statistical.standard;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.services.StatisticalStandardQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StatisticalStandardQueryServiceTest extends ServiceTest {

    @Mock
    private StatisticalStandardReferenceRepository repository;

    @InjectMocks
    private StatisticalStandardQueryService service;

    @Test
    public void getAllStatisticalStandardsTest() {
        //Setup
        final GetStatisticalStandardsQuery query = GetStatisticalStandardsQuery.create(Language.ENG);

        final StatisticalStandardReferenceEntity statisticalStandard = new StatisticalStandardReferenceEntity();
        statisticalStandard.setId(1L);
        statisticalStandard.setName("Standard", query.getLanguage());

        when(repository.findAll()).thenReturn(Collections.singletonList(statisticalStandard));

        //call
        service.getStatisticalStandards(query);

        //verify
        Mockito.verify(repository).findAll();
    }

    @Test
    public void getAStatisticalStandardsByNameTest() {
        //Setup
        final GetStatisticalStandardsQuery query = GetStatisticalStandardsQuery.create(null, "Sta", Language.ENG);

        final StatisticalStandardReferenceEntity statisticalStandard = new StatisticalStandardReferenceEntity();
        statisticalStandard.setId(1L);
        statisticalStandard.setName("Standard", query.getLanguage());

        when(repository.findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()),
                eq(query.getName()))).thenReturn(Collections.singletonList(statisticalStandard));

        //call
        service.getStatisticalStandards(query);

        //verify
        Mockito.verify(repository).findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()),
                eq(query.getName()));
    }

    @Test
    public void getAStatisticalStandardsByTypeTest() {
        //Setup
        final GetStatisticalStandardsQuery query = GetStatisticalStandardsQuery.create(StatisticalStandardType.CONCEPTS,
                null, Language.ENG);

        final StatisticalStandardReferenceEntity statisticalStandard = new StatisticalStandardReferenceEntity();
        statisticalStandard.setId(1L);
        statisticalStandard.setName("Standard", query.getLanguage());
        statisticalStandard.setType(StatisticalStandardType.CONCEPTS);

        when(repository.findByType(eq(query.getType()))).thenReturn(Collections.singletonList(statisticalStandard));

        //call
        service.getStatisticalStandards(query);

        //verify
        Mockito.verify(repository).findByType(eq(query.getType()));
    }

    @Test
    public void getStatisticalStandardByIdTest() {
        //setup
        final GetStatisticalStandardQuery query = GetStatisticalStandardQuery.create(1L, Language.ENG);

        final StatisticalStandardReferenceEntity statisticalStandard = new StatisticalStandardReferenceEntity();
        statisticalStandard.setId(1L);
        statisticalStandard.setName("Standard", query.getLanguage());
        statisticalStandard.setType(StatisticalStandardType.CONCEPTS);

        when(repository.findById(eq(query.getId()))).thenReturn(Optional.of(statisticalStandard));

        //call
        service.getStatisticalStandard(query);

        //verify
        verify(repository).findById(eq(query.getId()));

    }
}


