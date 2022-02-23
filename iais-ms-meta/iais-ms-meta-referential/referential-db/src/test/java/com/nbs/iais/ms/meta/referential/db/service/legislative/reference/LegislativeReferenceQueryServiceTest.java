package com.nbs.iais.ms.meta.referential.db.service.legislative.reference;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.services.LegislativeReferenceQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LegislativeReferenceQueryServiceTest extends ServiceTest {

    @Mock
    private LegislativeReferenceRepository repository;

    @InjectMocks
    private LegislativeReferenceQueryService service;

    @Test
    public void getLegislativeReferenceByIdTest() {

        //Setup
        final GetLegislativeReferenceQuery query = GetLegislativeReferenceQuery.create(1L, Language.ENG);

        final LegislativeReferenceEntity legislativeReference = getLegislativeReferenceEntity(query.getLanguage());

        when(repository.findById(eq(query.getId()))).thenReturn(Optional.of(legislativeReference));

        //call
        service.getLegislativeReference(query);

        //verify
        verify(repository).findById(query.getId());
    }

    private LegislativeReferenceEntity getLegislativeReferenceEntity(Language language) {
        final LegislativeReferenceEntity legislativeReference = new LegislativeReferenceEntity();
        legislativeReference.setId(1L);
        legislativeReference.setName("Name", language);
        legislativeReference.setLink("Link", language);
        legislativeReference.setLegislativeType(LegislativeType.LAW);
        return legislativeReference;
    }

    @Test
    public void getLegislativeReferencesByType() {

        //Setup
        final GetLegislativeReferencesQuery query = GetLegislativeReferencesQuery.create(LegislativeType.LAW, null, Language.ENG);

        final LegislativeReferenceEntity legislativeReference = getLegislativeReferenceEntity(query.getLanguage());

        when(repository.findByType(eq(query.getType()))).thenReturn(Collections.singletonList(legislativeReference));

        //call
        service.getLegislativeReferences(query);

        //verify
        verify(repository).findByType(eq(query.getType()));
    }

    @Test
    public void getLegislativeReferencesByName() {

        //Setup
        final GetLegislativeReferencesQuery query = GetLegislativeReferencesQuery.create(null, "Name", Language.ENG);

        final LegislativeReferenceEntity legislativeReference = getLegislativeReferenceEntity(query.getLanguage());

        when(repository.findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()), eq(query.getName())))
                .thenReturn(Collections.singletonList(legislativeReference));

        //call
        service.getLegislativeReferences(query);

        //verify
        verify(repository).findAllByNameInLanguageContaining(eq(query.getLanguage().getShortName()), eq(query.getName()));
    }
}
