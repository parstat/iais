package com.nbs.iais.ms.meta.referential.db.repository;

import com.nbs.iais.ms.common.db.repository.tests.RepositoryTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class LegislativeReferenceRepositoryTest extends RepositoryTest {

    @Autowired
    private LegislativeReferenceRepository legislativeReferenceRepository;


    @Test
    public void testSaveBusinessFunction(){
        final LegislativeReferenceEntity toSave = saveLegislativeReference();
        final LegislativeReferenceEntity saved = legislativeReferenceRepository.save(toSave);

        Assertions.assertNotNull(saved);
    }

    @Test
    public void testFindByType() {
        final LegislativeReferenceEntity toSave = saveLegislativeReference();
        final LegislativeReferenceEntity saved = legislativeReferenceRepository.save(toSave);

        Iterable<LegislativeReferenceEntity> legislativeReference = legislativeReferenceRepository.findByType(LegislativeType.REGULATION);
        Assertions.assertTrue(legislativeReference.iterator().hasNext());
    }

    @Test
    public void testFindAllByNameInLanguageContaining() {
        final LegislativeReferenceEntity toSave = saveLegislativeReference();
        final LegislativeReferenceEntity saved = legislativeReferenceRepository.save(toSave);

        Iterable<LegislativeReferenceEntity> legislativeReference = legislativeReferenceRepository.findAllByNameInLanguageContaining(Language.ENG.getShortName(),"name");
        Assertions.assertTrue(legislativeReference.iterator().hasNext());
    }

    private LegislativeReferenceEntity saveLegislativeReference(){
        final LegislativeReferenceEntity toSave = new LegislativeReferenceEntity();
        toSave.setName("Name test",Language.ENG);
        toSave.setName("Nume", Language.ROM);
        toSave.setName("имя", Language.RUS);
        toSave.setDescription("Description", Language.ENG);
        toSave.setDescription("Descriere", Language.ROM);
        toSave.setDescription("описание", Language.RUS);
        toSave.setLocalId("lfs");
        toSave.setVersion("1.0");
        toSave.setVersionDate(LocalDateTime.now());
        toSave.setLegislativeType(LegislativeType.REGULATION);

        return toSave;
    }
}
