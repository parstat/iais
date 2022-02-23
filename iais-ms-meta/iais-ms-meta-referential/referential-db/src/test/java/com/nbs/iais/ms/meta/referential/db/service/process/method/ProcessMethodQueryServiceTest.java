package com.nbs.iais.ms.meta.referential.db.service.process.method;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.method.GetProcessMethodsQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessMethodEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessMethodRepository;
import com.nbs.iais.ms.meta.referential.db.services.ProcessMethodQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProcessMethodQueryServiceTest extends ServiceTest {

    @Mock
    private ProcessMethodRepository repository;

    @InjectMocks
    private ProcessMethodQueryService service;

    private ProcessMethodEntity getProcessMethod(final Language language) {
        ProcessMethodEntity processMethodEntity = new ProcessMethodEntity();
        processMethodEntity.setId(1L);
        processMethodEntity.setName("Name", language);
        processMethodEntity.setDescription("Description", language);
        processMethodEntity.setVersion("1.0");
        processMethodEntity.setVersionDate(LocalDateTime.now());
        processMethodEntity.setVersionRationale("Rationale");
        return processMethodEntity;
    }

    @Test
    public void getProcessMethodByIdTest() {

        //setup
        final GetProcessMethodQuery query = GetProcessMethodQuery.create(1L, Language.ENG);

        final ProcessMethodEntity processMethod = getProcessMethod(query.getLanguage());

        when(repository.findById(eq(query.getId()))).thenReturn(Optional.of(processMethod));

        //call
        service.getProcessMethod(query);

        //verify
        verify(repository).findById(eq(query.getId()));
    }

    @Test
    public void getProcessMethodsByNameTest() {
        //setup
        final GetProcessMethodsQuery query = GetProcessMethodsQuery.create("Nam", Language.ENG);

        final ProcessMethodEntity processMethod = getProcessMethod(query.getLanguage());

        when(repository.findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()), eq(query.getName())))
        .thenReturn(Collections.singletonList(processMethod));

        //call
        service.getProcessMethods(query);

        //verify
        verify(repository).findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()), eq(query.getName()));
    }

    @Test
    public void getAllProcessMethodsTest() {
        //setup
        final GetProcessMethodsQuery query = GetProcessMethodsQuery.create(null, Language.ENG);

        final ProcessMethodEntity processMethod = getProcessMethod(query.getLanguage());

        when(repository.findAll()).thenReturn(Collections.singletonList(processMethod));

        //call
        service.getProcessMethods(query);

        //verify
        verify(repository).findAll();
    }
}
